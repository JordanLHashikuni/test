package com.valentinesgarage.ui.reports

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valentinesgarage.data.model.Task
import com.valentinesgarage.data.model.User
import com.valentinesgarage.data.repository.JobRepository
import com.valentinesgarage.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReportsViewModel @Inject constructor(
    private val jobRepository: JobRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _mechanics = MutableStateFlow<List<User>>(emptyList())
    val mechanics: StateFlow<List<User>> = _mechanics

    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    init {
        loadMechanics()
    }

    private fun loadMechanics() {
        viewModelScope.launch {
            userRepository.getAllMechanics().onSuccess { _mechanics.value = it }
        }
    }

    fun loadTasksForMechanic(mechanicUid: String) {
        viewModelScope.launch {
            _loading.value = true
            jobRepository.getCompletedTasksByMechanic(mechanicUid).onSuccess {
                _tasks.value = it
            }
            _loading.value = false
        }
    }
}
