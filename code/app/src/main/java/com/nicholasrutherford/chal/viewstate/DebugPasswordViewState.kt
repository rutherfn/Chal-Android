package com.nicholasrutherford.chal.viewstate

import com.nicholasrutherford.chal.ViewState

interface DebugPasswordViewState : ViewState {
    var userPasswordEntryValue: String
    var debugExistingPasswordValue: String
    var isUserCorrect: Boolean
    var errorDisplayForUserVisible: Boolean
}