package com.nicholasrutherford.chal.account

import android.content.Context

interface LoginNavigation {
    fun signUp(context: Context, loginActivity: LoginActivity)
    fun forgotPassword(context: Context, loginActivity: LoginActivity)
}