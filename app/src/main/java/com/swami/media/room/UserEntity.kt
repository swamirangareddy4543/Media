package com.swami.media.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "username")
    val username: String,
    @ColumnInfo(name = "password_hash")
    val passwordHash: String,
    @ColumnInfo(name = "salt")
    val salt: String,
    @ColumnInfo(name = "created_at")
    val createdAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "feed_items")
data class FeedItemEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    @ColumnInfo(name = "short_description")
    val shortDescription: String,
    @ColumnInfo(name = "full_description")
    val fullDescription: String
)

@Entity(tableName = "media_references", primaryKeys = ["feed_item_id", "position"])
data class MediaRef(
    @ColumnInfo(name = "feed_item_id")
    val feedItemId: Long,
    val type: String,
    val uri: String,
    val position: Int
)