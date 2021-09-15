package com.nicholasrutherford.chal.shared.preference.fetch

interface FetchSharedPreference {
    fun fetchProfilePictureDirectorySharedPreference(preferenceName: String): String?
}