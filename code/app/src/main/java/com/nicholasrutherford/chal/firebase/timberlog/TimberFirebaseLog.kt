package com.nicholasrutherford.chal.firebase.timberlog

import com.nicholasrutherford.chal.firebase.ChalFirebase

interface TimberFirebaseLog : ChalFirebase {
    fun logAccountUsernameError(username: String)
    fun logAccountFirstNameError(firstName: String)
    fun logAccountLastNameError(lastName: String)
    fun logAccountBioError(bio: String)
    fun logAccountAgeError(age: Int)

    fun logActiveChallengeNameError(index: String, name: String)
    fun logActiveChallengeBioError(index: String, bio: String)
    fun logActiveChallengeCategoryNameError(index: String, categoryName: String)
    fun logActiveChallengeDaysInChallengeError(index: String, daysInChallenge: Int)
    fun logActiveChallengeExpireError(index: String, challengeExpire: String)
    fun logActiveChallengeCurrentDayError(index: String, currentDay: Int)
    fun logDayOnChallengeError(index: String, dayOnChallenge: Int)
}