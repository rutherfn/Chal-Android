package com.nicholasrutherford.chal.profile

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.nicholasrutherford.chal.firebase.realtime.database.fetch.FetchFirebaseDatabase
import com.nicholasrutherford.chal.ui.base_vm.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ProfileViewModel @ViewModelInject constructor(
    private val application: Application,
    private val navigation: ProfileNavigation,
    private val fetchFirebaseDatabase: FetchFirebaseDatabase
) : BaseViewModel() {

    private val _profileInfo = MutableStateFlow(listOf<String>())
    private val profileInfo: StateFlow<List<String>> = _profileInfo

    val viewState = ProfileViewStateImpl()

    init {
        viewModelScope.launch {
            profileInfo.collect { profileInfoList ->
                if (profileInfoList.size == 4) {
                    updateProfilePage(
                        age = profileInfoList[0].toInt(),
                        description = profileInfoList[1],
                        username = profileInfoList[2],
                        profileImage = profileInfoList[3]
                    )
                } else {
                    updateProfilePage(
                        age = null,
                        description = "",
                        username = "",
                        profileImage = ""
                    )
                }
            }
        }
        fetchFirebaseDatabase.fetchProfileInfo(_profileInfo, true)
    }

    private fun updateProfilePage(age: Int?, description: String, username: String, profileImage: String) {
        viewState.age = age

        if (description.isEmpty()) {
            viewState.description = application.getString(R.string.click_here_to_add_a_description)
        } else {
            viewState.description = description
        }
        viewState.username = username
        viewState.profileImage = profileImage

        setViewStateAsUpdated()
    }

    fun onToolbarBackClicked() = navigation.showPop()

    fun onEditProfileClicked() = navigation.showEditProfile()

    inner class ProfileViewStateImpl: ProfileViewState {
        override var age: Int? = 0
        override var description: String? = ""
        override var username: String? = ""
        override var profileImage: String? = ""
        override var myChallengesTabActive = false
        override var myFriendsTabActive = false
    }
}