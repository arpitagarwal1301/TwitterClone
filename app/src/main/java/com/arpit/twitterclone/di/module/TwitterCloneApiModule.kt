package com.arpit.twitterclone.di.module

import com.arpit.twitterclone.data.remoteapi.TwitterCloneService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class TwitterCloneApiModule {

    @Singleton
    @Provides
    fun provideRetrofitService(): TwitterCloneService = Retrofit.Builder()
        .baseUrl(TwitterCloneService.TWITTER_API_URL)
        .addConverterFactory(
            MoshiConverterFactory.create(
                Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            )
        )
        .build()
        .create(TwitterCloneService::class.java)
}
