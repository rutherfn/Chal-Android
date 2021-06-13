package com.nicholasrutherford.chal.helpers.sharedpreference.clearsharedpreference

import com.nicholasrutherford.chal.SharedPreference

interface ClearSharedPreference : SharedPreference {
    fun clearAll()
    fun clearProgressTitle()
    fun clearProgressCaption()
}