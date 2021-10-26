package com.nicholasrutherford.chal.profile

interface ProfileViewState {
    var age: Int?
    var description: String?
    var username: String?
    var profileImage: String?
    var myChallengesTabActive: Boolean
    var myFriendsTabActive: Boolean
}