package com.nicholasrutherford.chal.viewstate

interface SettingsViewState : ViewState {
    var isMyProfileClicked: Boolean
    var isNotificationsClicked: Boolean
    var isPreferencesClicked: Boolean
    var isEmailUsClicked: Boolean
    var isTroubleshootingClicked: Boolean
    var isEndUserAgreementClicked: Boolean
    var isRateOurAppClicked: Boolean
    var isViewPermissionsClicked: Boolean
    var isDarkModeClicked: Boolean
}