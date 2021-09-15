package com.nicholasrutherford.chal.shared.preference.remove

import android.app.Application
import android.content.Context
import javax.inject.Inject

class RemoveSharedPreferenceImpl @Inject constructor(
    application: Application
) : RemoveSharedPreference {

    private val sharedPreference = application.getSharedPreferences("chal-preferences", Context.MODE_PRIVATE)
    private val editor = sharedPreference.edit()

    override fun removeProfilePictureDirectorySharedPreference(preferenceName: String) {
        if (!sharedPreference.getString(preferenceName, null).isNullOrEmpty()) {
            editor.remove(preferenceName).apply()
        }
    }

    override fun removeAllSharedPreferences() {
        editor.clear().apply()
    }
}