package com.valentinesgarage.data.model

import com.google.firebase.Timestamp

/**
 * Represents a repair/service job for a checked-in truck.
 * A Job is created at check-in and contains a list of Tasks.
 * Multiple mechanics can be assigned to the same job.
 */
data class Job(
    val jobId: String = "",
    val truckId: String = "",
    val truckRegistration: String = "",   // Denormalised for easy display
    val status: JobStatus = JobStatus.OPEN,
    val assignedMechanics: List<String> = emptyList(),   // List of User uids
    val createdBy: String = "",
    val createdAt: Timestamp? = null,
    val completedAt: Timestamp? = null
) {
    constructor() : this("", "", "", JobStatus.OPEN, emptyList(), "", null, null)

    fun toMap(): Map<String, Any?> = mapOf(
        "jobId" to jobId,
        "truckId" to truckId,
        "truckRegistration" to truckRegistration,
        "status" to status.name,
        "assignedMechanics" to assignedMechanics,
        "createdBy" to createdBy,
        "createdAt" to createdAt,
        "completedAt" to completedAt
    )
}

enum class JobStatus {
    /** Job is active — tasks still in progress */
    OPEN,

    /** All tasks ticked off */
    COMPLETED,

    /** Job was cancelled */
    CANCELLED
}
