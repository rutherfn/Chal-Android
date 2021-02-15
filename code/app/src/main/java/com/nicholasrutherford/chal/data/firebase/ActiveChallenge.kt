package com.nicholasrutherford.chal.data.firebase

data class ActiveChallenge(
    var name: String,
    var description: String,
    var numberOfDaysOfChallenge: Int,
    var challengeExipreTime: String,
    var currentDayOfChallange: Int,
    var categoryName: String,
    var activeChallengesPosts: List<ChallengesPosts>
)
