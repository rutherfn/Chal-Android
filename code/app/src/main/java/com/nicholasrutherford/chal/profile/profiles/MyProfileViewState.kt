package com.nicholasrutherford.chal.profile.profiles

interface MyProfileViewState {
    var age: Int?
    var description: String?
    var username: String?
    var profileImage: String?
    var myChallengesTabActive: Boolean
    var myFriendsTabActive: Boolean
}
