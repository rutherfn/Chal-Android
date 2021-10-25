package com.nicholasrutherford.chal.shared.preference.fetch

interface FetchSharedPreference {
    // firebase
    fun fetchProfilePictureDirectorySharedPreference(): String?

    // account shared preferences
    fun fetchAgeSharedPreference(): Int?
    fun fetchBioSharedPreference(): String?
    fun fetchEmailSharedPreference(): String?
    fun fetchLastNameSharedPreference(): String?
    fun fetchFirstNameSharedPreference(): String?
    fun fetchIdSharedPreference(): Int?
    fun fetchProfilePictureSharedPreference(): String?
    fun fetchUsernameSharedPreference(): String?

    // challenge banner shared preferences
    fun fetchBannerTypeSharedPreference(): Int?
    fun fetchChallengeBannerTypeTitleSharedPreference(): String?
    fun fetchChallengeBannerTypeDescription(): String?
    fun fetchChallengeBannerTypeIsVisible(): Boolean
    fun fetchChallengeBannerTypeIsCloseable(): Boolean

    // debug mode
    fun fetchChallengeModeSharedPreference(): Boolean
    fun fetchTurnOnAllFeaturesSharedPreference(): Boolean
    fun fetchShowDeviceNotificationPreference(): Boolean
    fun fetchShowUnActiviatedAccountPreference(): Boolean
    fun fetchShowOnBoarding(): Boolean

    // fetch login navigation id
    fun fetchLoginNavigationId(): Int?
}