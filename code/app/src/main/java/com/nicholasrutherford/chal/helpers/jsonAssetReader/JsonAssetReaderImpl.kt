package com.nicholasrutherford.chal.helpers.jsonAssetReader

import android.app.Application
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nicholasrutherford.chal.data.responses.sharedpreference.SharedPreferenceResponse
import java.io.IOException
import javax.inject.Inject

class JsonAssetReaderImpl @Inject constructor(private val application: Application) : JsonAssetReader  {

    override fun readJsonFromAsset(fileName: String): String? {
        val jsonString: String

        try {
            jsonString = application.applicationContext.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }

        return jsonString
    }

    override fun fetchSharedPreferenceList(fileName: String): List<SharedPreferenceResponse> {
        var sharedPreferenceList: List<SharedPreferenceResponse>
        val sharedPreferenceListJson = object : TypeToken<List<SharedPreferenceResponse>>() {}.type
        sharedPreferenceList = Gson().fromJson(readJsonFromAsset(fileName = fileName), sharedPreferenceListJson)

        return sharedPreferenceList
    }
}