package com.nicholasrutherford.chal.data.firebase

data class ChallengesPosts(
    var title: String,
    var description: String,
    var category: Int,
    var imageUrl: String,
    var currentDay: Int,
    var comments: List<Comments>
)
