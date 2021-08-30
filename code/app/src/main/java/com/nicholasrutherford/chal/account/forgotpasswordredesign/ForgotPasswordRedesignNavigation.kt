package com.nicholasrutherford.chal.account.forgotpasswordredesign

import com.nicholasrutherford.chal.Navigation

interface ForgotPasswordRedesignNavigation : Navigation {
    fun showAcProgress()
    fun forgotPasswordAlert(title: String, message: String)
    fun hideAcProgress()
    fun showLogin()
}