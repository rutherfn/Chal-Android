package com.nicholasrutherford.chal.navigationimpl.splash

import android.content.Intent
import com.nicholasrutherford.chal.account.login.LoginActivity
import com.nicholasrutherford.chal.MainActivity
import com.nicholasrutherford.chal.splash.SplashActivity
import com.nicholasrutherford.chal.splash.SplashNavigation

class SplashNavigationImpl : SplashNavigation {

    override fun home(splashActivity: SplashActivity) {
        val intent = Intent(splashActivity.applicationContext, MainActivity::class.java)

        splashActivity.startActivity(intent)
        splashActivity.finish()
    }

    override fun login(splashActivity: SplashActivity) {
        val intent = Intent(splashActivity.applicationContext, LoginActivity::class.java)

        splashActivity.startActivity(intent)
        splashActivity.finish()
    }
}