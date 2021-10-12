package com.nicholasrutherford.chal.main.upload.progress

import com.google.firebase.database.FirebaseDatabase
import com.nicholasrutherford.chal.helper.constants.USERS
import com.nicholasrutherford.chal.helper.constants.currentDayActiveChallengePath

class WriteNewActiveChallengeImpl {

    private val ref = FirebaseDatabase.getInstance().getReference(USERS)

    fun writeCurrentDay(uid: String,
                                 index: String,
                                 newValue: Int) {
        ref.child(currentDayActiveChallengePath(uid, index)).setValue(newValue)
            .addOnFailureListener {
            }
    }
}