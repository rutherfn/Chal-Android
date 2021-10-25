package com.nicholasrutherford.chal.shared.preference.create

import android.app.Application
import android.content.Context
import com.nicholasrutherford.chal.helper.constants.*
import javax.inject.Inject

class CreateSharedPreferenceImpl @Inject constructor(
    application: Application
): CreateSharedPreference {

    private val sharedPreference = application.getSharedPreferences(CHAL_PREFERENCES, Context.MODE_PRIVATE)
    private val editor = sharedPreference.edit()

    override fun createProfilePictureDirectorySharedPreference(newValue: String) {
        editor.putString(PROFILE_PICTURE_DIRECTORY_PREFERENCE, newValue)
        editor.apply()
    }

    override fun createAgeSharedPreference(age: Int) {
        editor.putInt(AGE_PREFERENCE, age)
        editor.apply()
    }

    override fun createBioSharedPreference(bio: String) {
        editor.putString(BIO_PREFERENCE, bio)
        editor.apply()
    }

    override fun createEmailSharedPreference(email: String) {
        editor.putString(EMAIL_PREFERENCE, email)
        editor.apply()
    }

    override fun createLastNameSharedPresence(lastName: String) {
        editor.putString(LAST_NAME_PREFERENCE, lastName)
        editor.apply()
    }

    override fun createFirstNameSharedPreference(firstName: String) {
        editor.putString(FIRST_NAME_PREFERENCE, firstName)
        editor.apply()
    }

    override fun createIdSharedPreference(id: Int) {
        editor.putInt(ID_PREFERENCE, id)
        editor.apply()
    }

    override fun createProfilePictureSharedPreference(profilePicture: String) {
        editor.putString(PROFILE_PICTURE_PREFERENCE, profilePicture)
        editor.apply()
    }

    override fun createUsernameSharedPreference(username: String) {
        editor.putString(USERNAME_PREFERENCE, username)
        editor.apply()
    }

    override fun createChallengeBannerTypeSharedPreference(bannerType: Int) {
        editor.putInt(BANNER_TYPE_PREFERENCE, bannerType)
        editor.apply()
    }

    override fun createChallengeBannerTypeTitleSharedPreference(title: String) {
        editor.putString(BANNER_TYPE_TITLE, title)
        editor.apply()
    }

    override fun createChallengeBannerTypeDescSharedPreference(desc: String) {
        editor.putString(BANNER_TYPE_DESCRIPTION, desc)
        editor.apply()
    }

    override fun createChallengeBannerTypeIsVisible(isVisible: Boolean) {
        editor.putBoolean(BANNER_TYPE_IS_VISIBLE, isVisible)
        editor.apply()
    }

    override fun createChallengeBannerTypeIsCloseable(isCloseable: Boolean) {
        editor.putBoolean(BANNER_TYPE_IS_CLOSEABLE, isCloseable)
        editor.apply()
    }

    override fun createEnableChallengeModePreference(isChecked: Boolean) {
        editor.putBoolean(CHALLENGE_MODE_PREFERENCE, isChecked)
        editor.apply()
    }

    override fun createTurnOnAllFeaturesPreference(isChecked: Boolean) {
        editor.putBoolean(TURN_ON_ALL_FEATURES_PREFERENCE, isChecked)
        editor.apply()
    }

    override fun createShowDeviceNotificationsPreference(isChecked: Boolean) {
        editor.putBoolean(SHOW_DEVICE_NOTIFICATIONS, isChecked)
        editor.apply()
    }

    override fun createUnActivatedAccountPreference(isChecked: Boolean) {
        editor.putBoolean(SHOW_UNACTIVATED_ACCOUNT, isChecked)
        editor.apply()
    }

    override fun createShowOnboardPreference(isChecked: Boolean) {
        editor.putBoolean(SHOW_ON_BOARDING, isChecked)
        editor.apply()
    }

    override fun createLoginNavigationId(navigationId: Int) {
        editor.putInt(LOGIN_NAVIGATION_ID, navigationId)
        editor.apply()
    }
}