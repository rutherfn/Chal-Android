package com.nicholasrutherford.chal.data.realdata

data class ActiveChallenges(
    val id: Int = 0,
    val name: String = "",
    val description: String = "",
    val numberOfDaysOfChallenge: Int= 0,
    val timeChallengeExpire: String= "",
    val userCurrentDay: Int = 0,
    val categoryName: String = "",
    val isRealChallenge: Boolean = false,
    val activeChallengesPosts: List<ActiveChallengesPosts>? = null
)