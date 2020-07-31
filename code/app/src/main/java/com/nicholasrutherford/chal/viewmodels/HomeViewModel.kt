package com.nicholasrutherford.chal.viewmodels

import androidx.lifecycle.ViewModel
import com.nicholasrutherford.chal.viewstate.HomeViewState

class HomeViewModel : ViewModel() {

    // declarations
    val viewState = HomeViewStateImpl()

    init {

    }

    inner class HomeViewStateImpl() : HomeViewState {
        override val isWallVisible = true
    }
}