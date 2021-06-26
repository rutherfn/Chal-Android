package com.nicholasrutherford.chal.data.firebase

data class ActiveChallenge(
    var name: String,
    var bio: String,
    var categoryName: String,
    var numberOfDaysInChallenge: Int,
    var challengeExpire: String,
    var currentDay: Int,
    var dayOnChallenge: Int
)
