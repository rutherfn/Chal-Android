package com.nicholarutherford.chal.more

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.nicholasrutherford.chal.firebase.auth.ChalFirebaseAuth
import com.nicholasrutherford.chal.firebase.realtime.database.fetch.FetchFirebaseDatabase
import com.nicholasrutherford.chal.ui.base_vm.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MoreViewModel @ViewModelInject constructor(
    private val firebaseAuth: ChalFirebaseAuth,
    private val navigation: MoreNavigation,
    private val fetchFirebaseDatabase: FetchFirebaseDatabase,
    private val application: Application
) : BaseViewModel() {

    private var isUserEnrolledInAChallenge = false

    private val _isUserEnrolledInChallenge = MutableStateFlow(false)
    private val isUserEnrolledInChallenge: StateFlow<Boolean> = _isUserEnrolledInChallenge

    private val _loggedInProfilePicture = MutableStateFlow(application.getString(R.string.empty_string))
    private val loggedInProfilePicture: StateFlow<String> = _loggedInProfilePicture

    var alertTitle = application.getString(R.string.empty_string)
    var alertMessage = application.getString(R.string.empty_string)

    val viewState = MoreViewStateImpl()

    init {
        viewModelScope.launch {
            isUserEnrolledInChallenge.collect { isEnrolled ->
                isUserEnrolledInAChallenge = isEnrolled
            }
        }

        viewModelScope.launch {
            loggedInProfilePicture.collect { toolbarImage ->
                setToolbarImageFromResults(toolbarImage)
            }
        }

        fetchFirebaseDatabase.fetchIsUserEnrolledInAChallenge(_isUserEnrolledInChallenge)
        fetchFirebaseDatabase.fetchLoggedInUserProfilePicture(_loggedInProfilePicture)
    }

    private fun setToolbarImageFromResults(toolbarImage: String) {
        viewState.toolbarImage = toolbarImage
        setViewStateAsUpdated()
    }

    fun onSignOutAccountClicked() {
        setShouldShowProgressAsUpdated()

        firebaseAuth.logUserOut()

        setShouldShowDismissProgressAsUpdated()
        navigation.showLogin()
    }

    private fun featureNotImplementedYetAlert() {
        alertTitle = "Not yet implement"
        alertMessage = "Feature not implemented. Please check back later to see if feature gets implemented."

        setShouldShowAlertAsUpdated()
    }

    fun onMyProfileClicked() {
        // show my profile screen
    }

    fun onSettingsClicked() {
        featureNotImplementedYetAlert()
    }

    fun onChatClicked() {
        featureNotImplementedYetAlert()
    }

    fun onMyFeetClicked() {
        featureNotImplementedYetAlert()
    }

    fun onChallengesClicked() {
        featureNotImplementedYetAlert()
    }

    fun onDebugClicked() {
        featureNotImplementedYetAlert()
    }

    fun onReportBugClicked() {
        featureNotImplementedYetAlert()
    }

    fun onUploadProgressClicked() {
        if (isUserEnrolledInAChallenge) {
            navigation.showUploadProgress()
        } else {
            alertTitle = application.getString(R.string.not_enrolled_in_challenge)
            alertMessage = application.getString(R.string.not_enrolled_in_challenge_message)

            setShouldShowAlertAsUpdated()
        }
    }

    inner class MoreViewStateImpl : MoreViewState {
        override var toolbarImage: String = application.getString(R.string.empty_string)
    }

}