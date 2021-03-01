package com.nicholasrutherford.chal.data.responses

data class CurrentActiveChallengesResponse(
    var currentDay: Int = 0,
    var dayOnChallenge: Int = 0,
    var dateChallengeExpired: String = "",
    var name: String = "",
    var description: String = "",
    var categoryName: String = "",
    var numberOfDaysOfChallenge: Int = 0
)
