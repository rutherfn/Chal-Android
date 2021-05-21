package com.nicholasrutherford.chal.more

import com.nicholasrutherford.chal.ViewState

interface MoreViewState : ViewState {
    var alertTitle: String
    var alertMessage: String
    var profilePictureDirectory: String
}