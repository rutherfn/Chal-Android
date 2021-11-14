package com.nicholasrutherford.chal.profile.edit

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.nicholasrutherford.chal.firebase.realtime.database.create.CreateFirebaseDatabase
import com.nicholasrutherford.chal.firebase.realtime.database.fetch.FetchFirebaseDatabase
import com.nicholasrutherford.chal.profile.R
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

    private val _editProfileInfo = MutableStateFlow(listOf<String>())
    private val editProfileInfo: StateFlow<List<String>> = _editProfileInfo


    val viewState = EditProfileViewStateImpl()

    init {
        viewModelScope.launch {
            editProfileInfo.collect { editProfileInfoList ->
                if (editProfileInfoList.size == 4) {
                    updateEditProfilePage(
                        username = editProfileInfoList[0],
                        firstName = editProfileInfoList[1],
                        lastName = editProfileInfoList[2],
                        bio = editProfileInfoList[3]
                    )
                    setViewStateAsUpdated()
                } else {
                    updateEditProfilePage(
                        username = null,
                        firstName = null,
                        lastName = null,
                        bio = null
                    )
                    setViewStateAsUpdated()
                }
            }
        }
        fetchFirebaseDatabase.fetchEditProfileInfo(_editProfileInfo)
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

        navigation.showPop()
    }

    fun onToolbarBackClicked() = navigation.showPop()

    fun onCancelAndDiscardChanges() = navigation.showPop()

    inner class EditProfileViewStateImpl : EditProfileViewState {
        override var username: String? = null
        override var bio: String? = null
        override var firstName: String? = null
        override var lastName: String? = null
        override var toolbarText: String = application.getString(R.string.edit_profile)
    }
}