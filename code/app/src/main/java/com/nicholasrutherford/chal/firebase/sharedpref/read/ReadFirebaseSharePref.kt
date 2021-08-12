package com.nicholasrutherford.chal.firebase.sharedpref.read

import android.content.Context
import android.content.SharedPreferences
import com.nicholasrutherford.chal.firebase.sharedpref.AGE_PREF
import com.nicholasrutherford.chal.firebase.sharedpref.BANNER_TYPE_DESCRIPTION
import com.nicholasrutherford.chal.firebase.sharedpref.BANNER_TYPE_IS_CLOSEABLE
import com.nicholasrutherford.chal.firebase.sharedpref.BANNER_TYPE_IS_VISIBLE
import com.nicholasrutherford.chal.firebase.sharedpref.BANNER_TYPE_PREF
import com.nicholasrutherford.chal.firebase.sharedpref.BANNER_TYPE_TITLE
import com.nicholasrutherford.chal.firebase.sharedpref.BIO_PREF
import com.nicholasrutherford.chal.firebase.sharedpref.EMAIL_PREF
import com.nicholasrutherford.chal.firebase.sharedpref.FIREBASE_SHARED_PREF
import com.nicholasrutherford.chal.firebase.sharedpref.FIRST_NAME_PREF
import com.nicholasrutherford.chal.firebase.sharedpref.ID_PREF
import com.nicholasrutherford.chal.firebase.sharedpref.LAST_NAME_PREF
import com.nicholasrutherford.chal.firebase.sharedpref.PASSWORD_PREF
import com.nicholasrutherford.chal.firebase.sharedpref.PROFILE_PICTURE_PREF
import com.nicholasrutherford.chal.firebase.sharedpref.USERNAME_PREF

class ReadFirebaseSharePref(private val appContext: Context) {
    private val sharedPrefs: SharedPreferences = appContext.getSharedPreferences(
        FIREBASE_SHARED_PREF, Context.MODE_PRIVATE)

    fun getSharedPrefsFirebaseAge(): Int? { return sharedPrefs.getInt(AGE_PREF, 0) }

    fun getSharedPrefsFirebaseBio(): String? { return sharedPrefs.getString(BIO_PREF, "") }

    fun getSharedPrefsFirebaseEmail(): String? { return sharedPrefs.getString(EMAIL_PREF, "") }

    fun getSharedPrefsFirebaseFirstName(): String? { return sharedPrefs.getString(FIRST_NAME_PREF, "") }

    fun getSharedPrefsFirebaseId(): Int? { return sharedPrefs.getInt(ID_PREF, 0) }

    fun getSharedPrefsFirebaseLastName(): String? { return sharedPrefs.getString(LAST_NAME_PREF, "") }

    fun getSharedPrefsFirebasePassword(): String? { return sharedPrefs.getString(PASSWORD_PREF, "") }

    fun getSharedPrefsFirebaseUserProfilePicture(): String? { return sharedPrefs.getString(
        PROFILE_PICTURE_PREF, "") }

    fun getSharedPrefsFirebaseUsername(): String? { return sharedPrefs.getString(USERNAME_PREF, "") }

    fun getSharedPrefsBannerType(): Int { return sharedPrefs.getInt(BANNER_TYPE_PREF, 0) }

    // banner type values

    fun getSharedPrefsBannerTypeTitle(): String? { return sharedPrefs.getString(BANNER_TYPE_TITLE, "")}

    fun getSharedPrefsBannerTypeDescription(): String? { return sharedPrefs.getString(
        BANNER_TYPE_DESCRIPTION, "")
    }

    fun getSharedPrefsBannerTypeIsVisible(): Boolean { return sharedPrefs.getBoolean(
        BANNER_TYPE_IS_VISIBLE, false)
    }

    fun getSharedPrefsBannerTypeIsCloseable(): Boolean { return sharedPrefs.getBoolean(
        BANNER_TYPE_IS_CLOSEABLE, false)
    }
}