package com.valentinesgarage.ui.jobs

import android.util.Log
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

    companion object {
        private const val TAG = "JobListViewModel"
    }

    val openJobs: StateFlow<List<Job>> = jobRepository.getOpenJobsFlow()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val currentUser get() = userRepository.currentFirebaseUser

    // ---------------------------------------------------------------------------
    // Admin role state
    //
    // Kept as a MutableStateFlow so the Fragment can:
    //   (a) read the CURRENT value synchronously in onCreateMenu() / onPrepareMenu()
    //   (b) collect emissions to trigger invalidateOptionsMenu() when Firestore
    //       returns after the menu is first drawn.
    //
    // The backing field is also exposed as a plain var (isAdminResolved) so the
    // Fragment can store it as a stable property and avoid relying on .value
    // being correct at the exact instant onCreateMenu() fires.
    // ---------------------------------------------------------------------------

    private val _isAdmin = MutableStateFlow(false)
    val isAdmin: StateFlow<Boolean> = _isAdmin

    /** True once the Firestore role fetch has completed. */
    var roleLoaded: Boolean = false
        private set

    init {
        loadRole()
    }

    private fun loadRole() {
        val uid = userRepository.currentFirebaseUser?.uid
        Log.d(TAG, "loadRole: uid=$uid")
        if (uid == null) {
            Log.w(TAG, "loadRole: no signed-in user — isAdmin stays false")
            roleLoaded = true
            return
        }
        viewModelScope.launch {
            val result = userRepository.getUser(uid)
            val role = result.getOrNull()?.role ?: UserRole.MECHANIC
            val admin = (role == UserRole.ADMIN)
            Log.d(TAG, "loadRole: uid=$uid  role=$role  isAdmin=$admin")
            _isAdmin.value = admin
            roleLoaded = true
        }
    }

    /** Signs the current user out of Firebase Auth. */
    fun logout() = userRepository.logout()
}
