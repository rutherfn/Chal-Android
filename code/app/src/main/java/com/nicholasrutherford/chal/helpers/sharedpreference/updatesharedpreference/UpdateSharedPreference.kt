package com.nicholasrutherford.chal.helpers.sharedpreference.updatesharedpreference

import com.nicholasrutherford.chal.SharedPreference

interface UpdateSharedPreference : SharedPreference {
    fun updateProgressCaption(caption: String)
    fun updateProgressTitle(title: String)
}