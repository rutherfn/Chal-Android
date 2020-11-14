package com.nicholasrutherford.chal.account

import android.content.Context
import android.content.Intent
import com.nicholasrutherford.chal.activitys.accounts.ForgotPasswordActivity
import com.nicholasrutherford.chal.activitys.accounts.SignUpActivity

class LoginNavigationImpl : LoginNavigation {

    override fun signUp(context: Context, loginActivity: LoginActivity) {
        val intent = Intent(context, SignUpActivity::class.java)

        loginActivity.startActivity(intent)
        loginActivity.finish()
    }

    override fun forgotPassword(context: Context, loginActivity: LoginActivity) {
        val intent = Intent(context, ForgotPasswordActivity::class.java)

        loginActivity.startActivity(intent)
        loginActivity.finish()
    }

}