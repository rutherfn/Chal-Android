package com.nicholasrutherford.chal.data.firebase

data class ActivePost (
    var title: String,
    var description: String,
    var category: Int,
    var image: String,
    var currentDay: String,
    var username: String,
    var usernameUrl: String
    )