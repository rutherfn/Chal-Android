package com.nicholasrutherford.chal.viewmodels

import androidx.lifecycle.ViewModel
import com.nicholasrutherford.chal.viewstate.MyProfileViewState

class MyProfileViewModel() : ViewModel() {

    // declarations
    val viewState = MyProfileViewStateImpl()

    init {

    }

    fun onclickChallenges() {

    }

    inner class MyProfileViewStateImpl() : MyProfileViewState {
        override val profileVisible = false
    }
}