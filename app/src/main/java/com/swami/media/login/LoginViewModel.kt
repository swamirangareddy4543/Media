package com.swami.media.login

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swami.media.core.AuthState
import com.swami.media.core.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: LoginRepository,
    private val app: Application
) : ViewModel() {

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState

    private val prefs = app.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)

    init {
        // Auto-login check
        val savedUsername = prefs.getString("username", null)
        if (savedUsername != null) {
            _authState.value = AuthState.Success(User(savedUsername))
        }
    }

    fun login(username: String, password: String) {
        if (username.isBlank() || password.isBlank()) {
            _authState.value = AuthState.Error("Username and password cannot be empty.")
            return
        }

        _authState.value = AuthState.Loading
        viewModelScope.launch {
            val userEntity = repository.loginUser(username, password)
            if (userEntity != null) {
                val user = User(userEntity.username) // ✅ convert to UI model
                saveUserSession(user.username)
                _authState.value = AuthState.Success(user)
            } else {
                _authState.value = AuthState.Error("Login failed. Invalid credentials.")
            }
        }
    }

    fun register(username: String, password: String) {
        if (username.isBlank() || password.isBlank() || password.length < 6) {
            _authState.value =
                AuthState.Error("Registration failed. Password must be at least 6 characters.")
            return
        }

        _authState.value = AuthState.Loading
        viewModelScope.launch {
            val userId = repository.registerUser(username, password)
            if (userId != null) {
                saveUserSession(username)
                _authState.value = AuthState.Success(User(username))
            } else {
                _authState.value = AuthState.Error("Registration failed. Username may be taken.")
            }
        }
    }

    fun logout() {
        prefs.edit().clear().apply()
        _authState.value = AuthState.Idle
    }

    fun resetState() {
        _authState.value = AuthState.Idle
    }

    private fun saveUserSession(username: String) {
        prefs.edit()
            .putString("username", username)
            .putBoolean("isLoggedIn", true)
            .apply()
    }

    fun isUserLoggedIn(): Boolean {
        return prefs.getBoolean("isLoggedIn", false)
    }
}
