package com.nicholasrutherford.chal.data.post

data class PostResponse (
    var category: Int = 0,
    var currentDay: String = "",
    var description: String = "",
    var image: String = "",
    var title: String = "",
    var username: String = "",
    var usernameUrl: String = "",
    var dateChallengeExpired: String = ""
)