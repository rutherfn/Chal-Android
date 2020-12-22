package com.nicholasrutherford.chal.account.forgotpassword

import android.content.Context
import com.nicholasrutherford.chal.Navigation

interface ForgotPasswordNavigation : Navigation {
    fun showAcProgress(forgotPasswordActivity: ForgotPasswordActivity)
    fun forgotPasswordAlert(title: String, message: String, forgotPasswordActivity: ForgotPasswordActivity)
    fun hideAcProgress()
    fun login(context: Context, forgotPasswordActivity: ForgotPasswordActivity)
}