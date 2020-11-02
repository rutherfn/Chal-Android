package com.nicholasrutherford.chal.screens.home

import com.nicholasrutherford.chal.room.entity.ConfigurationEntity
import com.nicholasrutherford.chal.viewstate.ViewState

interface HomeViewState  : ViewState {
    var configurationEntity: ConfigurationEntity
    val isWallVisible: Boolean
}