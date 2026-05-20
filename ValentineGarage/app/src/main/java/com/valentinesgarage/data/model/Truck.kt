package com.valentinesgarage.data.model

import com.google.firebase.Timestamp

/**
 * Represents a truck at the point of check-in.
 * Captures vehicle condition and odometer reading to prevent misuse.
 */
data class Truck(
    val truckId: String = "",
    val registrationNumber: String = "",
    val make: String = "",
    val model: String = "",
    val kilometerReading: Long = 0L,
    val conditionRating: ConditionRating = ConditionRating.GOOD,
    val conditionNotes: String = "",
    val photoUrls: List<String> = emptyList(),
    val checkedInBy: String = "",         // User uid
    val checkedInByName: String = "",     // Display name (for reports)
    val checkInTime: Timestamp? = null
) {
    constructor() : this("", "", "", "", 0L, ConditionRating.GOOD, "", emptyList(), "", "", null)

    fun toMap(): Map<String, Any?> = mapOf(
        "truckId" to truckId,
        "registrationNumber" to registrationNumber,
        "make" to make,
        "model" to model,
        "kilometerReading" to kilometerReading,
        "conditionRating" to conditionRating.name,
        "conditionNotes" to conditionNotes,
        "photoUrls" to photoUrls,
        "checkedInBy" to checkedInBy,
        "checkedInByName" to checkedInByName,
        "checkInTime" to checkInTime
    )
}

/**
 * Overall vehicle condition captured at check-in.
 * Displayed in Valentine's reports to track vehicle state over time.
 */
enum class ConditionRating {
    EXCELLENT,
    GOOD,
    FAIR,
    POOR,
    CRITICAL
}
