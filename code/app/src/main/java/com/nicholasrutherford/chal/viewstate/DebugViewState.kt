package com.nicholasrutherford.chal.viewstate

import com.nicholasrutherford.chal.data.debug.Debug

interface DebugViewState : ViewState {

    val debugModeVisible: Boolean
    val debugOptionsList: ArrayList<Debug>
}