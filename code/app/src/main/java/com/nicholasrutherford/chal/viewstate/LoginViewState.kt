package com.nicholasrutherford.chal.viewstate

import com.nicholasrutherford.chal.room.entity.ConfigurationEntity

interface LoginViewState : ViewState {
    var configurationEntity: ConfigurationEntity
    var emailErrorImageVisible: Boolean
    var emailErrorTextVisible: Boolean
}