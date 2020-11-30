package com.nicholasrutherford.chal.firebase.sharedpref

import android.content.Context
import android.content.SharedPreferences

class WriteFirebaseSharedPref(private val appContext: Context)  {
    private val sharedPrefs: SharedPreferences = appContext.getSharedPreferences(FIREBASE_SHARED_PREF, Context.MODE_PRIVATE)

    fun clearAllSharePrefs() = sharedPrefs.edit().clear().apply()

    fun writeSharedPrefsAge(age: Int) = sharedPrefs.edit().putInt(AGE_PREF, age).apply()

    fun writeSharePrefsBio(bio: String) = sharedPrefs.edit().putString(BIO_PREF, bio).apply()

    fun writeSharedPrefsEmail(email: String) = sharedPrefs.edit().putString(EMAIL_PREF, email).apply()

    fun writeSharePrefsFirstName(firstName: String) = sharedPrefs.edit().putString(FIRST_NAME_PREF, firstName).apply()

    fun writeSharedPrefsId(id: Int) = sharedPrefs.edit().putInt(ID_PREF, id).apply()

    fun writeSharedPrefsLastName(lastName: String) = sharedPrefs.edit().putString(LAST_NAME_PREF, lastName).apply()

    fun writeSharedPrefsPassword(password: String) = sharedPrefs.edit().putString(PASSWORD_PREF, password).apply()

    fun writeSharedPrefsUserProfilePicture(profilePicture: String)  = sharedPrefs.edit().putString(
        PROFILE_PICTURE_PREF, profilePicture).apply()

    fun writeSharedPrefsUsername(username: String) = sharedPrefs.edit().putString(USERNAME_PREF, username).apply()
}