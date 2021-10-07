package com.nicholasrutherford.chal.shared.preference.remove

interface RemoveSharedPreference {
    // firebase
    fun removeProfilePictureDirectorySharedPreference()

    // account shared preferences
    fun removeAgeSharedPreference()
    fun removeBioSharedPreference()
    fun removeEmailSharedPreference()
    fun removeLastNameSharedPreference()
    fun removeFirstNameSharedPreference()
    fun removeIdSharedPreference()
    fun removeProfilePictureSharedPreference()
    fun removeUsernameSharedPreference()

    // challenge banner shared preferences
    fun removeChallengeBannerPreferences()

    // remove all shared preferences
    fun removeAllSharedPreferences()
}