package com.nicholasrutherford.chal.shared.preference.fetch

interface FetchSharedPreference {
    // firebase
    fun fetchProfilePictureDirectorySharedPreference(preferenceName: String): String?

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
}