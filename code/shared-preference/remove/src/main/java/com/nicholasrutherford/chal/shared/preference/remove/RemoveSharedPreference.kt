package com.nicholasrutherford.chal.shared.preference.remove

interface RemoveSharedPreference {
    fun removeProfilePictureDirectorySharedPreference(preferenceName: String)
    fun removeAllSharedPreferences()
}