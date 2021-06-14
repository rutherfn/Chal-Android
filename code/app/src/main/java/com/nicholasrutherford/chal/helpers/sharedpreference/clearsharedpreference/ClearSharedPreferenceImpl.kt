package com.nicholasrutherford.chal.helpers.sharedpreference.clearsharedpreference

import android.app.Application
import com.nicholasrutherford.chal.helpers.sharedpreference.ReadSharedPreferenceJson
import javax.inject.Inject

class ClearSharedPreferenceImpl @Inject constructor(application: Application) : ClearSharedPreference {

    private val readSharedPreferenceJson = ReadSharedPreferenceJson(application)

    override fun clearAll() {
        readSharedPreferenceJson.editor.clear()
    }

    override fun clearProgressTitle() {
        readSharedPreferenceJson.sharedPreference.getString(readSharedPreferenceJson.progressTitle, "")?.let {
            readSharedPreferenceJson.editor.remove(readSharedPreferenceJson.progressTitle)
        }
    }

    override fun clearProgressCaption() {
        readSharedPreferenceJson.sharedPreference.getString(readSharedPreferenceJson.progressCaption, "")?.let {
            readSharedPreferenceJson.editor.remove(readSharedPreferenceJson.progressCaption)
        }
    }

}