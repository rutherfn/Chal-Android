package com.nicholasrutherford.chal.firebase.write.activechallenge

import java.util.*

interface WriteActiveChallengesExtension {
    fun writeCategoryName(index: String, newValue: String)
    fun writeBio(index: String, newValue: String)
    fun writeName(index: String, newValue: String)
    fun writeNumberOfDaysInChallenge(index: String, newValue: Int)
    fun writeTimeChallengeExpire(index: String, newValue: Date)
    fun writeUserCurrentDay(index: String, newValue: Int)
}