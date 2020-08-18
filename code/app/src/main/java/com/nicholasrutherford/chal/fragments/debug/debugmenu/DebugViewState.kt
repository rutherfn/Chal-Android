package com.nicholasrutherford.chal.fragments.debug.debugmenu

import com.nicholasrutherford.chal.data.debug.Debug
import com.nicholasrutherford.chal.viewstate.ViewState

interface DebugViewState : ViewState {
    val isChangeConfigurationsClicked: Boolean
    val container: Int
    val buttonRestartChangesValue: String
    val debugModeVisible: Boolean
    val debugOptionsList: ArrayList<Debug>
}