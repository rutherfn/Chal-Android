package com.nicholasrutherford.chal.data.responses

data class CurrentActiveChallengesResponse(
    var userCurrentDay: Int = 0,
    var timeResponseChallengeExpire: TimeResponse? = TimeResponse(0, 0, 0, 0, 0, 0, 0, 0.0, 0),
    var name: String = "",
    var description: String = "",
    var categoryName: String = "",
    var numberOfDaysOfChallenge: Int = 0
)
