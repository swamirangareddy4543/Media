package com.swami.media.core.di


import android.content.Context
import com.swami.media.feed.FeedRepository
import com.swami.media.room.AppDatabase
import com.swami.media.room.UserDao
import com.swami.media.login.LoginRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCoroutineScope(): CoroutineScope = CoroutineScope(SupervisorJob())

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
        scope: CoroutineScope
    ): AppDatabase {
        return AppDatabase.getDatabase(context, scope)
    }

    @Provides
    fun provideUserDao(database: AppDatabase): UserDao = database.userDao()

    @Provides
    @Singleton
    fun provideLoginRepository(userDao: UserDao): LoginRepository {
        return LoginRepository(userDao)
    }

    @Provides
    @Singleton
    fun provideFeedRepository(): FeedRepository {
        return FeedRepository()
    }


}
