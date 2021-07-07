package com.nicholasrutherford.chal.main.splash

import android.app.Application
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import android.os.Handler
import android.os.Looper
import javax.inject.Inject

@Suppress("MagicNumber")
const val SPLASH_DURATION = 5000

class SplashViewModel @Inject constructor(private val application: Application, splashActivity: SplashActivity) : ViewModel() {

    private val auth = FirebaseAuth.getInstance()

    init {

    }

    private fun test() {
        Handler(Looper.getMainLooper()).postDelayed(
            {
                if (auth.currentUser == null) {
                    /// navigation.login
                } else {
                    // navigation home
                }
            // Your Code
        }, SPLASH_DURATION.toLong())
    }
}