package com.arpit.twitterclone

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class TwitterCloneApp : Application(){

    override fun onCreate() {
        super.onCreate()
    }
}