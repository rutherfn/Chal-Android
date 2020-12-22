package com.nicholasrutherford.chal.firebase.write.activechallenge

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.nicholasrutherford.chal.firebase.*
import java.util.*

class WriteActiveChallengeFirebase () : WriteActiveChallengesExtension {

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

    override fun writeTimeChallengeExpire(index: String, newValue: Date) {
        ref.child("$uid$ACTIVE_CHALLENGES$index").child(TIME_CHANGE_EXPIRE).setValue(newValue)
    }

    override fun writeUserCurrentDay(index: String, newValue: Int) {
        ref.child("$uid$ACTIVE_CHALLENGES$index").child(USER_CURRENT_DAY).setValue(newValue)
    }


}