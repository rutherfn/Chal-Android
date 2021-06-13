package com.nicholasrutherford.chal.helpers.sharedpreference.updatesharedpreference

import android.app.Application
import com.nicholasrutherford.chal.helpers.sharedpreference.ReadSharedPreferenceJson
import javax.inject.Inject

class UpdateSharedPreferenceImpl @Inject constructor(application: Application): UpdateSharedPreference {

    private val readSharedPreferenceJson = ReadSharedPreferenceJson(application)

    override fun updateProgressCaption(caption: String) {
        readSharedPreferenceJson.editor.putString(readSharedPreferenceJson.progressCaption, caption)
        readSharedPreferenceJson.editor.commit()
    }

    override fun updateProgressTitle(title: String) {
        readSharedPreferenceJson.editor.putString(readSharedPreferenceJson.progressTitle, title)
        readSharedPreferenceJson.editor.commit()
    }
}