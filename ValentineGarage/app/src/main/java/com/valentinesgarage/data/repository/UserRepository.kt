package com.valentinesgarage.data.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.valentinesgarage.data.model.User
import com.valentinesgarage.data.model.UserRole
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) {

    companion object {
        private const val TAG = "UserRepository"

        // "Users" (capital U) — this is the collection that was used when the
        // admin document was first created. The Firestore rules must use the
        // same casing: match /Users/{userId}. Do not change this without also
        // migrating all existing documents and updating the security rules.
        private const val USERS_COLLECTION = "Users"
    }

    val currentFirebaseUser: FirebaseUser?
        get() = auth.currentUser

    val authStateFlow: Flow<FirebaseUser?> = callbackFlow {
        val listener = FirebaseAuth.AuthStateListener { trySend(it.currentUser) }
        auth.addAuthStateListener(listener)
        awaitClose { auth.removeAuthStateListener(listener) }
    }

    suspend fun login(email: String, password: String): Result<FirebaseUser> {
        return try {
            val result = auth.signInWithEmailAndPassword(email, password).await()
            val user = result.user
                ?: return Result.failure(Exception("Login failed: no user returned"))
            Log.d(TAG, "login: Auth succeeded for uid=${user.uid}")
            Result.success(user)
        } catch (e: Exception) {
            Log.e(TAG, "login: Failed — ${e.message}", e)
            Result.failure(e)
        }
    }

    /**
     * Fetches the Firestore profile for the given uid.
     *
     * Reads raw document fields and converts the "role" string manually via
     * UserRole.valueOf() instead of toObject(). Firestore's reflection-based
     * deserializer silently fails to map stored strings (e.g. "ADMIN") to
     * Kotlin enum values, leaving role as the default (MECHANIC) for every user.
     */
    suspend fun getUser(uid: String): Result<User> {
        return try {
            Log.d(TAG, "getUser: Fetching profile for uid=$uid from collection=$USERS_COLLECTION")
            val doc = firestore.collection(USERS_COLLECTION).document(uid).get().await()

            if (!doc.exists()) {
                Log.w(TAG, "getUser: No document found at $USERS_COLLECTION/$uid")
                return Result.failure(Exception("User profile not found for uid=$uid"))
            }

            val rawRole = doc.getString("role")
            Log.d(TAG, "getUser: raw role from Firestore = \"$rawRole\"")

            val role = try {
                if (rawRole != null) UserRole.valueOf(rawRole) else UserRole.MECHANIC
            } catch (e: IllegalArgumentException) {
                Log.w(TAG, "getUser: Unrecognised role \"$rawRole\" — defaulting to MECHANIC")
                UserRole.MECHANIC
            }

            val user = User(
                uid   = doc.getString("uid")   ?: uid,
                name  = doc.getString("name")  ?: "",
                email = doc.getString("email") ?: "",
                role  = role
            )
            Log.d(TAG, "getUser: resolved uid=${user.uid}  role=${user.role}")
            Result.success(user)

        } catch (e: Exception) {
            Log.e(TAG, "getUser: Failed for uid=$uid — ${e.message}", e)
            Result.failure(e)
        }
    }

    /**
     * Creates a new mechanic account.
     *
     * Auth state sequence:
     *   1. createUserWithEmailAndPassword() creates the mechanic Auth account
     *      AND immediately switches the session — request.auth.uid is now
     *      the mechanic, not the admin.
     *   2. The Firestore write therefore runs as the mechanic. The security
     *      rule covering this is:
     *        allow create: if isOwner(userId)                       // mechanic uid matches ✓
     *                      && request.resource.data.role == "MECHANIC"  // ✓
     *                      && request.resource.data.uid  == userId;     // ✓
     *   3. reSignInAdmin() restores the admin session afterwards.
     */
    suspend fun createMechanic(
        name: String,
        email: String,
        password: String,
        adminEmail: String,
        adminPassword: String
    ): Result<User> {
        Log.d(TAG, "createMechanic: starting — adminUid=${auth.currentUser?.uid}")

        val mechanicUid: String
        try {
            val result = auth.createUserWithEmailAndPassword(email, password).await()
            mechanicUid = result.user?.uid
                ?: return Result.failure(Exception("Auth account created but UID was null"))
            Log.d(TAG, "createMechanic: Auth created mechanicUid=$mechanicUid  " +
                       "currentUid=${auth.currentUser?.uid}")
        } catch (e: Exception) {
            Log.e(TAG, "createMechanic: Auth creation failed — ${e.message}", e)
            reSignInAdmin(adminEmail, adminPassword)
            return Result.failure(Exception("Failed to create Auth account: ${e.message}"))
        }

        val user = User(uid = mechanicUid, name = name, email = email, role = UserRole.MECHANIC)
        Log.d(TAG, "createMechanic: writing $USERS_COLLECTION/$mechanicUid — ${user.toMap()}")
        try {
            firestore
                .collection(USERS_COLLECTION)
                .document(mechanicUid)
                .set(user.toMap())
                .await()
            Log.d(TAG, "createMechanic: Firestore write succeeded")
        } catch (e: Exception) {
            Log.e(TAG, "createMechanic: Firestore write failed — ${e.message}", e)
            try {
                auth.currentUser?.delete()?.await()
                Log.d(TAG, "createMechanic: orphaned Auth account deleted")
            } catch (deleteEx: Exception) {
                Log.e(TAG, "createMechanic: could not delete orphaned Auth account", deleteEx)
            }
            reSignInAdmin(adminEmail, adminPassword)
            return Result.failure(Exception("Firestore profile write failed: ${e.message}"))
        }

        reSignInAdmin(adminEmail, adminPassword)
        return Result.success(user)
    }

    private suspend fun reSignInAdmin(adminEmail: String, adminPassword: String) {
        try {
            auth.signInWithEmailAndPassword(adminEmail, adminPassword).await()
            Log.d(TAG, "reSignInAdmin: restored uid=${auth.currentUser?.uid}")
        } catch (e: Exception) {
            Log.e(TAG, "reSignInAdmin: failed — ${e.message}", e)
        }
    }

    suspend fun getAllMechanics(): Result<List<User>> {
        return try {
            val snapshot = firestore.collection(USERS_COLLECTION)
                .whereEqualTo("role", UserRole.MECHANIC.name)
                .get()
                .await()
            val mechanics = snapshot.documents.mapNotNull { doc ->
                val rawRole = doc.getString("role") ?: return@mapNotNull null
                val role = try { UserRole.valueOf(rawRole) }
                           catch (e: IllegalArgumentException) { return@mapNotNull null }
                User(
                    uid   = doc.getString("uid")   ?: doc.id,
                    name  = doc.getString("name")  ?: "",
                    email = doc.getString("email") ?: "",
                    role  = role
                )
            }
            Log.d(TAG, "getAllMechanics: found ${mechanics.size}")
            Result.success(mechanics)
        } catch (e: Exception) {
            Log.e(TAG, "getAllMechanics: failed — ${e.message}", e)
            Result.failure(e)
        }
    }

    fun logout() = auth.signOut()
}
