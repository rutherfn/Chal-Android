package com.nicholasrutherford.chal.main.splash

import android.os.Handler
import android.os.Looper
import androidx.hilt.lifecycle.ViewModelInject
import com.nicholasrutherford.chal.firebase.auth.ChalFirebaseAuth
import com.nicholasrutherford.chal.ui.base_vm.BaseViewModel

@Suppress("MagicNumber")
const val SPLASH_DELAYED = 5000

class SplashViewModel @ViewModelInject constructor(
    private val firebaseAuth: ChalFirebaseAuth,
    private val navigation: SplashNavigation
) : BaseViewModel() {

    var viewState = SplashRedesignViewStateImpl()


    fun checkIfUserIsSignedIn() {
        Handler(Looper.getMainLooper()).postDelayed({
            if (!firebaseAuth.isLoggedIn) {
                navigation.showlogin()
            } else {
                navigation.showlogin() // should be showNewsFeed()nn
            }
        }, SPLASH_DELAYED.toLong())
    }

    inner class SplashRedesignViewStateImpl : SplashRedesignViewState {
        override var splashImageRes: Int = R.mipmap.chalappicon
    }
}