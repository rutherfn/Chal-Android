package com.nicholasrutherford.chal.splash

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.navigationimpl.SplashNavigationImpl
import com.nicholasrutherford.chal.room.StarterTableEntries
import com.nicholasrutherford.chal.room.helpers.ConfigurationHelper
import com.nicholasrutherford.chal.viewstate.SplashViewState
import kotlinx.coroutines.launch
import android.os.Handler

class SplashViewModel(context: Context, private val activity: SplashActivity)  : ViewModel() {

    private var mAuth: FirebaseAuth? = null
    private val starterTableEntries = StarterTableEntries()
    private val navigation = SplashNavigationImpl()
    var viewState = SplashViewModelImpl()
    private val androidDebugHelper = ConfigurationHelper(context)

    init {
        mAuth = FirebaseAuth.getInstance()
        attemptToInitConfigIfUpdated()
        checkIfUserIsSignedIn()
    }

    private fun attemptToInitConfigIfUpdated() {
        viewModelScope.launch { androidDebugHelper.initDebugOnSplashIfEmpty(starterTableEntries.configurationStarterEntry) }
    }

    private fun checkIfUserIsSignedIn() {
        val handler = Handler()
        handler.postDelayed({
            val user = mAuth!!.currentUser

            if(user == null) {
                navigation.login(activity)
            } else {
                navigation.home(activity)
            }
        }, 5000)
    }

    inner class SplashViewModelImpl : SplashViewState {
        override var splashImageRes: Int =  R.mipmap.chalappicon
    }

    }