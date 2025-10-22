package com.swami.media.core

import com.swami.media.room.UserEntity

/**
 * Sealed class to represent the different states of the Login/Registration UI.
 */
sealed class AuthState {
    object Idle : AuthState()
    object Loading : AuthState()
    data class Success(val user: User) : AuthState()
    data class Error(val message: String) : AuthState()
}
