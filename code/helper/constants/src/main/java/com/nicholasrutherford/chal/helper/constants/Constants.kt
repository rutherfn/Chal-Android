package com.nicholasrutherford.chal.helper.constants

@Suppress("MagicNumber")
// account validations
const val AT_SYMBOL = "@"
const val USERNAME = "username"
const val EMAIL = "email"
const val PASSWORD = "password"

// challenge params
const val CHALLENGE_TITLE = "challengeTitle"
const val CHALLENGE_CATEGORY = "challengeCategory"
const val CHALLENGE_URL = "challengeUrl"
const val CHALLENGE_DESC = "challengeDesc"
const val CHALLENGE_TIME = "challengeTime"
const val CHALLENGE_DURATION = "challengeDuration"
const val CHALLENGE_CATEGORY_NUMBER = "challengeCategoryNumber"

const val CHALLENGE_ADDED_PROGRESS_TITLE = "challengeAddedProgressTitle"
const val CHALLENGE_ADDED_PROGRESS_DAY = "challengeAddedProgressDay"

const val JSON_CHALLENGES_NAME = "challenges.json"

val CURRENT_CATEGORY_LIST = arrayListOf("Health And Wellness", "Intellectual", "Lifestyle")

// splash loading
const val LOADING_DELAY = 1000

// hide keyboard flag 0
const val KEYBOARD_FLAGS = 0

const val CHALLENGE_HEADER_PLACEHOLDER_URL = "https://tsico.com/wp-content/uploads/2019/05/3-Unique-Debt-Collection-Challenges.jpg"

// Firebase Database Routes here
const val ROUTE_USERS = "/users/"
fun userDatabaseReference(uid: String): String { return "/users/$uid"}

const val ROUTE_ALL_ACTIVE_CHALLENGES = "/all-active-challenges/"

const val PLACEHOLDER_CHALLENGE_HEADER_IMAGE = "https://www.topsecrets.com/wp-content/uploads/2020/03/goal-leap-4052923_1280-1024x512.jpg"

// will do for now, stock image if user does not have a profile picture
const val DEFAULT_PROFILE_IMAGE = "https://www.dovercourt.org/wp-content/uploads/2019/11/610-6104451_image-placeholder-png-user-profile-placeholder-image-png.jpg"

fun bindUserId(uid: String?): String { return "/users/$uid" }
fun bindUserImageFile(fileName: String): String { return "/images/$fileName" }

const val USERS = "/users/"
const val POSTS = "/posts/"
const val ALL_ACTIVE_CHALLENGES = "/all-active-challenges/"

// route for general info
const val AGE = "age"
const val BIO = "bio"
const val FIRST_NAME = "firstName"
const val ID = "id"
const val LAST_NAME = "lastName"
const val PROFILE_IMAGE = "profileImage"
const val CHALLENGE_BANNER_TYPE = "challengeBannerType"

const val PROFILE_INFO = "/profileInfo/"
const val ACTIVE_CHALLENGES = "/activeChallenges/"
const val CATEGORY_NAME = "categoryName"
const val NAME = "name"
const val NUMBER_OF_DAYS_OF_CHALLENGE = "numberOfDaysOfChallenge"
const val DATE_CHALLENGE_EXPIRED = "dateChallengeExpired"

const val USER_CURRENT_DAY = "currentDay"

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
fun usernameAccountPath(uid: String): String { return "$uid/$USERNAME" }
fun firstNameAccountPath(uid: String): String { return "$uid/$FIRST_NAME" }
fun lastNameAccountPath(uid: String): String { return "$uid/$LAST_NAME" }
fun bioAccountPath(uid: String): String { return "$uid/$BIO" }
fun ageAccountPath(uid: String): String { return "$uid/$AGE" }
fun challengeBannerTypePath(uid: String): String { return "$uid/$CHALLENGE_BANNER_TYPE"}

// active challenges paths
fun nameOfChallengePath(uid: String, index: String): String { return "$uid$ACTIVE_CHALLENGES$index/$NAME"}
fun bioActiveChallengePath(uid: String, index: String): String { return "$uid$ACTIVE_CHALLENGES$index/$BIO"}
fun categoryNameActiveChallengePath(uid: String, index: String): String { return "$uid$ACTIVE_CHALLENGES$index/$CATEGORY_NAME"}
fun daysInChallengeActiveChallengePath(uid: String, index: String): String { return "$uid$ACTIVE_CHALLENGES$index/$NUMBER_OF_DAYS_OF_CHALLENGE"}
fun dateChallengeExpireActiveChallengePath(uid: String, index: String): String { return "$uid$ACTIVE_CHALLENGES$index/$DATE_CHALLENGE_EXPIRED"}
fun currentDayActiveChallengePath(uid: String, index: String): String { return "$uid$ACTIVE_CHALLENGES$index/$USER_CURRENT_DAY"}
fun usernameActiveChallengePath(uid: String, index: String): String { return "$uid$ACTIVE_CHALLENGES$index/$USERNAME"}

// active challenges in active-challenges table paths
fun nameAllActiveChallengePath(allChallengesIndex: Int): String { return "$allChallengesIndex/$NAME" }
fun bioAllActiveChallengePath(allChallengesIndex: Int): String { return "$allChallengesIndex/$BIO" }
fun categoryAllActiveChallengePath(allChallengesIndex: Int): String { return "$allChallengesIndex/$CATEGORY_NAME"}
fun daysInChallengeAllActiveChallengePath(allChallengesIndex: Int): String { return "$allChallengesIndex/$NUMBER_OF_DAYS_OF_CHALLENGE"}
fun dateChallengeExpireAllActiveChallengePath(allChallengesIndex: Int): String { return "$allChallengesIndex/$DATE_CHALLENGE_EXPIRED"}
fun currentDayAllActiveChallengePath(allChallengesIndex: Int): String { return "$allChallengesIndex/$USER_CURRENT_DAY"}
fun usernameAllActiveChallengePath(allChallengesIndex: Int): String { return "$allChallengesIndex/$USERNAME"}

// active challenges posts in user table paths
fun titleActiveChallengeUserPostPath(uid: String, userActiveChallengeIndex: Int, userActiveChallengePostIndex: Int) : String {
    return "$uid$ACTIVE_CHALLENGES$userActiveChallengeIndex$ACTIVE_CHALLENGES_POSTS$userActiveChallengePostIndex/$TITLE"
}
fun descriptionActiveChallengeUserPostPath(uid: String, userActiveChallengeIndex: Int, userActiveChallengePostIndex: Int) : String {
    return "$uid$ACTIVE_CHALLENGES$userActiveChallengeIndex$ACTIVE_CHALLENGES_POSTS$userActiveChallengePostIndex/$DESCRIPTION"
}
fun categoryActiveChallengeUserPostPath(uid: String, userActiveChallengeIndex: Int, userActiveChallengePostIndex: Int) : String {
    return "$uid$ACTIVE_CHALLENGES$userActiveChallengeIndex$ACTIVE_CHALLENGES_POSTS$userActiveChallengePostIndex/$CATEGORY"
}
fun imageActiveChallengeUserPostPath(uid: String, userActiveChallengeIndex: Int, userActiveChallengePostIndex: Int) : String {
    return "$uid$ACTIVE_CHALLENGES$userActiveChallengeIndex$ACTIVE_CHALLENGES_POSTS$userActiveChallengePostIndex/$IMAGE"
}
fun currentDayActiveChallengeUserPostPath(uid: String, userActiveChallengeIndex: Int, userActiveChallengePostIndex: Int) : String {
    return "$uid$ACTIVE_CHALLENGES$userActiveChallengeIndex$ACTIVE_CHALLENGES_POSTS$userActiveChallengePostIndex/$CURRENT_DAY"
}
fun usernameActiveChallengeUserPostPath(uid: String, userActiveChallengeIndex: Int, userActiveChallengePostIndex: Int) : String {
    return "$uid$ACTIVE_CHALLENGES$userActiveChallengeIndex$ACTIVE_CHALLENGES_POSTS$userActiveChallengePostIndex/$USERNAME"
}
fun usernameUrlActiveChallengeUserPostPath(uid: String, userActiveChallengeIndex: Int, userActiveChallengePostIndex: Int) : String {
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
fun currentChallengeExpireDayPath(postIndex: Int): String { return "$postIndex/$DATE_CHALLENGE_EXPIRED"}

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

const val CHALLENGE_MODE_PREFERENCE = "CHALLENGE_MODE_PREFERENCE"
const val TURN_ON_ALL_FEATURES_PREFERENCE = "TURN_ON_ALL_FEATURES_PREFERENCE"
const val SHOW_DEVICE_NOTIFICATIONS = "SHOW_DEVICE_NOTIFICATIONS"
const val SHOW_UNACTIVATED_ACCOUNT = "SHOW_UNACTIVATED_ACCOUNT"
const val SHOW_ON_BOARDING = "SHOW_ON_BOARDING"

const val LOGIN_NAVIGATION_ID = "LOGIN_NAVIGATION_ID"