package com.nicholasrutherford.chal.shared.preference.create

import android.app.Application
import android.content.Context
import javax.inject.Inject

class CreateSharedPreferenceImpl @Inject constructor(
    application: Application
): CreateSharedPreference {

    private val sharedPreference = application.getSharedPreferences("chal-preferences", Context.MODE_PRIVATE)
    private val editor = sharedPreference.edit()

    override fun createProfilePictureDirectorySharedPreference(preferenceName: String, newValue: String) {
        editor.putString(preferenceName, newValue)
        editor.apply()
    }
}