package com.nicholasrutherford.chal.shared.preference.fetch

import android.app.Application
import android.content.Context
import com.nicholasrutherford.chal.helper.constants.*
import javax.inject.Inject

class FetchSharedPreferenceImpl @Inject constructor(
    application: Application
): FetchSharedPreference {

    private val sharedPreference = application.getSharedPreferences(CHAL_PREFERENCES, Context.MODE_PRIVATE)

    private fun getPreferenceString(preferenceName: String): String? {
        return sharedPreference.getString(preferenceName, null)
    }

    private fun getPreferenceInt(preferenceName: String): Int {
        return sharedPreference.getInt(preferenceName, STOCK_INT_PREFERENCE)
    }

    private fun getPreferenceBoolean(preferenceName: String): Boolean {
        return sharedPreference.getBoolean(preferenceName, false)
    }

    override fun fetchProfilePictureDirectorySharedPreference(preferenceName: String): String? {
        val profilePictureDirectory = sharedPreference.getString(preferenceName, null)

        if (profilePictureDirectory.isNullOrEmpty()) {
            return  null
        } else {
            return profilePictureDirectory
        }
    }

    override fun fetchAgeSharedPreference(): Int? {
        if (getPreferenceInt(AGE_PREFERENCE) == STOCK_INT_PREFERENCE) {
            return null
        } else {
            return getPreferenceInt(AGE_PREFERENCE)
        }
    }

    override fun fetchBioSharedPreference(): String? {
        if (getPreferenceString(BIO_PREFERENCE).isNullOrEmpty()) {
            return null
        } else {
            return getPreferenceString(BIO_PREFERENCE)
        }
    }

    override fun fetchEmailSharedPreference(): String? {
        if (getPreferenceString(EMAIL_PREFERENCE).isNullOrEmpty()) {
            return null
        } else {
            return getPreferenceString(EMAIL_PREFERENCE)
        }
    }

    override fun fetchLastNameSharedPreference(): String? {
        if (getPreferenceString(LAST_NAME_PREFERENCE).isNullOrEmpty()) {
            return null
        } else {
            return getPreferenceString(LAST_NAME_PREFERENCE)
        }
    }

    override fun fetchFirstNameSharedPreference(): String? {
        if (getPreferenceString(FIRST_NAME_PREFERENCE).isNullOrEmpty()) {
            return null
        } else {
            return getPreferenceString(FIRST_NAME_PREFERENCE)
        }
    }

    override fun fetchIdSharedPreference(): Int? {
        if (getPreferenceInt(ID_PREFERENCE) == STOCK_INT_PREFERENCE) {
            return null
        } else {
            return getPreferenceInt(ID_PREFERENCE)
        }
    }

    override fun fetchProfilePictureSharedPreference(): String? {
        if (getPreferenceString(PROFILE_PICTURE_PREFERENCE).isNullOrEmpty()) {
            return null
        } else {
            return getPreferenceString(PROFILE_PICTURE_PREFERENCE)
        }
    }

    override fun fetchUsernameSharedPreference(): String? {
        if (getPreferenceString(USERNAME_PREFERENCE).isNullOrEmpty()) {
            return null
        } else {
            return getPreferenceString(USERNAME_PREFERENCE)
        }
    }

    override fun fetchBannerTypeSharedPreference(): Int? {
        if (getPreferenceInt(BANNER_TYPE_PREFERENCE) == STOCK_INT_PREFERENCE) {
            return null
        } else {
            return getPreferenceInt(BANNER_TYPE_PREFERENCE)
        }
    }

    override fun fetchChallengeBannerTypeTitleSharedPreference(): String? {
        if (getPreferenceString(BANNER_TYPE_TITLE).isNullOrEmpty()) {
            return null
        } else {
            return getPreferenceString(BANNER_TYPE_TITLE)
        }
    }

    override fun fetchChallengeBannerTypeDescription(): String? {
        if (getPreferenceString(BANNER_TYPE_DESCRIPTION).isNullOrEmpty()) {
            return null
        } else {
            return getPreferenceString(BANNER_TYPE_DESCRIPTION)
        }
    }

    override fun fetchChallengeBannerTypeIsVisible(): Boolean {
        return getPreferenceBoolean(BANNER_TYPE_IS_VISIBLE)
    }

    override fun fetchChallengeBannerTypeIsCloseable(): Boolean {
        return getPreferenceBoolean(BANNER_TYPE_IS_CLOSEABLE)
    }
}