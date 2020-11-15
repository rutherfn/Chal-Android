package com.nicholasrutherford.chal.account.createaccount

import com.nicholasrutherford.chal.viewstate.ViewState

interface CreateAccountViewState : ViewState {
    var usernameErrorVisible: Boolean
    var emailErrorVisible: Boolean
    var passwordErrorVisible: Boolean
}