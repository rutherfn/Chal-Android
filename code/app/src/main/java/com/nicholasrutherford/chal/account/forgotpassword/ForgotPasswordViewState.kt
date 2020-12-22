package com.nicholasrutherford.chal.account.forgotpassword

import com.nicholasrutherford.chal.ViewState

interface ForgotPasswordViewState : ViewState {
    var errorForgotPasswordVisible: Boolean
}