package com.nicholasrutherford.chal.firebase.write.activenewchallenge

import com.nicholasrutherford.chal.data.firebase.ActiveChallenge
import com.nicholasrutherford.chal.firebase.ChalFirebase

interface WriteNewActiveChallenge : ChalFirebase {
    fun writeNewActiveChallenge(allActiveChallengeIndex: Int, uid: String, index: String, activeChallenge: ActiveChallenge)
    fun writeName(allActiveChallengeIndex: Int, uid: String, index: String, newValue: String)
    fun writeBio(allActiveChallengeIndex: Int, uid: String, index: String, newValue: String)
    fun writeCategoryName(allActiveChallengeIndex: Int, uid: String, index: String, newValue: String)
    fun writeNumberOfDaysInChallenge(allActiveChallengeIndex: Int, uid: String, index: String, newValue: Int)
    fun writeDateChallengeExpire(allActiveChallengeIndex: Int, uid: String, index: String, newValue: String)
    fun writeCurrentDay(uid: String, index: String, newValue: Int)
    fun writeUsername(allActiveChallengeIndex: Int, uid: String, index: String, newValue: String)
}
