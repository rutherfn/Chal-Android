package com.nicholasrutherford.chal.viewmodels

import androidx.lifecycle.ViewModel
import com.nicholasrutherford.chal.viewstate.SettingsViewState

class SettingsViewModel : ViewModel() {

    private val viewState = SettingsViewStateImpl()

    init {

    }

    fun clickOnMyProfile() { viewState.isMyProfileClicked = true }

    inner class SettingsViewStateImpl() : SettingsViewState {
        override var isMyProfileClicked = false
        override var isNotificationsClicked = false
        override var isPreferencesClicked = false
        override var isEmailUsClicked = false
        override var isTroubleshootingClicked = false
        override var isEndUserAgreementClicked = false
        override var isRateOurAppClicked = false
        override var isViewPermissionsClicked = false
        override var isDarkModeClicked = false
    }
}