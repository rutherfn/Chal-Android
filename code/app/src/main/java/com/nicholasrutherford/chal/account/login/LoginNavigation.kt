package com.nicholasrutherford.chal.account.login

import android.content.Context

interface LoginNavigation {
    fun signUp(context: Context, loginActivity: LoginActivity)
    fun forgotPassword(context: Context, loginActivity: LoginActivity)
    fun errorLogin(errorMessageText: String, loginActivity: LoginActivity)
    fun showAcProgress(loginActivity: LoginActivity)
    fun hideAcProgress()
    fun loginToApp(loginActivity: LoginActivity)
}