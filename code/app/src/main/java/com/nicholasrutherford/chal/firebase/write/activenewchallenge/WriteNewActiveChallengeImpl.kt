package com.nicholasrutherford.chal.firebase.write.activenewchallenge

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.nicholasrutherford.chal.data.firebase.ActiveChallenge
import com.nicholasrutherford.chal.firebase.ACTIVE_CHALLENGES
import com.nicholasrutherford.chal.firebase.CATEGORY_NAME
import com.nicholasrutherford.chal.firebase.DATE_CHALLENGE_EXPIRED
import com.nicholasrutherford.chal.firebase.DESCRIPTION
import com.nicholasrutherford.chal.firebase.NAME
import com.nicholasrutherford.chal.firebase.NUMBER_OF_DAYS_OF_CHALLENGE
import com.nicholasrutherford.chal.firebase.USERS
import com.nicholasrutherford.chal.firebase.USER_CURRENT_DAY
import com.nicholasrutherford.chal.firebase.USER_DAY_ON_CHALLENGE

class WriteNewActiveChallengeImpl() : WriteNewActiveChallenge {

    val uid = FirebaseAuth.getInstance().uid ?: ""
    val ref = FirebaseDatabase.getInstance().getReference(USERS)

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
        ref.child("$uid$ACTIVE_CHALLENGES$index").child(NAME).setValue(newValue)
    }

    override fun writeBio(index: String, newValue: String) {
        ref.child("$uid$ACTIVE_CHALLENGES$index").child(DESCRIPTION).setValue(newValue)
    }

    override fun writeCategoryName(index: String, newValue: String) {
        ref.child("$uid$ACTIVE_CHALLENGES$index").child(CATEGORY_NAME).setValue(newValue)
    }

    override fun writeNumberOfDaysInChallenge(index: String, newValue: Int) {
        ref.child("$uid$ACTIVE_CHALLENGES$index").child(NUMBER_OF_DAYS_OF_CHALLENGE).setValue(newValue)
    }

    override fun writeDateChallengeExpire(index: String, newValue: String) {
        ref.child("$uid$ACTIVE_CHALLENGES$index").child(DATE_CHALLENGE_EXPIRED).setValue(newValue)
    }

    override fun writeCurrentDay(index: String, newValue: Int) {
        ref.child("$uid$ACTIVE_CHALLENGES$index").child(USER_CURRENT_DAY).setValue(newValue)
    }

    override fun writeDayOnChallenge(index: String, newValue: Int) {
        ref.child("$uid$ACTIVE_CHALLENGES$index").child(USER_DAY_ON_CHALLENGE).setValue(newValue)
    }
}
