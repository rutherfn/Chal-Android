package com.nicholasrutherford.chal.viewstate

import com.nicholasrutherford.chal.data.debug.Debug

interface DebugViewState : ViewState {
    val isChangeConfigurationsClicked: Boolean
    val container: Int
    val buttonRestartChangesValue: String
    val debugModeVisible: Boolean
    val debugOptionsList: ArrayList<Debug>
}