package com.valentinesgarage.ui.jobs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valentinesgarage.data.model.Task
import com.valentinesgarage.data.model.Truck
import com.valentinesgarage.data.repository.JobRepository
import com.valentinesgarage.data.repository.TruckRepository
import com.valentinesgarage.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JobDetailViewModel @Inject constructor(
    private val jobRepository: JobRepository,
    private val truckRepository: TruckRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _truck = MutableStateFlow<Truck?>(null)
    val truck: StateFlow<Truck?> = _truck

    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private var currentJobId: String = ""

    fun init(jobId: String, truckId: String) {
        currentJobId = jobId
        loadTruck(truckId)
        observeTasks(jobId)
    }

    private fun loadTruck(truckId: String) {
        viewModelScope.launch {
            truckRepository.getTruck(truckId).onSuccess { _truck.value = it }
        }
    }

    private fun observeTasks(jobId: String) {
        viewModelScope.launch {
            jobRepository.getTasksFlow(jobId).collect { _tasks.value = it }
        }
    }

    fun addTask(description: String) {
        if (description.isBlank()) return
        viewModelScope.launch {
            jobRepository.addTask(currentJobId, description).onFailure {
                _error.value = it.message
            }
        }
    }

    fun completeTask(taskId: String, notes: String) {
        val user = userRepository.currentFirebaseUser ?: return
        viewModelScope.launch {
            jobRepository.completeTask(
                jobId = currentJobId,
                taskId = taskId,
                mechanicUid = user.uid,
                mechanicName = user.displayName ?: user.email ?: "Unknown",
                notes = notes
            ).onFailure { _error.value = it.message }
        }
    }
}
