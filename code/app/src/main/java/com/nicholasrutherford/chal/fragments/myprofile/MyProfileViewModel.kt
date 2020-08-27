package com.nicholasrutherford.chal.fragments.myprofile

import androidx.lifecycle.ViewModel

class MyProfileViewModel() : ViewModel() {

    // declarations
    val viewState = MyProfileViewStateImpl()

    init {

    }

    fun onclickChallenges() {

    }

    inner class MyProfileViewStateImpl() : MyProfileViewState { override val profileVisible = false }
}