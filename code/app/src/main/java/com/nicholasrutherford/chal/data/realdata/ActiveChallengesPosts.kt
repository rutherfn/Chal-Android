package com.nicholasrutherford.chal.data.realdata

data class ActiveChallengesPosts(
    val id: Int = 0,
    val title: String = "",
    val description: String = "",
    val category: Int = 0,
    val image: String = "",
    val currentDay: Int = 0,
    val comments: List<Comments> = listOf(Comments(0, "", "", "", 0))
)