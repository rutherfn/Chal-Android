package com.nicholasrutherford.chal.splashredesign

import android.app.Application
import android.os.Handler
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.navigationimpl.splash.SplashRedesignNavigationImpl
import javax.inject.Inject

class SplashRedesignViewModel @Inject constructor(
    private val application: Application,
    private val navigation: SplashRedesignNavigationImpl
    ) : ViewModel() {

    var viewState = SplashRedesignViewStateImpl()

    fun checkIfUserIsSignedIn() {
        val handler = Handler()
        handler.postDelayed(
            {
                if (FirebaseAuth.getInstance().currentUser == null) {
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