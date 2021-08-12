package com.nicholasrutherford.chal.firebase

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
const val EMAIL = "email"
const val FIRST_NAME = "firstName"
const val ID = "id"
const val LAST_NAME = "lastName"
const val PASSWORD = "password"
const val PROFILE_IMAGE = "profileImage"
const val USERNAME = "username"
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
fun nameActiveChallengePath(uid: String, index: String): String { return "$uid$ACTIVE_CHALLENGES$index/$NAME"}
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

