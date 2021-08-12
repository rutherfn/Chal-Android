package com.nicholasrutherford.chal.firebase.sharedpref.clear

import android.content.Context
import android.content.SharedPreferences
import com.nicholasrutherford.chal.firebase.sharedpref.BANNER_TYPE_DESCRIPTION
import com.nicholasrutherford.chal.firebase.sharedpref.BANNER_TYPE_IS_CLOSEABLE
import com.nicholasrutherford.chal.firebase.sharedpref.BANNER_TYPE_IS_VISIBLE
import com.nicholasrutherford.chal.firebase.sharedpref.BANNER_TYPE_TITLE
import com.nicholasrutherford.chal.firebase.sharedpref.FIREBASE_SHARED_PREF

class ClearFirebaseSharedPref(private val context: Context) {

    private val sharedPrefs: SharedPreferences = context.getSharedPreferences(
        FIREBASE_SHARED_PREF, Context.MODE_PRIVATE)

    fun clearBannerTypeDetails() {
        clearBannerTitle()
        clearBannerDescription()
        clearBannerIsVisible()
        clearBannerIsCloseable()
    }

    private fun clearBannerTitle() {
        context.getSharedPreferences(BANNER_TYPE_TITLE, Context.MODE_PRIVATE)
            .edit().clear().apply()
    }

    private fun clearBannerDescription() {
        context.getSharedPreferences(BANNER_TYPE_DESCRIPTION, Context.MODE_MULTI_PROCESS)
            .edit().clear().apply()
    }

    private fun clearBannerIsVisible() {
        context.getSharedPreferences(BANNER_TYPE_IS_VISIBLE, Context.MODE_MULTI_PROCESS)
            .edit().clear().apply()
    }

    private fun clearBannerIsCloseable() {
        context.getSharedPreferences(BANNER_TYPE_IS_CLOSEABLE, Context.MODE_MULTI_PROCESS)
            .edit().clear().apply()
    }
}