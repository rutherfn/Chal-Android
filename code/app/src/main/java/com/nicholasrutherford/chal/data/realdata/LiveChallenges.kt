package com.nicholasrutherford.chal.data.realdata

data class LiveChallenges(
    val id: Int,
    val category: String,
    val categoryNumber: Int,
    val challenges: List<Challenges>
)
