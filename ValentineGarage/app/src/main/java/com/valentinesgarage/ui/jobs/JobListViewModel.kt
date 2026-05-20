package com.valentinesgarage.ui.jobs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valentinesgarage.data.model.Job
import com.valentinesgarage.data.model.UserRole
import com.valentinesgarage.data.repository.JobRepository
import com.valentinesgarage.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JobListViewModel @Inject constructor(
    jobRepository: JobRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    val openJobs: StateFlow<List<Job>> = jobRepository.getOpenJobsFlow()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val currentUser get() = userRepository.currentFirebaseUser

    // Emits true when the signed-in user is an admin so the fragment can
    // show/hide the "Add Staff" overflow menu item.
    private val _isAdmin = MutableStateFlow(false)
    val isAdmin: StateFlow<Boolean> = _isAdmin

    init {
        loadRole()
    }

    private fun loadRole() {
        val uid = userRepository.currentFirebaseUser?.uid ?: return
        viewModelScope.launch {
            val role = userRepository.getUser(uid).getOrNull()?.role ?: UserRole.MECHANIC
            _isAdmin.value = (role == UserRole.ADMIN)
        }
    }

    /** Signs the current user out of Firebase Auth. */
    fun logout() = userRepository.logout()
}
