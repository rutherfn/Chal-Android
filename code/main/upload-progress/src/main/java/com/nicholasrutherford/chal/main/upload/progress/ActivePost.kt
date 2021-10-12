package com.nicholasrutherford.chal.main.upload.progress

data class ActivePost (
    var title: String,
    var description: String,
    var category: Int,
    var image: String,
    var currentDay: String,
    var username: String,
    var usernameUrl: String,
    var dateChallengeExpired: String
)