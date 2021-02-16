package com.nicholasrutherford.chal.data.responses

data class NewsFeedResponse(
    var username: String = "",
    var usernameUrl: String = "",
    var image: String = "",
    var currentDay: String = "",
    var description: String = "",
    var category: Int = 0,
    var title: String = ""
)
