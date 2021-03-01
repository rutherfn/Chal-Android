package com.nicholasrutherford.chal.firebase.write.activechallenge

interface WriteActiveChallenge {
    fun writeCategoryName(index: String, newValue: String)
    fun writeBio(index: String, newValue: String)
    fun writeName(index: String, newValue: String)
    fun writeNumberOfDaysInChallenge(index: String, newValue: Int)
    fun writeDateChallengeExpire(index: String, newValue: String)
    fun writeUserCurrentDay(index: String, newValue: Int)
    fun writeUserDayOnChallenge(index: String, newValue: Int)
}
