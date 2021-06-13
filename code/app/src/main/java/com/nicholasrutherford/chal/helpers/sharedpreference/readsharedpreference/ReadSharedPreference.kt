package com.nicholasrutherford.chal.helpers.sharedpreference.readsharedpreference

import com.nicholasrutherford.chal.SharedPreference

interface ReadSharedPreference: SharedPreference {
    fun readProgressTitle(): String?
    fun readProgressCaption(): String?
}