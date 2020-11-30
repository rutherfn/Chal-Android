package com.nicholasrutherford.chal.data.realdata

data class Account(
    val id: Int = 0,
    val username: String,
    val email: String,
    val profileImage: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val bio: String,
    val age: Int,
    val friends:List<CurrentFriends>,
    val activeChallenges: List<ActiveChallenges>?
)