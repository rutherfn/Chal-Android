package com.nicholasrutherford.chal.firebase

// will do for now, stock image if user does not have a profile picture
const val DEFAULT_PROFILE_IMAGE = "https://www.dovercourt.org/wp-content/uploads/2019/11/610-6104451_image-placeholder-png-user-profile-placeholder-image-png.jpg"

fun bindUserId(uid: String?): String { return "/users/$uid" }
fun bindUserImageFile(fileName: String): String { return "/images/$fileName" }

const val USERS = "/users/"
const val POSTS = "/posts/"

// route for general info
const val AGE = "age"
const val BIO = "bio"
const val EMAIL = "email"
const val FIRST_NAME = "firstName"
const val ID = "id"
const val LAST_NAME = "lastName"
const val PASSWORD = "password"
const val PROFILE_IMAGE = "profileImage"
const val USERNAME = "username"

const val PROFILE_INFO = "/profileInfo/"
const val ACTIVE_CHALLENGES = "/activeChallenges/"
const val CATEGORY_NAME = "categoryName"
const val ID_ACTIVE_CHALLENGES = "id"
const val NAME = "name"
const val NUMBER_OF_DAYS_OF_CHALLENGE = "numberOfDaysOfChallenge"
const val DATE_CHALLENGE_EXPIRED = "dateChallengeExpired"

const val USER_CURRENT_DAY = "currentDay"
const val USER_DAY_ON_CHALLENGE = "dayOnChallenge"

const val ACTIVE_CHALLENGES_POSTS = "/activeChallengesPosts/"
const val ID_ACTIVE_CHALLENGES_POST = "id"
const val USERNAME_URL = "usernameUrl"
const val TITLE = "title"
const val DESCRIPTION = "description"
const val CATEGORY = "category"
const val IMAGE = "image"
const val CURRENT_DAY = "currentDay"

// comments here

const val FRIENDS = "/friends/"
const val EMAIL_FRIENDS = "email"
const val ID_FRIENDS = "id"
const val USERNAME_FRIENDS = "username"
