package com.nicholasrutherford.chal.account.login

import com.nicholasrutherford.chal.viewstate.ViewState
import com.nicholasrutherford.chal.room.entity.ConfigurationEntity

interface LoginViewState : ViewState {
    var emailErrorImageVisible: Boolean
    var emailErrorTextVisible: Boolean
}