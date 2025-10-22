package com.swami.media.login

import com.swami.media.room.UserDao
import com.swami.media.room.UserEntity
import com.swami.media.room.AuthUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginRepository @Inject constructor(private val userDao: UserDao) {

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
