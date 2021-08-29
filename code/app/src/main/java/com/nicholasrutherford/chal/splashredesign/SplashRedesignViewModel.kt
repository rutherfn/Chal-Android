package com.nicholasrutherford.chal.splashredesign

import android.os.Handler
import androidx.lifecycle.ViewModel
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.firebase.auth.ChalFirebaseAuthImpl
import com.nicholasrutherford.chal.navigationimpl.splash.SplashRedesignNavigationImpl
import javax.inject.Inject

class SplashRedesignViewModel @Inject constructor(
    private val firebaseAuth: ChalFirebaseAuthImpl,
    private val navigation: SplashRedesignNavigationImpl
) : ViewModel() {

    var viewState = SplashRedesignViewStateImpl()

    fun checkIfUserIsSignedIn() {
        val handler = Handler()
        handler.postDelayed(
            {
                if (!firebaseAuth.isLoggedIn) {
                    navigation.showlogin()
                } else {
                    navigation.showHome()
                }
            },
            5000
        )
    }

    inner class SplashRedesignViewStateImpl : SplashRedesignViewState {
            override var splashImageRes: Int = R.mipmap.chalappicon
        }
}