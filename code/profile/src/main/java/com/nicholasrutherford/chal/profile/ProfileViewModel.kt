package com.nicholasrutherford.chal.profile

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.nicholasrutherford.chal.data.account.info.ProfileInfo
import com.nicholasrutherford.chal.firebase.realtime.database.fetch.FetchFirebaseDatabase
import com.nicholasrutherford.chal.ui.base_vm.BaseViewModel
import con.nicholasrutherford.chal.data.challenges.ActiveChallengesListResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ProfileViewModel @ViewModelInject constructor(
    private val application: Application,
    private val navigation: ProfileNavigation,
    private val fetchFirebaseDatabase: FetchFirebaseDatabase
) : BaseViewModel() {

    private val _profileInfo = MutableStateFlow(ProfileInfo())
    private val profileInfo: StateFlow<ProfileInfo> = _profileInfo

    private val _allUserActiveChallengesList = MutableStateFlow(listOf<ActiveChallengesListResponse>())
    val allUserActiveChallengesList: StateFlow<List<ActiveChallengesListResponse>> = _allUserActiveChallengesList

    val viewState = ProfileViewStateImpl()

    init {
        viewModelScope.launch {
            profileInfo.collect { info ->
                updateProfilePage(
                    age = info.age,
                    description = info.description,
                    username = info.username,
                    profileImage = info.profileImage
                )
            }
        }
        fetchAllUserActiveChallenges()
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

    fun updateActiveChallengesSize(count: Int) {
        viewState.activeChallengesSize = count

        setViewStateAsUpdated()
    }

    fun onItemClicked(index: Int) {
        val newIndex = index.toString()

        navigation.showProfileChallengePosts(newIndex)
    }

    private fun fetchAllUserActiveChallenges() = fetchFirebaseDatabase.fetchAllUserActiveChallenges(_allUserActiveChallengesList)

    fun onToolbarBackClicked() = navigation.showPop()

    fun onEditProfileClicked() = navigation.showEditProfile()

    inner class ProfileViewStateImpl: ProfileViewState {
        override var toolbarText: String = application.getString(R.string.my_profile)
        override var profileImageHeaderBackground: String = application.getString(R.string.placeholder_image)
        override var age: Int? = application.getString(R.string.zero).toInt()
        override var description: String? = application.getString(R.string.empty_string)
        override var username: String? = application.getString(R.string.empty_string)
        override var profileImage: String? = application.getString(R.string.empty_string)
        override var myChallengesTabActive = false
        override var myFriendsTabActive = false
        override var activeChallengesSize: Int? = null
    }
}