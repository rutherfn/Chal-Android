package com.nicholasrutherford.chal.shared.preference.fetch

import android.app.Application
import android.content.Context
import com.nicholasrutherford.chal.helper.constants.CHAL_PREFERENCES
import javax.inject.Inject

class FetchSharedPreferenceImpl @Inject constructor(
    application: Application
): FetchSharedPreference {

    private val sharedPreference = application.getSharedPreferences(CHAL_PREFERENCES, Context.MODE_PRIVATE)

    override fun fetchProfilePictureDirectorySharedPreference(preferenceName: String): String? {
        val profilePictureDirectory = sharedPreference.getString(preferenceName, null)

        if (profilePictureDirectory.isNullOrEmpty()) {
            return  null
        } else {
            return profilePictureDirectory
        }
    }
}