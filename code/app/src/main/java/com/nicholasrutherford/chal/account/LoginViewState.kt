package com.nicholasrutherford.chal.account

import com.nicholasrutherford.chal.viewstate.ViewState
import com.nicholasrutherford.chal.room.entity.ConfigurationEntity

interface LoginViewState : ViewState {
    var configurationEntity: ConfigurationEntity
    var emailErrorImageVisible: Boolean
    var emailErrorTextVisible: Boolean
}