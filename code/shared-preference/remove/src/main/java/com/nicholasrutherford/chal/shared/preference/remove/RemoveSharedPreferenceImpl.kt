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

    private fun removeBannerTypeSharedPreference() {
        checkAndRemovePreferenceInt(BANNER_TYPE_PREFERENCE)
    }

    private fun removeChallengeBannerTypeTitleSharedPreference() {
        checkAndRemoveSharedPreferenceString(BANNER_TYPE_TITLE)
    }

    private fun removeChallengeBannerTypeDescSharedPreference() {
        checkAndRemoveSharedPreferenceString(BANNER_TYPE_PREFERENCE)
    }

    private fun removeChallengeBannerTypeIsVisible() {
        editor.remove(BANNER_TYPE_IS_VISIBLE).apply()
    }

    private fun removeChallengeBannerTypeIsCloseable() {
        editor.remove(BANNER_TYPE_IS_CLOSEABLE).apply()
    }

    override fun removeChallengeBannerPreferences() {
        removeBannerTypeSharedPreference()
        removeChallengeBannerTypeTitleSharedPreference()
        removeChallengeBannerTypeDescSharedPreference()
        removeChallengeBannerTypeIsVisible()
        removeChallengeBannerTypeIsCloseable()
    }

    override fun removeChallengeModePreference() {
        editor.remove(CHALLENGE_MODE_PREFERENCE).apply()
    }

    override fun removeTurnOnAllFeaturesPreference() {
        editor.remove(TURN_ON_ALL_FEATURES_PREFERENCE).apply()
    }

    override fun removeShowDeviceNotificationPreference() {
        editor.remove(SHOW_DEVICE_NOTIFICATIONS).apply()
    }

    override fun removeShowUnActivatedAccountPreference() {
        editor.remove(SHOW_UNACTIVATED_ACCOUNT).apply()
    }

    override fun removeShowOnBoardingPreference() {
        editor.remove(SHOW_ON_BOARDING).apply()
    }

    override fun removeLoginNavigationId() {
        editor.remove(LOGIN_NAVIGATION_ID).apply()
    }

    override fun removeAllSharedPreferences() {
        editor.clear().apply()
    }
}