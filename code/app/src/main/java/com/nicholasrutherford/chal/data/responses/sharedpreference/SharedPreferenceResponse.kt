package com.nicholasrutherford.chal.data.responses.sharedpreference

data class SharedPreferenceResponse(
    val type: String,
    val sharedPrefTypes: List<SharedPreferenceType>
)