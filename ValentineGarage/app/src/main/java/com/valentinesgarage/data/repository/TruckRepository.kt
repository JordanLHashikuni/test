package com.valentinesgarage.data.repository

import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.storage.FirebaseStorage
import com.valentinesgarage.data.model.Job
import com.valentinesgarage.data.model.JobStatus
import com.valentinesgarage.data.model.Truck
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Handles all truck check-in data operations.
 * When a truck is checked in, a [Truck] document and a linked [Job] are
 * created together so repairs can immediately be assigned.
 */
@Singleton
class TruckRepository @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val storage: FirebaseStorage
) {

    companion object {
        private const val TRUCKS_COLLECTION = "trucks"
        private const val JOBS_COLLECTION = "jobs"
    }

    /**
     * Checks in a truck and automatically opens a repair Job for it.
     * Both documents are written in a Firestore batch for atomicity —
     * either both succeed or neither does.
     *
     * @return Result wrapping the created [Truck] or an exception.
     */
    suspend fun checkInTruck(truck: Truck): Result<Truck> {
        return try {
            val truckId = UUID.randomUUID().toString()
            val jobId = UUID.randomUUID().toString()
            val now = Timestamp.now()

            val newTruck = truck.copy(truckId = truckId, checkInTime = now)

            // Create a job linked to this truck automatically
            val newJob = Job(
                jobId = jobId,
                truckId = truckId,
                truckRegistration = truck.registrationNumber,
                status = JobStatus.OPEN,
                createdBy = truck.checkedInBy,
                createdAt = now
            )

            // Atomic batch write — truck + job created together
            firestore.batch().apply {
                set(firestore.collection(TRUCKS_COLLECTION).document(truckId), newTruck.toMap())
                set(firestore.collection(JOBS_COLLECTION).document(jobId), newJob.toMap())
            }.commit().await()

            Result.success(newTruck)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Uploads a vehicle condition photo to Firebase Storage.
     * Returns the public download URL to store in the Truck document.
     */
    suspend fun uploadTruckPhoto(truckId: String, imageBytes: ByteArray): Result<String> {
        return try {
            val ref = storage.reference.child("trucks/$truckId/${UUID.randomUUID()}.jpg")
            ref.putBytes(imageBytes).await()
            val url = ref.downloadUrl.await().toString()
            Result.success(url)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Retrieves a single truck by ID — used in the Job Detail screen
     * to show check-in condition alongside the repair checklist.
     */
    suspend fun getTruck(truckId: String): Result<Truck> {
        return try {
            val doc = firestore.collection(TRUCKS_COLLECTION).document(truckId).get().await()
            val truck = doc.toObject(Truck::class.java)
                ?: return Result.failure(Exception("Truck not found"))
            Result.success(truck)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Returns all trucks as a real-time Flow ordered by check-in time.
     * Used in Valentine's reports screen.
     */
    fun getAllTrucksFlow(): Flow<List<Truck>> = callbackFlow {
        val listener = firestore.collection(TRUCKS_COLLECTION)
            .orderBy("checkInTime", Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot, error ->
                if (error != null) { close(error); return@addSnapshotListener }
                val trucks = snapshot?.documents
                    ?.mapNotNull { it.toObject(Truck::class.java) }
                    ?: emptyList()
                trySend(trucks)
            }
        awaitClose { listener.remove() }
    }
}
