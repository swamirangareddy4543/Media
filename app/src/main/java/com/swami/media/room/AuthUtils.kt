package com.swami.media.room

import java.security.MessageDigest
import java.security.SecureRandom
import android.util.Base64

object AuthUtils {

    private const val HASH_ALGORITHM = "SHA-256"
    private const val SALT_LENGTH = 16

    fun generateSalt(): String {
        val random = SecureRandom()
        val salt = ByteArray(SALT_LENGTH)
        random.nextBytes(salt)
        return Base64.encodeToString(salt, Base64.NO_WRAP)
    }

    fun hashPassword(password: String, salt: String): String {
        return try {
            val digest = MessageDigest.getInstance(HASH_ALGORITHM)
            val combinedBytes = password.toByteArray() + Base64.decode(salt, Base64.NO_WRAP)
            val hash = digest.digest(combinedBytes)
            Base64.encodeToString(hash, Base64.NO_WRAP)
        } catch (e: Exception) {
            throw RuntimeException("Error hashing password", e)
        }
    }

    fun verifyPassword(password: String, storedHash: String, storedSalt: String): Boolean {
        val calculatedHash = hashPassword(password, storedSalt)
        return calculatedHash == storedHash
    }
}
