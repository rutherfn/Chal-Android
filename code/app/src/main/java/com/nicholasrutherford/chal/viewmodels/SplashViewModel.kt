package com.nicholasrutherford.chal.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.room.StarterTableEntries
import com.nicholasrutherford.chal.room.helpers.ConfigurationHelper
import com.nicholasrutherford.chal.viewstate.SplashViewState
import kotlinx.coroutines.launch

class SplashViewModel(context: Context)  : ViewModel() {

    // declarations
    private val starterTableEntries = StarterTableEntries()
    var viewState = SplashViewModelImpl()
    private val androidDebugHelper = ConfigurationHelper(context)

    init {
        attemptToInitConfigIfUpdated()
    }

    private fun attemptToInitConfigIfUpdated() {
        viewModelScope.launch { androidDebugHelper.initDebugOnSplashIfEmpty(starterTableEntries.configurationStarterEntry) }
    }

    inner class SplashViewModelImpl : SplashViewState {
        override var splashImageRes =  R.drawable.primary_logo
    }

    }