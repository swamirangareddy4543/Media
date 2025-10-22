package com.swami.media.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    /**
     * Register: Inserts a new user into the database.
     * @param user The UserEntity to insert (must contain hashed password and salt).
     * @return The row ID of the inserted user.
     */
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertUser(user: UserEntity): Long

    /**
     * Authentication: Retrieves a user by their username for login verification.
     * This is the crucial step before calling AuthUtils.verifyPassword.
     * @param username The username provided by the user.
     * @return The UserEntity if found, or null otherwise.
     */
    @Query("SELECT * FROM users WHERE username = :username LIMIT 1")
    suspend fun getUserByUsername(username: String): UserEntity?

    /**
     * Optional: Get the current authenticated user's ID.
     * We use Flow to observe changes if needed in a more complex scenario.
     */
    @Query("SELECT id FROM users LIMIT 1") // Assuming we fetch the logged-in user's ID
    fun getCurrentUserId(): Flow<Long?>

    /**
     * Check if a username already exists during registration.
     */
    @Query("SELECT EXISTS(SELECT 1 FROM users WHERE username = :username LIMIT 1)")
    suspend fun isUsernameTaken(username: String): Boolean
}
