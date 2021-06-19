package com.nicholasrutherford.chal.data.responses

data class ActiveChallengesPostsResponse(
    var category: Int = 0,
    var currentDay: String = "",
    var description: String = "",
    var image: String = "",
    var title: String = "",
    var username: String = "",
    var usernameUrl: String = ""
)