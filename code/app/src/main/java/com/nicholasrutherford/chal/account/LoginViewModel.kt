package com.nicholasrutherford.chal.account

import android.content.Context
import android.widget.TextView
import androidx.lifecycle.ViewModel
import com.nicholasrutherford.chal.helpers.Helper
import com.nicholasrutherford.chal.helpers.Typeface

class LoginViewModel(private val loginActivity: LoginActivity, val appContext: Context) : ViewModel() {

    val viewState = LoginViewStateImpl()
    private val navigation = LoginNavigationImpl()
    private val typeface = Typeface()
    private val helper = Helper()

    fun updateEmailAfterTextChanged(email: String) {
        if(email.contains("@") && email.contains(".com")) {
            emailErrorNotVisible()
        } else if(email == "") {
            emailErrorNotVisible()
        } else {
            emailErrorVisible()
        }
    }

    fun emailErrorVisible() {
        viewState.emailErrorImageVisible = true
        viewState.emailErrorTextVisible = true
    }

    fun emailErrorNotVisible() {
        viewState.emailErrorImageVisible = false
        viewState.emailErrorTextVisible = false
    }

    fun onLogInClicked() {
        helper.hideSoftKeyBoard(loginActivity)
    }

    fun onSignUpClicked() {
        navigation.signUp(appContext, loginActivity)
    }

    fun onForgotPasswordClicked() {
        navigation.forgotPassword(appContext, loginActivity)
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

    inner class LoginViewStateImpl: LoginViewState {
        override var emailErrorImageVisible = false
        override var emailErrorTextVisible = false
    }
}