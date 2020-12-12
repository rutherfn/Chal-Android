package com.nicholasrutherford.chal.splash

import android.content.Context
import androidx.lifecycle.ViewModel
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.navigationimpl.splash.SplashNavigationImpl
import android.os.Handler
import androidx.lifecycle.viewModelScope
import com.nicholasrutherford.chal.ChalRoom
import com.nicholasrutherford.chal.room.helpers.FirebaseKeysViewModelHelper

class SplashViewModel(context: Context, private val activity: SplashActivity)  : ViewModel() {
    private val navigation = SplashNavigationImpl()
    var viewState = SplashViewModelImpl()

    init {
        val firebaseKeysHelper = FirebaseKeysViewModelHelper(
            application = activity.application,
            viewModelScope = viewModelScope
        )
        val chalRoom = ChalRoom(activity.application)
        firebaseKeysHelper.fetchLatestKeys(chalRoom)

        checkIfUserIsSignedIn(firebaseKeysHelper)
    }

    private fun checkIfUserIsSignedIn(firebaseKeysViewModelHelper: FirebaseKeysViewModelHelper) {
        val handler = Handler()
        handler.postDelayed({
            val user = firebaseKeysViewModelHelper.mAuth?.currentUser ?: null

            if (user == null) {
                navigation.login(activity)
            } else {
                navigation.home(activity)
            }
        }, 5000)
    }

    inner class SplashViewModelImpl : SplashViewState {
        override var splashImageRes: Int = R.mipmap.chalappicon
    }

}