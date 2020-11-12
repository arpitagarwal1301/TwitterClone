package com.arpit.twitterclone.di.module

import android.app.Application
import com.arpit.twitterclone.data.local.TwitterPostsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class TwitterDatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(application: Application) = TwitterPostsDatabase.getInstance(application)

    @Singleton
    @Provides
    fun providePostsDao(database: TwitterPostsDatabase) = database.getPostsDao()
}
