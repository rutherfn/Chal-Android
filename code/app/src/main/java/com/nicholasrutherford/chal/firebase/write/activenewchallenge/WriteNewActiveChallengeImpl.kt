package com.nicholasrutherford.chal.firebase.write.activenewchallenge

import com.google.firebase.database.FirebaseDatabase
import com.nicholasrutherford.chal.data.firebase.ActiveChallenge
import com.nicholasrutherford.chal.firebase.ALL_ACTIVE_CHALLENGES
import com.nicholasrutherford.chal.firebase.USERS
import com.nicholasrutherford.chal.firebase.bioActiveChallengePath
import com.nicholasrutherford.chal.firebase.bioAllActiveChallengePath
import com.nicholasrutherford.chal.firebase.categoryAllActiveChallengePath
import com.nicholasrutherford.chal.firebase.categoryNameActiveChallengePath
import com.nicholasrutherford.chal.firebase.currentDayActiveChallengePath
import com.nicholasrutherford.chal.firebase.dateChallengeExpireActiveChallengePath
import com.nicholasrutherford.chal.firebase.dateChallengeExpireAllActiveChallengePath
import com.nicholasrutherford.chal.firebase.daysInChallengeActiveChallengePath
import com.nicholasrutherford.chal.firebase.daysInChallengeAllActiveChallengePath
import com.nicholasrutherford.chal.firebase.nameActiveChallengePath
import com.nicholasrutherford.chal.firebase.nameAllActiveChallengePath
import com.nicholasrutherford.chal.firebase.usernameActiveChallengePath
import com.nicholasrutherford.chal.firebase.usernameAllActiveChallengePath
import timber.log.Timber

class WriteNewActiveChallengeImpl : WriteNewActiveChallenge {

    private val ref = FirebaseDatabase.getInstance().getReference(USERS)
    private val refAllActiveChallenges = FirebaseDatabase.getInstance()
        .getReference(ALL_ACTIVE_CHALLENGES)

    override fun writeNewActiveChallenge(allActiveChallengeIndex: Int, uid: String, index: String, activeChallenge: ActiveChallenge) {
        writeName(allActiveChallengeIndex = allActiveChallengeIndex, uid = uid, index = index, newValue = activeChallenge.name)
        writeBio(allActiveChallengeIndex = allActiveChallengeIndex, uid = uid, index = index, newValue = activeChallenge.bio)
        writeCategoryName(allActiveChallengeIndex = allActiveChallengeIndex, uid = uid, index = index, newValue = activeChallenge.categoryName)

        writeNumberOfDaysInChallenge(allActiveChallengeIndex = allActiveChallengeIndex, uid = uid, index = index, newValue = activeChallenge.numberOfDaysInChallenge)
        writeDateChallengeExpire(allActiveChallengeIndex = allActiveChallengeIndex, uid = uid, index = index, newValue = activeChallenge.challengeExpire)
        writeCurrentDay( uid = uid, index = index, newValue = activeChallenge.currentDay)
        writeUsername(allActiveChallengeIndex = allActiveChallengeIndex, uid = uid, index = index, newValue = activeChallenge.username)
    }

    override fun writeName(allActiveChallengeIndex: Int,
        uid: String,
        index: String,
        newValue: String) {
        ref.child(nameActiveChallengePath(uid, index)).setValue(newValue)
            .addOnFailureListener {
                Timber.d("Error writing Firebase field $newValue to ${nameActiveChallengePath(uid, index)}"
                )
            }

        refAllActiveChallenges.child(nameAllActiveChallengePath(allActiveChallengeIndex))
            .setValue(newValue)
            .addOnFailureListener {}
    }

    override fun writeBio(allActiveChallengeIndex: Int,
        uid: String,
        index: String,
        newValue: String) {
        ref.child(bioActiveChallengePath(uid, index)).setValue(newValue)
            .addOnFailureListener {
                Timber.d("Error writing Firebase field $newValue to ${bioActiveChallengePath(uid, index)}")
            }

        refAllActiveChallenges.child(bioAllActiveChallengePath(allActiveChallengeIndex))
            .setValue(newValue)
            .addOnFailureListener {}
    }

    override fun writeCategoryName(allActiveChallengeIndex: Int,
        uid: String,
        index: String,
        newValue: String) {
        ref.child(categoryNameActiveChallengePath(uid, index)).setValue(newValue)
            .addOnFailureListener {
                Timber.d("Error writing Firebase field $newValue to ${categoryNameActiveChallengePath(uid, index)}")
            }

        refAllActiveChallenges.child(categoryAllActiveChallengePath(allActiveChallengeIndex))
            .setValue(newValue)
            .addOnFailureListener {}
    }

    override fun writeNumberOfDaysInChallenge(allActiveChallengeIndex: Int,
        uid: String,
        index: String,
        newValue: Int) {
        ref.child(daysInChallengeActiveChallengePath(uid, index)).setValue(newValue)
            .addOnFailureListener {
                Timber.d("Error writing Firebase field $newValue to ${daysInChallengeActiveChallengePath(uid, index)}")
            }

        refAllActiveChallenges.child(daysInChallengeAllActiveChallengePath(allActiveChallengeIndex))
            .setValue(newValue)
            .addOnFailureListener {}
    }

    override fun writeDateChallengeExpire(allActiveChallengeIndex: Int,
        uid: String,
        index: String,
        newValue: String) {
        ref.child(dateChallengeExpireActiveChallengePath(uid, index))
            .setValue(newValue)
            .addOnFailureListener {
                Timber.d("Error writing Firebase field $newValue to ${dateChallengeExpireActiveChallengePath(uid, index)}")
            }

        refAllActiveChallenges.child(dateChallengeExpireAllActiveChallengePath(allActiveChallengeIndex))
            .setValue(newValue)
            .addOnFailureListener {}
    }

    override fun writeCurrentDay(uid: String,
        index: String,
        newValue: Int) {
        ref.child(currentDayActiveChallengePath(uid, index)).setValue(newValue)
            .addOnFailureListener {
                Timber.d("Error writing Firebase field $newValue to ${currentDayActiveChallengePath(uid, index)}")
            }
    }

    override fun writeUsername(allActiveChallengeIndex: Int,
        uid: String,
        index: String,
        newValue: String) {
        ref.child(usernameActiveChallengePath(uid, index)).setValue(newValue)
            .addOnFailureListener {
                Timber.d("Error writing Firebase field $newValue to ${usernameActiveChallengePath(uid, index)}")
            }

        refAllActiveChallenges.child(usernameAllActiveChallengePath(allActiveChallengeIndex))
            .setValue(newValue)
            .addOnFailureListener {}
    }
}
