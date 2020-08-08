package com.nicholasrutherford.chal.viewstate

import com.nicholasrutherford.chal.room.entity.ConfigurationEntity

interface HomeViewState  : ViewState {
    var configurationEntity: ConfigurationEntity
    val isWallVisible: Boolean
}