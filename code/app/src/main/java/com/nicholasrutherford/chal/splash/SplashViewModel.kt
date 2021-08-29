package com.nicholasrutherford.chal.splash

import android.content.Context
import android.os.Handler
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.nicholasrutherford.chal.ChallengeCalenderDay
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.navigationimpl.splash.SplashNavigationImpl

class SplashViewModel(context: Context, private val activity: SplashActivity) : ViewModel() {

    private val navigation = SplashNavigationImpl()
    var viewState = SplashViewModelImpl()

    private val test = ChallengeCalenderDay()

    init {
        checkIfUserIsSignedIn()
    }

    private fun checkIfUserIsSignedIn() {
        val handler = Handler()
        handler.postDelayed(
            {
                if (FirebaseAuth.getInstance().currentUser == null) {
                    navigation.home(activity) // change this later for real functionality
                } else {
                    navigation.home(activity)
                }
            },
            5000
        )
    }

    inner class SplashViewModelImpl : SplashViewState {
        override var splashImageRes: Int = R.mipmap.chalappicon
    }
}
