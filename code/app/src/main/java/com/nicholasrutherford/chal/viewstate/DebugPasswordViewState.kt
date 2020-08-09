package com.nicholasrutherford.chal.viewstate

interface DebugPasswordViewState : ViewState {
    var userPasswordEntryValue: String
    var debugExistingPasswordValue: String
    var isUserCorrect: Boolean
    var errorDisplayForUserVisible: Boolean
}