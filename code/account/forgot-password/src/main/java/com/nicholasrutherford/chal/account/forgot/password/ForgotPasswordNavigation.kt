package com.nicholasrutherford.chal.account.forgot.password

interface ForgotPasswordNavigation {
    fun showAcProgress()
    fun hideAcProgress()
    fun showForgotPasswordAlert(title: String, message: String)
}