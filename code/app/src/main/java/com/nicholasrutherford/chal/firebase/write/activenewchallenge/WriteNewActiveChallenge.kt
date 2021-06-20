package com.nicholasrutherford.chal.firebase.write.activenewchallenge

import com.nicholasrutherford.chal.data.firebase.ActiveChallenge
import com.nicholasrutherford.chal.firebase.ChalFirebase

interface WriteNewActiveChallenge : ChalFirebase {
    fun writeNewActiveChallenge(index: String, activeChallenge: ActiveChallenge)
    fun writeName(index: String, newValue: String)
    fun writeBio(index: String, newValue: String)
    fun writeCategoryName(index: String, newValue: String)
    fun writeNumberOfDaysInChallenge(index: String, newValue: Int)
    fun writeDateChallengeExpire(index: String, newValue: String)
    fun writeCurrentDay(index: String, newValue: Int)
    fun writeDayOnChallenge(index: String, newValue: Int)
}
