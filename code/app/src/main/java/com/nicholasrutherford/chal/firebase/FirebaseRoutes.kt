package com.nicholasrutherford.chal.firebase

// will do for now, stock image if user does not have a profile picture
const val DEFAULT_PROFILE_IMAGE = "https://www.dovercourt.org/wp-content/uploads/2019/11/610-6104451_image-placeholder-png-user-profile-placeholder-image-png.jpg"

const val USERS = "/users/" // realtime database route

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

// route for json array objects
const val ACTIVE_CHALLENGES = "activeChallenges"
const val FRIENDS = "friends"