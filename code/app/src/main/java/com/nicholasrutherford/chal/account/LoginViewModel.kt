package com.nicholasrutherford.chal.account

import android.content.Context
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicholasrutherford.chal.helpers.Typeface
import com.nicholasrutherford.chal.room.StarterTableEntries
import com.nicholasrutherford.chal.room.helpers.ConfigurationHelper
import kotlinx.coroutines.launch

class LoginViewModel(context: Context) : ViewModel() {

    private val viewState = LoginViewStateImpl()
    private val configHelper = ConfigurationHelper(context)
    private val typeface = Typeface()

    init {
        setupConfigLogin()
    }

    private fun setupConfigLogin() {
        viewModelScope.launch {
            viewState.configurationEntity = configHelper.currentConfigurationRecords()[0]
        }
    }

    fun headerBold(textView: TextView, context: Context) {
        typeface.setTypefaceForHeaderBold(textView, context)
    }

    fun bodyBold(textView: TextView, context: Context) {
        typeface.setTypefaceForBodyBold(textView, context)
    }

    fun bodyItalic(textView: TextView, context: Context) {
        typeface.setTypefaceForBodyItalic(textView, context)
    }

    fun lightBody(textView: TextView, context: Context) {
        typeface.setTypefaceForLightBody(textView, context)
    }

    fun subHeaderBold(textView: TextView, context: Context) {
        typeface.setTypefaceForSubHeaderBold(textView, context)
    }

    fun onAttemptToVerifyUser() {

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