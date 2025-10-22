package com.swami.media.feed

import com.swami.media.data.FeedItemData
import com.swami.media.data.getMockFeedItems
import kotlinx.coroutines.delay
import javax.inject.Inject

class FeedRepository @Inject constructor(){
    suspend fun getAllFeedItems(): Result<List<FeedItemData>> {
        return try {
            delay(1000)
            Result.success(getMockFeedItems())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getFeedItemById(itemId: Long): FeedItemData? {
        delay(500)
        return getMockFeedItems().find { it.id == itemId }
    }
}