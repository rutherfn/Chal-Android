package com.nicholasrutherford.chal.firebase.timberlog

import com.nicholasrutherford.chal.firebase.ageAccountPath
import com.nicholasrutherford.chal.firebase.bioAccountPath
import com.nicholasrutherford.chal.firebase.bioActiveChallengePath
import com.nicholasrutherford.chal.firebase.categoryNameActiveChallengePath
import com.nicholasrutherford.chal.firebase.currentDayActiveChallengePath
import com.nicholasrutherford.chal.firebase.dateChallengeExpireActiveChallengePath
import com.nicholasrutherford.chal.firebase.dayOnChallengeActiveChallengePath
import com.nicholasrutherford.chal.firebase.daysInChallengeActiveChallengePath
import com.nicholasrutherford.chal.firebase.firstNameAccountPath
import com.nicholasrutherford.chal.firebase.lastNameAccountPath
import com.nicholasrutherford.chal.firebase.nameActiveChallengePath
import com.nicholasrutherford.chal.firebase.usernameAccountPath
import timber.log.Timber

class TimberFirebaseLogImpl : TimberFirebaseLog {

    override fun logAccountUsernameError(username: String) {
        Timber.d("Error writing Firebase field $username to ${usernameAccountPath()}")
    }

    override fun logAccountFirstNameError(firstName: String) {
        Timber.d("Error writing Firebase field $firstName to ${firstNameAccountPath()}")
    }

    override fun logAccountLastNameError(lastName: String) {
        Timber.d("Error writing Firebase field $lastName to ${lastNameAccountPath()}")
    }

    override fun logAccountBioError(bio: String) {
        Timber.d("Error writing Firebase field $bio to ${bioAccountPath()}")
    }

    override fun logAccountAgeError(age: Int) {
        Timber.d("Error writing Firebase field $age to ${ageAccountPath()}")
    }

    override fun logActiveChallengeNameError(index: String, name: String) {
        Timber.d("Error writing Firebase field $name to ${nameActiveChallengePath(index)}")
    }

    override fun logActiveChallengeBioError(index: String, bio: String) {
        Timber.d("Error writing Firebase field $bio to ${bioActiveChallengePath(index)}")
    }

    override fun logActiveChallengeCategoryNameError(index: String, categoryName: String) {
        Timber.d("Error writing Firebase field $categoryName to ${categoryNameActiveChallengePath(index)}")
    }

    override fun logActiveChallengeDaysInChallengeError(index: String, daysInChallenge: Int) {
        Timber.d("Error writing Firebase field $daysInChallenge to ${daysInChallengeActiveChallengePath(index)}")
    }

    override fun logActiveChallengeExpireError(index: String, challengeExpire: String) {
        Timber.d("Error writing Firebase field $challengeExpire to ${dateChallengeExpireActiveChallengePath(index)}")
    }

    override fun logActiveChallengeCurrentDayError(index: String, currentDay: Int) {
        Timber.d("Error writing Firebase field $currentDay to ${currentDayActiveChallengePath(index)}")
    }

    override fun logDayOnChallengeError(index: String, dayOnChallenge: Int) {
        Timber.d("Error writing Firebase field $dayOnChallenge to ${dayOnChallengeActiveChallengePath(index)}")
    }
}