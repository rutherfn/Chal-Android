package com.nicholasrutherford.chal.data.realdata

data class ActiveChallenges(
    var id: Int = 0,
    var name: String = "",
    var description: String = "",
    var numberOfDaysOfChallenge: Int = 0,
    var timeChallengeExpire: String = "",
    var userCurrentDay: Int = 0,
    var categoryName: String = "",
    var activeChallengesPosts: List<ActiveChallengesPosts>? = null
)
