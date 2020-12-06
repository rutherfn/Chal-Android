package com.nicholasrutherford.chal.account.login

import com.nicholasrutherford.chal.ViewState

interface LoginViewState : ViewState {
    var emailErrorImageVisible: Boolean
    var emailErrorTextVisible: Boolean
}