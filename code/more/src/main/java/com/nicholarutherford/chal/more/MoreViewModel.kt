package com.nicholarutherford.chal.more

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.nicholasrutherford.chal.data.elert.AlertType
import com.nicholasrutherford.chal.firebase.auth.ChalFirebaseAuth
import com.nicholasrutherford.chal.firebase.realtime.database.fetch.FetchFirebaseDatabase
import com.nicholasrutherford.chal.shared.preference.fetch.FetchSharedPreference
import com.nicholasrutherford.chal.shared.preference.remove.RemoveSharedPreference
import com.nicholasrutherford.chal.ui.base_vm.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MoreViewModel @ViewModelInject constructor(
    private val firebaseAuth: ChalFirebaseAuth,
    private val navigation: MoreNavigation,
    private val fetchFirebaseDatabase: FetchFirebaseDatabase,
    private val fetchSharedPreference: FetchSharedPreference,
    private val removeSharedPreference: RemoveSharedPreference,
    private val application: Application
) : BaseViewModel() {

    private var isUserEnrolledInAChallenge = false

    private val _isUserEnrolledInChallenge = MutableStateFlow(false)
    private val isUserEnrolledInChallenge: StateFlow<Boolean> = _isUserEnrolledInChallenge

    private val _loggedInProfilePicture = MutableStateFlow(application.getString(R.string.empty_string))
    private val loggedInProfilePicture: StateFlow<String> = _loggedInProfilePicture

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
        removeSharedPreference.removeAllSharedPreferences()
        setShouldShowProgressAsUpdated()

        firebaseAuth.logUserOut()
        setShouldSetAlertAsUpdated(
            title = application.getString(R.string.logged_out),
            message = application.getString(R.string.we_have_currently_logged_you_out_press_ok_to_confirm_log_out),
            type = AlertType.REGULAR_OK_ALERT,
            shouldCloseAppAfterDone = true
        )

        setShouldShowAlertAsUpdated()
        setShouldShowDismissProgressAsUpdated()
    }

    private fun featureNotImplementedYetAlert() {
        setShouldSetAlertAsUpdated(
            title = application.getString(R.string.not_yet_implemented),
            message = application.getString(R.string.feature_not_implemented_please_come_back_later_to_see_if_feature_gets_implemented),
            type = AlertType.REGULAR_OK_ALERT,
            shouldCloseAppAfterDone = false
        )

        setShouldShowAlertAsUpdated()
    }

    fun onMyProfileClicked() {
        navigation.showProfile()
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
        navigation.showDebug()
    }

    fun onReportBugClicked() {
        navigation.showBugReport()
    }

    fun onUploadProgressClicked() {
        if (isUserEnrolledInAChallenge) {
            navigation.showUploadProgress()
        } else {
            setShouldSetAlertAsUpdated(
                title = application.getString(R.string.not_enrolled_in_challenge),
                message = application.getString(R.string.not_enrolled_in_challenge_message),
                type = AlertType.REGULAR_OK_ALERT,
                shouldCloseAppAfterDone = false
            )
            setShouldShowAlertAsUpdated()
        }
    }

    inner class MoreViewStateImpl : MoreViewState {
        override var toolbarImage: String = application.getString(R.string.empty_string)
    }

}