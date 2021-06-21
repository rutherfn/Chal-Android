package com.nicholasrutherford.chal.firebase.write.activenewchallenge

import com.google.firebase.database.FirebaseDatabase
import com.nicholasrutherford.chal.data.firebase.ActiveChallenge
import com.nicholasrutherford.chal.firebase.USERS
import com.nicholasrutherford.chal.firebase.bioActiveChallengePath
import com.nicholasrutherford.chal.firebase.categoryNameActiveChallengePath
import com.nicholasrutherford.chal.firebase.currentDayActiveChallengePath
import com.nicholasrutherford.chal.firebase.dateChallengeExpireActiveChallengePath
import com.nicholasrutherford.chal.firebase.dayOnChallengeActiveChallengePath
import com.nicholasrutherford.chal.firebase.daysInChallengeActiveChallengePath
import com.nicholasrutherford.chal.firebase.nameActiveChallengePath
import com.nicholasrutherford.chal.firebase.timberlog.TimberFirebaseLogImpl

class WriteNewActiveChallengeImpl : WriteNewActiveChallenge {

    private val ref = FirebaseDatabase.getInstance().getReference(USERS)
    private val timberFirebaseLog = TimberFirebaseLogImpl()

    override fun writeNewActiveChallenge(index: String, activeChallenge: ActiveChallenge) {
        writeName(index = index, newValue = activeChallenge.name)
        writeBio(index = index, newValue = activeChallenge.bio)
        writeCategoryName(index = index, newValue = activeChallenge.categoryName)

        writeNumberOfDaysInChallenge(index = index, newValue = activeChallenge.numberOfDaysInChallenge)
        writeDateChallengeExpire(index = index, newValue = activeChallenge.challengeExpire)
        writeCurrentDay(index = index, newValue = activeChallenge.currentDay)
        writeDayOnChallenge(index = index, newValue = activeChallenge.dayOnChallenge)
    }

    override fun writeName(index: String, newValue: String) {
        ref.child(nameActiveChallengePath(index)).setValue(newValue)
            .addOnFailureListener { timberFirebaseLog.logActiveChallengeNameError(index, newValue) }
    }

    override fun writeBio(index: String, newValue: String) {
        ref.child(bioActiveChallengePath(index)).setValue(newValue)
            .addOnFailureListener { timberFirebaseLog.logActiveChallengeBioError(index, newValue) }
    }

    override fun writeCategoryName(index: String, newValue: String) {
        ref.child(categoryNameActiveChallengePath(index)).setValue(newValue)
            .addOnFailureListener { timberFirebaseLog.logActiveChallengeCategoryNameError(index, newValue) }
    }

    override fun writeNumberOfDaysInChallenge(index: String, newValue: Int) {
        ref.child(daysInChallengeActiveChallengePath(index)).setValue(newValue)
            .addOnFailureListener { timberFirebaseLog.logActiveChallengeDaysInChallengeError(index, newValue) }
    }

    override fun writeDateChallengeExpire(index: String, newValue: String) {
        ref.child(dateChallengeExpireActiveChallengePath(index))
            .setValue(newValue)
            .addOnFailureListener { timberFirebaseLog.logActiveChallengeExpireError(index, newValue) }
    }

    override fun writeCurrentDay(index: String, newValue: Int) {
        ref.child(currentDayActiveChallengePath(index)).setValue(newValue)
            .addOnFailureListener { timberFirebaseLog.logActiveChallengeCurrentDayError(index, newValue) }
    }

    override fun writeDayOnChallenge(index: String, newValue: Int) {
        ref.child(dayOnChallengeActiveChallengePath(index)).setValue(newValue)
            .addOnFailureListener { timberFirebaseLog.logDayOnChallengeError(index, newValue) }
    }
}
