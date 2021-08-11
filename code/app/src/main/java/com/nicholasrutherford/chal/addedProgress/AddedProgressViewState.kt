package com.nicholasrutherford.chal.addedProgress

import com.nicholasrutherford.chal.ViewState

interface AddedProgressViewState : ViewState {
    var toolbarBackButtonVisible: Boolean
    var toolbarTitle: String
}