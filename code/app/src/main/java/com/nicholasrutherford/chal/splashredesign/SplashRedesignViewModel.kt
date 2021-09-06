package com.nicholasrutherford.chal.splashredesign

import android.os.Handler
import android.os.Looper
import androidx.hilt.lifecycle.ViewModelInject
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.firebase.auth.ChalFirebaseAuth
import com.nicholasrutherford.chal.navigationimpl.splash.SplashRedesignNavigationImpl
import com.nicholasrutherford.chal.ui.base_vm.BaseViewModel

@Suppress("MagicNumber")
const val SPLASH_DELAYED = 5000

class SplashRedesignViewModel @ViewModelInject constructor(
    private val firebaseAuth: ChalFirebaseAuth,
    private val navigation: SplashRedesignNavigationImpl
) : BaseViewModel() {

    var viewState = SplashRedesignViewStateImpl()

    fun checkIfUserIsSignedIn() {
        Handler(Looper.getMainLooper()).postDelayed({
            if (!firebaseAuth.isLoggedIn) {
                navigation.showlogin()
            } else {
                navigation.showlogin()
            }
        }, SPLASH_DELAYED.toLong())
    }

    inner class SplashRedesignViewStateImpl : SplashRedesignViewState {
            override var splashImageRes: Int = R.mipmap.chalappicon
        }
}