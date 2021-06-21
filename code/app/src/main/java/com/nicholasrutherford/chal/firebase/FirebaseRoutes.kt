package com.nicholasrutherford.chal.firebase

import com.google.firebase.auth.FirebaseAuth

// will do for now, stock image if user does not have a profile picture
const val DEFAULT_PROFILE_IMAGE = "https://www.dovercourt.org/wp-content/uploads/2019/11/610-6104451_image-placeholder-png-user-profile-placeholder-image-png.jpg"

val uid = FirebaseAuth.getInstance().uid ?: ""

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
const val NAME = "name"
const val NUMBER_OF_DAYS_OF_CHALLENGE = "numberOfDaysOfChallenge"
const val DATE_CHALLENGE_EXPIRED = "dateChallengeExpired"

const val USER_CURRENT_DAY = "currentDay"
const val USER_DAY_ON_CHALLENGE = "dayOnChallenge"

const val ACTIVE_CHALLENGES_POSTS = "/activeChallengesPosts/"
const val USERNAME_URL = "usernameUrl"
const val TITLE = "title"
const val DESCRIPTION = "description"
const val CATEGORY = "category"
const val IMAGE = "image"
const val CURRENT_DAY = "currentDay"

const val FRIENDS = "/friends/"
const val EMAIL_FRIENDS = "email"
const val ID_FRIENDS = "id"
const val USERNAME_FRIENDS = "username"

// account paths
fun usernameAccountPath(): String { return "$uid/$USERNAME" }
fun firstNameAccountPath(): String { return "$uid/$FIRST_NAME" }
fun lastNameAccountPath(): String { return "$uid/$LAST_NAME" }
fun bioAccountPath(): String { return "$uid/$BIO" }
fun ageAccountPath(): String { return "$uid/$AGE" }

// active challenges paths
fun nameActiveChallengePath(index: String): String { return "$uid$ACTIVE_CHALLENGES$index/$NAME"}
fun bioActiveChallengePath(index: String): String { return "$uid$ACTIVE_CHALLENGES$index/$BIO"}
fun categoryNameActiveChallengePath(index: String): String { return "$uid$ACTIVE_CHALLENGES$index/$CATEGORY_NAME"}
fun daysInChallengeActiveChallengePath(index: String): String { return "$uid$ACTIVE_CHALLENGES$index/$NUMBER_OF_DAYS_OF_CHALLENGE"}
fun dateChallengeExpireActiveChallengePath(index: String): String { return "$uid$ACTIVE_CHALLENGES$index/$DATE_CHALLENGE_EXPIRED"}
fun currentDayActiveChallengePath(index: String): String { return "$uid$ACTIVE_CHALLENGES$index/$USER_CURRENT_DAY"}
fun dayOnChallengeActiveChallengePath(index: String): String { return "$uid$ACTIVE_CHALLENGES$index/$USER_DAY_ON_CHALLENGE"}

// active challenges posts in user table paths
fun titleActiveChallengeUserPostPath(userActiveChallengeIndex: Int, userActiveChallengePostIndex: Int) : String {
    return "$uid$ACTIVE_CHALLENGES$userActiveChallengeIndex$ACTIVE_CHALLENGES_POSTS$userActiveChallengePostIndex/$TITLE"
}
fun descriptionActiveChallengeUserPostPath(userActiveChallengeIndex: Int, userActiveChallengePostIndex: Int) : String {
    return "$uid$ACTIVE_CHALLENGES$userActiveChallengeIndex$ACTIVE_CHALLENGES_POSTS$userActiveChallengePostIndex/$DESCRIPTION"
}
fun categoryActiveChallengeUserPostPath(userActiveChallengeIndex: Int, userActiveChallengePostIndex: Int) : String {
    return "$uid$ACTIVE_CHALLENGES$userActiveChallengeIndex$ACTIVE_CHALLENGES_POSTS$userActiveChallengePostIndex/$CATEGORY"
}
fun imageActiveChallengeUserPostPath(userActiveChallengeIndex: Int, userActiveChallengePostIndex: Int) : String {
    return "$uid$ACTIVE_CHALLENGES$userActiveChallengeIndex$ACTIVE_CHALLENGES_POSTS$userActiveChallengePostIndex/$IMAGE"
}
fun currentDayActiveChallengeUserPostPath(userActiveChallengeIndex: Int, userActiveChallengePostIndex: Int) : String {
    return "$uid$ACTIVE_CHALLENGES$userActiveChallengeIndex$ACTIVE_CHALLENGES_POSTS$userActiveChallengePostIndex/$CURRENT_DAY"
}
fun usernameActiveChallengeUserPostPath(userActiveChallengeIndex: Int, userActiveChallengePostIndex: Int) : String {
    return "$uid$ACTIVE_CHALLENGES$userActiveChallengeIndex$ACTIVE_CHALLENGES_POSTS$userActiveChallengePostIndex/$USERNAME"
}
fun usernameUrlActiveChallengeUserPostPath(userActiveChallengeIndex: Int, userActiveChallengePostIndex: Int) : String {
    return "$uid$ACTIVE_CHALLENGES$userActiveChallengeIndex$ACTIVE_CHALLENGES_POSTS$userActiveChallengePostIndex/$USERNAME_URL"
}

// posts in post table paths
fun titlePostPath(postIndex: Int): String { return "$postIndex/$TITLE" }
fun descriptionPostPath(postIndex: Int): String { return "$postIndex/$DESCRIPTION"}
fun categoryPostPath(postIndex: Int): String { return "$postIndex/$CATEGORY"}
fun imagePostPath(postIndex: Int): String { return "$postIndex/$IMAGE"}
fun currentDayPath(postIndex: Int): String { return "$postIndex/$CURRENT_DAY"}
fun usernamePath(postIndex: Int): String { return "$postIndex/$USERNAME"}
fun usernameUrlPath(postIndex: Int): String { return "$postIndex/$USERNAME_URL"}
