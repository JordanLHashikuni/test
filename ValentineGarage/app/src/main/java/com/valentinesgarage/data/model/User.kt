package com.valentinesgarage.data.model

/**
 * Represents a garage employee.
 * Role determines what screens and actions the user can access.
 */
data class User(
    val uid: String = "",
    val name: String = "",
    val email: String = "",
    val role: UserRole = UserRole.MECHANIC
) {
    /** Required by Firestore for deserialization */
    constructor() : this("", "", "", UserRole.MECHANIC)

    fun toMap(): Map<String, Any> = mapOf(
        "uid" to uid,
        "name" to name,
        "email" to email,
        "role" to role.name
    )
}

enum class UserRole {
    /** Can check in trucks, update tasks, add notes */
    MECHANIC,

    /** Full access: check-in, tasks, and reports (Valentine) */
    ADMIN
}
