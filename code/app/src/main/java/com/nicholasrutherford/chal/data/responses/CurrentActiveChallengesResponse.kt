package com.nicholasrutherford.chal.data.responses

data class CurrentActiveChallengesResponse(
    var activeChallengesPosts: List<ActiveChallengesPostsResponse>? = emptyList(),
    var categoryName: String = "",
    var currentDay: Int = 0,
    var dateChallengeExpired: String = "",
    var name: String = "",
    var bio: String = "",
    var numberOfDaysOfChallenge: Int = 0
)
