package com.valentinesgarage.data.repository

import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.valentinesgarage.data.model.Job
import com.valentinesgarage.data.model.JobStatus
import com.valentinesgarage.data.model.Task
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Handles all job and task operations.
 *
 * Tasks are stored as a Firestore subcollection: jobs/{jobId}/tasks/{taskId}
 * Real-time listeners (Flows) power the collaborative checklist — when one
 * mechanic ticks off a task, all other mechanics see it update instantly.
 */
@Singleton
class JobRepository @Inject constructor(
    private val firestore: FirebaseFirestore
) {

    companion object {
        private const val JOBS_COLLECTION = "jobs"
        private const val TASKS_SUBCOLLECTION = "tasks"
    }

    // ── Jobs ──────────────────────────────────────────────────────────────────

    /**
     * Returns all open jobs as a real-time Flow.
     * Mechanics see this list to pick up work.
     */
    fun getOpenJobsFlow(): Flow<List<Job>> = callbackFlow {
        val listener = firestore.collection(JOBS_COLLECTION)
            .whereEqualTo("status", JobStatus.OPEN.name)
            .orderBy("createdAt", Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot, error ->
                if (error != null) { close(error); return@addSnapshotListener }
                val jobs = snapshot?.documents
                    ?.mapNotNull { it.toObject(Job::class.java) }
                    ?: emptyList()
                trySend(jobs)
            }
        awaitClose { listener.remove() }
    }

    /**
     * Returns ALL jobs (open + completed) — used in Valentine's reports.
     */
    fun getAllJobsFlow(): Flow<List<Job>> = callbackFlow {
        val listener = firestore.collection(JOBS_COLLECTION)
            .orderBy("createdAt", Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot, error ->
                if (error != null) { close(error); return@addSnapshotListener }
                val jobs = snapshot?.documents
                    ?.mapNotNull { it.toObject(Job::class.java) }
                    ?: emptyList()
                trySend(jobs)
            }
        awaitClose { listener.remove() }
    }

    /**
     * Marks a job as completed once all tasks are ticked off.
     */
    suspend fun completeJob(jobId: String): Result<Unit> {
        return try {
            firestore.collection(JOBS_COLLECTION).document(jobId)
                .update(
                    mapOf(
                        "status" to JobStatus.COMPLETED.name,
                        "completedAt" to Timestamp.now()
                    )
                ).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // ── Tasks ─────────────────────────────────────────────────────────────────

    /**
     * Returns all tasks for a job as a real-time Flow.
     * This is the core of the collaborative checklist — every mechanic
     * working on the same job observes the same live task list.
     */
    fun getTasksFlow(jobId: String): Flow<List<Task>> = callbackFlow {
        val listener = firestore.collection(JOBS_COLLECTION)
            .document(jobId)
            .collection(TASKS_SUBCOLLECTION)
            .orderBy("createdAt", Query.Direction.ASCENDING)
            .addSnapshotListener { snapshot, error ->
                if (error != null) { close(error); return@addSnapshotListener }
                val tasks = snapshot?.documents
                    ?.mapNotNull { it.toObject(Task::class.java) }
                    ?: emptyList()
                trySend(tasks)
            }
        awaitClose { listener.remove() }
    }

    /**
     * Adds a new task to a job's checklist.
     * Called by a mechanic or admin when a repair item is identified.
     */
    suspend fun addTask(jobId: String, description: String): Result<Task> {
        return try {
            val taskId = UUID.randomUUID().toString()
            val task = Task(
                taskId = taskId,
                jobId = jobId,
                description = description,
                createdAt = Timestamp.now()
            )
            firestore.collection(JOBS_COLLECTION)
                .document(jobId)
                .collection(TASKS_SUBCOLLECTION)
                .document(taskId)
                .set(task.toMap())
                .await()
            Result.success(task)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Marks a task as done and records which mechanic completed it.
     * The [mechanicUid] and [mechanicName] are stored so Valentine's
     * reports can show exactly who did what.
     *
     * @param notes Optional notes the mechanic wants to add about this task.
     */
    suspend fun completeTask(
        jobId: String,
        taskId: String,
        mechanicUid: String,
        mechanicName: String,
        notes: String = ""
    ): Result<Unit> {
        return try {
            firestore.collection(JOBS_COLLECTION)
                .document(jobId)
                .collection(TASKS_SUBCOLLECTION)
                .document(taskId)
                .update(
                    mapOf(
                        "isDone" to true,
                        "notes" to notes,
                        "completedBy" to mechanicUid,
                        "completedByName" to mechanicName,
                        "completedAt" to Timestamp.now()
                    )
                ).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Fetches all completed tasks across all jobs for a specific mechanic.
     * Powers Valentine's per-employee report view.
     */
    suspend fun getCompletedTasksByMechanic(mechanicUid: String): Result<List<Task>> {
        return try {
            // Firestore collection group query — searches across all task subcollections
            val snapshot = firestore.collectionGroup(TASKS_SUBCOLLECTION)
                .whereEqualTo("completedBy", mechanicUid)
                .whereEqualTo("isDone", true)
                .get()
                .await()
            val tasks = snapshot.documents.mapNotNull { it.toObject(Task::class.java) }
            Result.success(tasks)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
