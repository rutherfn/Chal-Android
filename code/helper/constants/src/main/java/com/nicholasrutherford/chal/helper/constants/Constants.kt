package com.nicholasrutherford.chal.helper.constants

@Suppress("MagicNumber")
// account validations
const val AT_SYMBOL = "@"
const val USERNAME = "username"
const val EMAIL = "email"
const val PASSWORD = "password"

// splash loading
const val LOADING_DELAY = 1000

// hide keyboard flag 0
const val KEYBOARD_FLAGS = 0

// Firebase Database Routes here
const val ROUTE_USERS = "/users/"
fun userDatabaseReference(uid: String) = ROUTE_USERS.plus(uid)

// we can't set shared int shared preferences to null, so set it to 11 as a default value
const val STOCK_INT_PREFERENCE = 11

// constants for shared preference
const val CHAL_PREFERENCES = "CHAL_PREFERENCES"
const val PROFILE_PICTURE_DIRECTORY_PREFERENCE = "PROFILE_PICTURE_DIRECTORY_PREFERENCE"
const val AGE_PREFERENCE = "AGE_PREFERENCE"
const val BIO_PREFERENCE = "BIO_PREFERENCE"
const val EMAIL_PREFERENCE = "EMAIL_PREFERENCE"
const val FIRST_NAME_PREFERENCE = "FIRST_NAME_PREFERENCE"
const val ID_PREFERENCE = "ID_PREFERENCE"
const val LAST_NAME_PREFERENCE = "LAST_NAME_PREFERENCE"
const val PROFILE_PICTURE_PREFERENCE = "PROFILE_PICTURE_PREFERENCE"
const val USERNAME_PREFERENCE = "USERNAME_PREFERENCE"

const val BANNER_TYPE_PREFERENCE = "BANNER_TYPE_PREF"
const val BANNER_TYPE_TITLE = "BANNER_TYPE_TITLE_PREFERENCE"
const val BANNER_TYPE_DESCRIPTION = "BANNER_TYPE_DESCRIPTION_PREFERENCE"
const val BANNER_TYPE_IS_VISIBLE = "BANNER_TYPE_IS_VISIBLE_PREFERENCE"
const val BANNER_TYPE_IS_CLOSEABLE = "BANNER_TYPE_IS_CLOSEABLE_PREFERENCE"