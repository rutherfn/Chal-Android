package com.nicholasrutherford.chal.firebase.sharedpref

import android.content.Context
import android.content.SharedPreferences

class ReadFirebaseSharePref(private val appContext: Context) {
    private val sharedPrefs: SharedPreferences = appContext.getSharedPreferences(FIREBASE_SHARED_PREF, Context.MODE_PRIVATE)

    fun getSharedPrefsFirebaseAge(): Int? { return sharedPrefs.getInt(AGE_PREF, 0) }

    fun getSharedPrefsFirebaseBio(): String? { return sharedPrefs.getString(BIO_PREF, "") }

    fun getSharedPrefsFirebaseEmail(): String? { return sharedPrefs.getString(EMAIL_PREF, "") }

    fun getSharedPrefsFirebaseFirstName(): String? { return sharedPrefs.getString(FIRST_NAME_PREF, "")}

    fun getSharedPrefsFirebaseId(): Int? { return sharedPrefs.getInt(ID_PREF, 0)}

    fun getSharedPrefsFirebaseLastName(): String? { return sharedPrefs.getString(LAST_NAME_PREF, "")}

    fun getSharedPrefsFirebasePassword(): String? { return sharedPrefs.getString(PASSWORD_PREF, "")}

    fun getSharedPrefsFirebaseUserProfilePicture(): String? { return sharedPrefs.getString(
        PROFILE_PICTURE_PREF, "")}

    fun getSharedPrefsFirebaseUsername(): String? { return sharedPrefs.getString(USERNAME_PREF, "")}
}