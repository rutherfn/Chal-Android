package com.nicholasrutherford.chal.data.account.info

data class ActiveChallengesPosts(
    var id: Int = 0,
    var title: String = "",
    var description: String = "",
    var category: Int = 0,
    var image: String = "",
    var currentDay: Int = 0,
    var comments: List<Comments> = listOf(Comments(0, "", "", "", 0))
)