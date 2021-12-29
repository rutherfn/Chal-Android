package com.nicholasrutherford.chal.profile.edit

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.nicholasrutherford.chal.firebase.realtime.database.create.CreateFirebaseDatabase
import com.nicholasrutherford.chal.firebase.realtime.database.fetch.FetchFirebaseDatabase
import com.nicholasrutherford.chal.profile.R
import com.nicholasrutherford.chal.data.account.info.ProfileInfo
import com.nicholasrutherford.chal.ui.base_vm.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class EditProfileViewModel @ViewModelInject constructor(
    private val navigation: EditProfileNavigation,
    private val createFirebaseDatabase: CreateFirebaseDatabase,
    private val fetchFirebaseDatabase: FetchFirebaseDatabase,
    private val application: Application
) : BaseViewModel() {

    private val _profileInfo = MutableStateFlow(ProfileInfo())
    private val profileInfo: StateFlow<ProfileInfo> = _profileInfo


    val viewState = EditProfileViewStateImpl()

    init {
        viewModelScope.launch {
            profileInfo.collect { info ->
                updateEditProfilePage(
                    username = info.username,
                    firstName = info.firstName,
                    lastName = info.lastName,
                    bio = info.description
                )
                setViewStateAsUpdated()
            }
        }
        fetchFirebaseDatabase.fetchProfileInfo(_profileInfo, true)
    }

    fun updateEditProfilePage(username: String?, firstName: String?, lastName: String?, bio: String?) {
        viewState.username = username
        viewState.bio = bio
        viewState.firstName = firstName
        viewState.lastName = lastName
    }

    fun onEditProfileClicked(username: String, firstName: String, lastName: String, bio: String) {
        updateEditProfilePage(username, firstName, lastName, bio)
        setShouldShowProgressAsUpdated()

        createFirebaseDatabase.createAccountinfo(username, firstName, lastName, bio)
        setShouldShowDismissProgressAsUpdated()

        navigation.navigateBack()
    }

    fun onToolbarBackClicked() = navigation.navigateBack()

    fun onCancelAndDiscardChanges() = navigation.navigateBack()

    inner class EditProfileViewStateImpl : EditProfileViewState {
        override var username: String? = null
        override var bio: String? = null
        override var firstName: String? = null
        override var lastName: String? = null
        override var toolbarText: String = application.getString(R.string.edit_profile)
        override var profileImageHeaderBackground: String = application.getString(R.string.placeholder_image)
    }
}