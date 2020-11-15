package com.nicholasrutherford.chal.data.realdata

data class ActiveChallengesPosts(
    val id: Int?,
    val title: String?,
    val description: String?,
    val category: Int?,
    val image: String?,
    val currentDay: Int?,
    val comments: List<Comments>?
)