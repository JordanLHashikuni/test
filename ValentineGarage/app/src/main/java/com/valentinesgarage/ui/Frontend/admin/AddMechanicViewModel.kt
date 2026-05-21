package com.valentinesgarage.ui.admin

import androidx.lifecycle.ViewModel
import com.valentinesgarage.data.model.User
import com.valentinesgarage.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddMechanicViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    /**
     * Creates a new mechanic account in Firebase Auth and Firestore.
     *
     * FIX: Now accepts adminEmail and adminPassword so UserRepository can
     * re-authenticate the admin after Firebase automatically switches the
     * Auth session to the newly created mechanic account.
     *
     * @param name          Display name for the new mechanic.
     * @param email         Login email for the new mechanic.
     * @param password      Login password for the new mechanic.
     * @param adminEmail    The currently signed-in admin's email.
     * @param adminPassword The currently signed-in admin's password.
     */
    suspend fun createMechanic(
        name: String,
        email: String,
        password: String,
        adminEmail: String,
        adminPassword: String
    ): Result<User> = userRepository.createMechanic(
        name          = name,
        email         = email,
        password      = password,
        adminEmail    = adminEmail,
        adminPassword = adminPassword
    )
}
