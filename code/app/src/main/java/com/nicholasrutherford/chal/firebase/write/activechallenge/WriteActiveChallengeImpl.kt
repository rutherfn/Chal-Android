package com.nicholasrutherford.chal.firebase.write.activechallenge

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.nicholasrutherford.chal.firebase.ACTIVE_CHALLENGES
import com.nicholasrutherford.chal.firebase.CATEGORY_NAME
import com.nicholasrutherford.chal.firebase.DATE_CHALLENGE_EXPIRED
import com.nicholasrutherford.chal.firebase.DESCRIPTION
import com.nicholasrutherford.chal.firebase.NAME
import com.nicholasrutherford.chal.firebase.NUMBER_OF_DAYS_OF_CHALLENGE
import com.nicholasrutherford.chal.firebase.USERS
import com.nicholasrutherford.chal.firebase.USER_CURRENT_DAY
import com.nicholasrutherford.chal.firebase.USER_DAY_ON_CHALLENGE

class WriteActiveChallengeImpl() : WriteActiveChallenge {

    val uid = FirebaseAuth.getInstance().uid ?: ""
    val ref = FirebaseDatabase.getInstance().getReference(USERS)

    override fun writeCategoryName(index: String, newValue: String) {
        ref.child("$uid$ACTIVE_CHALLENGES$index").child(CATEGORY_NAME).setValue(newValue)
    }

    override fun writeBio(index: String, newValue: String) {
        ref.child("$uid$ACTIVE_CHALLENGES$index").child(DESCRIPTION).setValue(newValue)
    }

    override fun writeName(index: String, newValue: String) {
        ref.child("$uid$ACTIVE_CHALLENGES$index").child(NAME).setValue(newValue)
    }

    override fun writeNumberOfDaysInChallenge(index: String, newValue: Int) {
        ref.child("$uid$ACTIVE_CHALLENGES$index").child(NUMBER_OF_DAYS_OF_CHALLENGE).setValue(newValue)
    }

    override fun writeDateChallengeExpire(index: String, newValue: String) {
        ref.child("$uid$ACTIVE_CHALLENGES$index").child(DATE_CHALLENGE_EXPIRED).setValue(newValue)
    }

    override fun writeUserCurrentDay(index: String, newValue: Int) {
        ref.child("$uid$ACTIVE_CHALLENGES$index").child(USER_CURRENT_DAY).setValue(newValue)
    }

    override fun writeUserDayOnChallenge(index: String, newValue: Int) {
        ref.child("$uid$ACTIVE_CHALLENGES$index").child(USER_DAY_ON_CHALLENGE).setValue(newValue)
    }
}
