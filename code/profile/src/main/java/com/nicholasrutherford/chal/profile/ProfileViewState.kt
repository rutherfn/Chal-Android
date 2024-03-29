package com.nicholasrutherford.chal.profile

interface ProfileViewState {
    var toolbarText: String
    var profileImageHeaderBackground: String
    var age: Int?
    var description: String?
    var username: String?
    var profileImage: String?
    var myChallengesTabActive: Boolean
    var myFriendsTabActive: Boolean
    var activeChallengesSize: Int?
}