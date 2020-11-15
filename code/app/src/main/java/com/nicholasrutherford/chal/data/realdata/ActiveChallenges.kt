package com.nicholasrutherford.chal.data.realdata

data class ActiveChallenges(
    val id: Int?,
    val name: String?,
    val description: String?,
    val numberOfDaysOfChallenge: Int?,
    val timeChallengeExpire: String?,
    val userCurrentDay: Int?,
    val categoryName: String?,
    val activeChallengesPosts: List<ActiveChallengesPosts>
)