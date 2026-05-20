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
        private const val USERS_COLLECTION = "Users"
    }

    val currentFirebaseUser: FirebaseUser?
        get() = auth.currentUser

    val authStateFlow: Flow<FirebaseUser?> = callbackFlow {
        val listener = FirebaseAuth.AuthStateListener { trySend(it.currentUser) }
        auth.addAuthStateListener(listener)
        awaitClose { auth.removeAuthStateListener(listener) }
    }

    /**
     * Signs the user in with Firebase Auth only.
     *
     * FIX: The previous version called getUser() immediately after
     * signInWithEmailAndPassword(). This caused a race: Firebase Auth
     * issues a new ID token on sign-in, but Firestore's server-side rule
     * evaluation uses that token to verify auth state. On slow connections
     * or cold starts the token is not yet propagated when the Firestore
     * request arrives, so the read is evaluated as unauthenticated and
     * returns PERMISSION_DENIED — even with correct rules.
     *
     * Login now only authenticates. The Firestore profile is fetched
     * separately by getUser(), called only after navigation to JobListFragment
     * where the auth token is guaranteed to be valid. AuthViewModel.LoginState
     * now uses the FirebaseUser directly instead of the full User model.
     */
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
     * Called after login, never during login.
     */
    suspend fun getUser(uid: String): Result<User> {
        return try {
            Log.d(TAG, "getUser: Fetching profile for uid=$uid")
            val doc = firestore.collection(USERS_COLLECTION).document(uid).get().await()
            val user = doc.toObject(User::class.java)
                ?: return Result.failure(Exception("User profile not found for uid=$uid"))
            Log.d(TAG, "getUser: role=${user.role}")
            Result.success(user)
        } catch (e: Exception) {
            Log.e(TAG, "getUser: Failed for uid=$uid — ${e.message}", e)
            Result.failure(e)
        }
    }

    /**
     * Creates a new mechanic account in Firebase Auth then writes their
     * Firestore profile while signed in as the mechanic (see comments in
     * the previous fix for the full explanation of why this is necessary).
     */
    suspend fun createMechanic(
        name: String,
        email: String,
        password: String,
        adminEmail: String,
        adminPassword: String
    ): Result<User> {
        Log.d(TAG, "createMechanic: Starting for email=$email")

        val mechanicUid: String
        try {
            val result = auth.createUserWithEmailAndPassword(email, password).await()
            mechanicUid = result.user?.uid
                ?: return Result.failure(Exception("Auth account created but UID was null"))
            Log.d(TAG, "createMechanic: Auth account created uid=$mechanicUid")
        } catch (e: Exception) {
            Log.e(TAG, "createMechanic: Auth creation failed — ${e.message}", e)
            reSignInAdmin(adminEmail, adminPassword)
            return Result.failure(Exception("Failed to create Auth account: ${e.message}"))
        }

        val user = User(uid = mechanicUid, name = name, email = email, role = UserRole.MECHANIC)
        try {
            firestore
                .collection(USERS_COLLECTION)
                .document(mechanicUid)
                .set(user.toMap())
                .await()
            Log.d(TAG, "createMechanic: Firestore profile written for uid=$mechanicUid")
        } catch (e: Exception) {
            Log.e(TAG, "createMechanic: Firestore write failed — ${e.message}", e)
            try {
                auth.currentUser?.delete()?.await()
                Log.d(TAG, "createMechanic: Orphaned Auth account deleted")
            } catch (deleteEx: Exception) {
                Log.e(TAG, "createMechanic: Could not delete orphaned Auth account", deleteEx)
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
            Log.d(TAG, "reSignInAdmin: Admin session restored")
        } catch (e: Exception) {
            Log.e(TAG, "reSignInAdmin: Could not restore admin session", e)
        }
    }

    suspend fun getAllMechanics(): Result<List<User>> {
        return try {
            val snapshot = firestore.collection(USERS_COLLECTION)
                .whereEqualTo("role", UserRole.MECHANIC.name)
                .get()
                .await()
            val mechanics = snapshot.documents.mapNotNull { it.toObject(User::class.java) }
            Log.d(TAG, "getAllMechanics: Found ${mechanics.size}")
            Result.success(mechanics)
        } catch (e: Exception) {
            Log.e(TAG, "getAllMechanics: Failed — ${e.message}", e)
            Result.failure(e)
        }
    }

    fun logout() = auth.signOut()
}
