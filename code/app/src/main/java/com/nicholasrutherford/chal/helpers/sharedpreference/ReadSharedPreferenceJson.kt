package com.nicholasrutherford.chal.helpers.sharedpreference

import android.app.Application
import android.content.Context
import com.nicholasrutherford.chal.helpers.jsonAssetReader.JsonAssetReaderImpl
import javax.inject.Inject

@Suppress("MagicNumber")
private const val PREFERENCE_FILE_NAME = "preference.json"

const val CHAL_SHARED_PREFERENCES = "CHAL_SHARED_PREFERENCES"

class ReadSharedPreferenceJson @Inject constructor(application: Application) {

    val sharedPreference = application.getSharedPreferences(CHAL_SHARED_PREFERENCES, Context.MODE_PRIVATE)
    var editor = sharedPreference.edit()

    private val jsonAssetReader = JsonAssetReaderImpl(application)

    private val sharedPreferenceList = jsonAssetReader.fetchSharedPreferenceList(PREFERENCE_FILE_NAME)

    val progressCaption = sharedPreferenceList[0].sharedPrefTypes[1].name
    val progressTitle = sharedPreferenceList[0].sharedPrefTypes[0].name
}