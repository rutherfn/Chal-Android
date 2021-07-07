package com.nicholasrutherford.chal.main.splash

import android.app.Application
import android.content.Intent
import javax.inject.Inject

class SplashNavigationImpl @Inject constructor(private val application: Application, private val splashActivity: SplashActivity): SplashNavigation {

    override fun login() {
        println("login here")
    }

    override fun newsFeed() {
    }
}