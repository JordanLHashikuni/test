package com.valentinesgarage.data.model

import com.google.firebase.Timestamp

/**
 * Represents a single repair/service task within a Job.
 * Mechanics tick off tasks and add notes as they work.
 * Stored as a subcollection: jobs/{jobId}/tasks/{taskId}
 *
 * The [completedBy] and [completedByName] fields allow Valentine to
 * see exactly which mechanic did what — the core reporting requirement.
 */
data class Task(
    val taskId: String = "",
    val jobId: String = "",
    val description: String = "",
    val isDone: Boolean = false,
    val notes: String = "",
    val completedBy: String = "",         // User uid — empty if not yet done
    val completedByName: String = "",     // Display name for reports
    val completedAt: Timestamp? = null,
    val createdAt: Timestamp? = null
) {
    constructor() : this("", "", "", false, "", "", "", null, null)

    fun toMap(): Map<String, Any?> = mapOf(
        "taskId" to taskId,
        "jobId" to jobId,
        "description" to description,
        "isDone" to isDone,
        "notes" to notes,
        "completedBy" to completedBy,
        "completedByName" to completedByName,
        "completedAt" to completedAt,
        "createdAt" to createdAt
    )
}
