package com.nicholasrutherford.chal.challenge.manager.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ChallengeManager : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}