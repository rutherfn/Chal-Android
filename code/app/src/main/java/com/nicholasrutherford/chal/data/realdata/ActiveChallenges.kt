package com.nicholasrutherford.chal.data.realdata

import java.util.*

data class ActiveChallenges(
    val id: Int?,
    val name: String?,
    val description: String?,
    val numberOfDaysOfChallenge: Int?,
    val timeChallengeExpire: Date?,
    val userCurrentDay: Int?,
    val categoryName: String?,
    val activeChallengesPosts: List<ActiveChallengesPosts>
)