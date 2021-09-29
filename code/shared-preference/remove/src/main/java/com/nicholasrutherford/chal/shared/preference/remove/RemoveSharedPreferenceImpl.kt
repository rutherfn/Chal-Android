package com.nicholasrutherford.chal.shared.preference.remove

import android.app.Application
import android.content.Context
import com.nicholasrutherford.chal.helper.constants.PROFILE_PICTURE_DIRECTORY_PREFERENCE
import javax.inject.Inject

class RemoveSharedPreferenceImpl @Inject constructor(
    application: Application
) : RemoveSharedPreference {

    private val sharedPreference = application.getSharedPreferences("chal-preferences", Context.MODE_PRIVATE)
    private val editor = sharedPreference.edit()

    override fun removeProfilePictureDirectorySharedPreference() {
        if (!sharedPreference.getString(PROFILE_PICTURE_DIRECTORY_PREFERENCE, null).isNullOrEmpty()) {
            editor.remove(PROFILE_PICTURE_DIRECTORY_PREFERENCE).apply()
        }
    }

    override fun removeAllSharedPreferences() {
        editor.clear().apply()
    }
}