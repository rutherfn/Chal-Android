package com.nicholasrutherford.chal.shared.preference.create

interface CreateSharedPreference {
    // firebase
    fun createProfilePictureDirectorySharedPreference(newValue: String)

    // account shared preferences
    fun createAgeSharedPreference(age: Int)
    fun createBioSharedPreference(bio: String)
    fun createEmailSharedPreference(email: String)
    fun createLastNameSharedPresence(lastName: String)
    fun createFirstNameSharedPreference(firstName: String)
    fun createIdSharedPreference(id: Int)
    fun createProfilePictureSharedPreference(profilePicture: String)
    fun createUsernameSharedPreference(username: String)

    // challenge shared preferences
    fun createChallengeBannerTypeSharedPreference(bannerType: Int)
    fun createChallengeBannerTypeTitleSharedPreference(title: String)
    fun createChallengeBannerTypeDescSharedPreference(desc: String)
    fun createChallengeBannerTypeIsVisible(isVisible: Boolean)
    fun createChallengeBannerTypeIsCloseable(isCloseable: Boolean)

    // debug mode preferences
    fun createEnableChallengeModePreference(isChecked: Boolean)
    fun createTurnOnAllFeaturesPreference(isChecked: Boolean)
    fun createShowDeviceNotificationsPreference(isChecked: Boolean)
    fun createUnActivatedAccountPreference(isChecked: Boolean)
    fun createShowOnboardPreference(isChecked: Boolean)

    // this is used for navigation purposes, for login
    fun createLoginNavigationId(navigationId: Int)
}