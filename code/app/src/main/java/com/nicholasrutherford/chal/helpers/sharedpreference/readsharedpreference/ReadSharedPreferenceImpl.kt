package com.nicholasrutherford.chal.helpers.sharedpreference.readsharedpreference

import android.app.Application
import com.nicholasrutherford.chal.helpers.sharedpreference.ReadSharedPreferenceJson
import javax.inject.Inject

class ReadSharedPreferenceImpl @Inject constructor(private val application: Application) : ReadSharedPreference {

    private val readSharedPreferenceJson = ReadSharedPreferenceJson(application)

    override fun readProgressTitle(): String? {
        return readSharedPreferenceJson.sharedPreference.getString(readSharedPreferenceJson.progressTitle, "")
    }

    override fun readProgressCaption(): String? {
        return readSharedPreferenceJson.sharedPreference.getString(readSharedPreferenceJson.progressCaption, "")
    }
}