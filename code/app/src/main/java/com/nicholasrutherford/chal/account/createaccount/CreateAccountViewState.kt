package com.nicholasrutherford.chal.account.createaccount

import com.nicholasrutherford.chal.ViewState

interface CreateAccountViewState : ViewState {
    var usernameErrorVisible: Boolean
    var emailErrorVisible: Boolean
    var passwordErrorVisible: Boolean
}