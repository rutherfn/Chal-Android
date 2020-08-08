package com.nicholasrutherford.chal.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicholasrutherford.chal.helpers.Helper
import com.nicholasrutherford.chal.room.StarterTableEntries
import com.nicholasrutherford.chal.room.helpers.ConfigurationHelper
import com.nicholasrutherford.chal.viewstate.LoginViewState
import kotlinx.coroutines.launch

class LoginViewModel(context: Context) : ViewModel() {

    val viewState = LoginViewStateImpl()
    private val configHelper = ConfigurationHelper(context)

    init {
        setupConfigLogin()
    }

    fun setupConfigLogin() {
        viewModelScope.launch {
            viewState.configurationEntity = configHelper.currentConfigurationRecords()[0]
        }
    }

    fun clickListenerAttemptToVerifyFirebaseUser() {

    }

    fun isEmailErrorVisible() {
        viewState.emailErrorImageVisible = true
        viewState.emailErrorTextVisible = true
    }

    fun isEmailErrorNotVisible() {
        viewState.emailErrorImageVisible = false
        viewState.emailErrorTextVisible = false
    }

    inner class LoginViewStateImpl: LoginViewState {
        override var configurationEntity = StarterTableEntries().configurationStarterEntry
        override var emailErrorImageVisible = false
        override var emailErrorTextVisible = false
    }
}