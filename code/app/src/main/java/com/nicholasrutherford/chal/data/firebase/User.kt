package com.nicholasrutherford.chal.data.firebase

data class User(
    var id: Int,
    var username: String,
    var email: String,
    var profileImageUrl: String,
    var password: String,
    var firstName: String,
    var lastName: String,
    var bio: String,
    var age: Int,
    var currentFriends: List<Friends>,
    var activeChallenges: List<ActiveChallenge>
)