package com.nicholasrutherford.chal.navigationimpl.splash

import android.content.Intent
import com.nicholasrutherford.chal.account.login.LoginActivity
import com.nicholasrutherford.chal.main.MainActivity
import com.nicholasrutherford.chal.splashredesign.SplashRedesignNavigation
import javax.inject.Inject

class SplashRedesignNavigationImpl @Inject constructor(private val main: MainActivity):
    SplashRedesignNavigation {

    override fun showHome() {
        val intent = Intent(main.applicationContext, MainActivity::class.java)

        main.startActivity(intent)
        main.finish()
    }

    override fun showlogin() {
        val intent = Intent(main.applicationContext, LoginActivity::class.java)

        main.startActivity(intent)
        main.finish()
    }
}