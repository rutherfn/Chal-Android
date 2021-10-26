package com.nicholasrutherford.chal.profile

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import com.nicholasrutherford.chal.ui.base_vm.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ProfileViewModel @ViewModelInject constructor(
    private val application: Application
) : BaseViewModel() {

    private val _profileInfo = MutableStateFlow(listOf<String>())
    private val profileInfo: StateFlow<List<String>> = _profileInfo

    val viewState = ProfileViewStateImpl()

    init {

    }

    inner class ProfileViewStateImpl: ProfileViewState {
        override var age: Int? = 0
        override var description: String? = ""
        override var username: String? = ""
        override var profileImage: String? = ""
        override var myChallengesTabActive = false
        override var myFriendsTabActive = false
    }
}