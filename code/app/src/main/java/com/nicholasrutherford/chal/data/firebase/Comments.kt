package com.nicholasrutherford.chal.data.firebase

data class Comments(
    var id: Int,
    var description: String,
    var username: String,
    var imageUrl: String,
    var numberOfLikes: Int
)