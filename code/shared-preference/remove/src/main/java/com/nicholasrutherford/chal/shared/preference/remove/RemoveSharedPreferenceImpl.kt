package com.nicholasrutherford.chal.shared.preference.remove

import android.app.Application
import android.content.Context
import com.nicholasrutherford.chal.helper.constants.*
import javax.inject.Inject

class RemoveSharedPreferenceImpl @Inject constructor(
    application: Application
) : RemoveSharedPreference {

    private val sharedPreference = application.getSharedPreferences(CHAL_PREFERENCES, Context.MODE_PRIVATE)
    private val editor = sharedPreference.edit()

    private fun checkAndRemoveSharedPreferenceString(sharedPreferenceName: String) {
        if (!sharedPreference.getString(sharedPreferenceName, null).isNullOrEmpty()) {
            editor.remove(sharedPreferenceName).apply()
        }
    }

    private fun checkAndRemovePreferenceInt(sharedPreferenceName: String) {
        if (sharedPreference.getInt(sharedPreferenceName, STOCK_INT_PREFERENCE) != STOCK_INT_PREFERENCE) {
            editor.remove(sharedPreferenceName).apply()
        }
    }

    override fun removeProfilePictureDirectorySharedPreference() {
        checkAndRemoveSharedPreferenceString(PROFILE_PICTURE_DIRECTORY_PREFERENCE)
    }

    override fun removeAgeSharedPreference() {
        checkAndRemovePreferenceInt(AGE_PREFERENCE)
    }

    override fun removeBioSharedPreference() {
        checkAndRemoveSharedPreferenceString(BIO_PREFERENCE)
    }

    override fun removeEmailSharedPreference() {
        checkAndRemoveSharedPreferenceString(EMAIL_PREFERENCE)
    }

    override fun removeLastNameSharedPreference() {
        checkAndRemoveSharedPreferenceString(LAST_NAME_PREFERENCE)
    }

    override fun removeFirstNameSharedPreference() {
        checkAndRemoveSharedPreferenceString(FIRST_NAME_PREFERENCE)
    }

    override fun removeIdSharedPreference() {
        checkAndRemovePreferenceInt(ID_PREFERENCE)
    }

    override fun removeProfilePictureSharedPreference() {
        checkAndRemoveSharedPreferenceString(PROFILE_PICTURE_PREFERENCE)
    }

    override fun removeUsernameSharedPreference() {
        checkAndRemoveSharedPreferenceString(USERNAME_PREFERENCE)
    }

    override fun removeBannerTypeSharedPreference() {
        checkAndRemovePreferenceInt(BANNER_TYPE_PREFERENCE)
    }

    override fun removeChallengeBannerTypeTitleSharedPreference() {
        checkAndRemoveSharedPreferenceString(BANNER_TYPE_TITLE)
    }

    override fun removeChallengeBannerTypeDescSharedPreference() {
        checkAndRemoveSharedPreferenceString(BANNER_TYPE_PREFERENCE)
    }

    override fun removeChallengeBannerTypeIsVisible() {
        editor.remove(BANNER_TYPE_IS_VISIBLE).apply()
    }

    override fun removeChallengeBannerTypeIsCloseable() {
        editor.remove(BANNER_TYPE_IS_CLOSEABLE).apply()
    }

    override fun removeAllSharedPreferences() {
        editor.clear().apply()
    }
}