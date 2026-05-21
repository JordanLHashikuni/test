package com.valentinesgarage.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valentinesgarage.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    sealed class LoginState {
        object Idle    : LoginState()
        object Loading : LoginState()
        object Success : LoginState()
        data class Error(val message: String) : LoginState()
    }

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState

    /**
     * FIX: login() now only calls Firebase Auth — no Firestore read.
     *
     * The previous version used Result<User> (a Firestore User model),
     * which meant a Firestore read happened inside the login call before
     * the Auth token had fully propagated, causing PERMISSION_DENIED.
     *
     * Now login() returns Result<FirebaseUser> (Auth only). The Firestore
     * profile is fetched later by MainActivity and JobListViewModel after
     * navigation, when the token is guaranteed to be valid.
     *
     * LoginState.Success no longer carries a User object — the fragment
     * only needs to know login succeeded to trigger navigation.
     */
    fun login(email: String, password: String) {
        if (email.isBlank() || password.isBlank()) {
            _loginState.value = LoginState.Error("Email and password are required")
            return
        }
        viewModelScope.launch {
            _loginState.value = LoginState.Loading
            val result = userRepository.login(email, password)
            _loginState.value = result.fold(
                onSuccess = { LoginState.Success },
                onFailure = { LoginState.Error(it.message ?: "Login failed") }
            )
        }
    }

    fun logout() = userRepository.logout()

    val currentUser get() = userRepository.currentFirebaseUser
}
