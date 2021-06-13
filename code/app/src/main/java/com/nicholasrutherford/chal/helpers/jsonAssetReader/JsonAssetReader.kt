package com.nicholasrutherford.chal.helpers.jsonAssetReader

import com.nicholasrutherford.chal.data.responses.sharedpreference.SharedPreferenceResponse

interface JsonAssetReader {
    fun readJsonFromAsset(fileName: String): String?
    fun fetchSharedPreferenceList(fileName: String): List<SharedPreferenceResponse>
}