package com.swami.media.login

import com.swami.media.room.UserDao
import com.swami.media.room.UserEntity
import com.swami.media.room.AuthUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginRepository @Inject constructor(private val userDao: UserDao) {

    /**
     * Attempts to register a new user.
     * @param username The user's chosen username.
     * @param plainPassword The user's plain text password.
     * @return Result of the registration: Long (new user ID) or null on failure.
     */
    suspend fun registerUser(username: String, plainPassword: String): Long? = withContext(Dispatchers.IO) {
        if (userDao.isUsernameTaken(username)) {
            return@withContext null // Username already exists
        }

        val salt = AuthUtils.generateSalt()
        val passwordHash = AuthUtils.hashPassword(plainPassword, salt)

        val newUser = UserEntity(
            username = username,
            passwordHash = passwordHash,
            salt = salt,
            createdAt = System.currentTimeMillis()
        )

        return@withContext try {
            userDao.insertUser(newUser)
        } catch (e: Exception) {
            null
        }
    }

    /**
     * Attempts to log in a user by verifying credentials.
     * @param username The user's input username.
     * @param plainPassword The user's input plain text password.
     * @return The UserEntity if login is successful, or null on failure.
     */
    suspend fun loginUser(username: String, plainPassword: String): UserEntity? = withContext(Dispatchers.IO) {
        val storedUser = userDao.getUserByUsername(username) ?: return@withContext null
        val isPasswordCorrect = AuthUtils.verifyPassword(
            plainPassword,
            storedUser.passwordHash,
            storedUser.salt
        )
        return@withContext if (isPasswordCorrect) storedUser else null
    }

}
