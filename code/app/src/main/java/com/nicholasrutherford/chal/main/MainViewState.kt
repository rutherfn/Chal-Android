package com.nicholasrutherford.chal.main

import com.nicholasrutherford.chal.Screens
import com.nicholasrutherford.chal.ViewState

interface MainViewState : ViewState {
    var currentScreen: Screens?
}