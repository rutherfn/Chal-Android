package com.nicholasrutherford.chal.profile.edit

interface EditProfileViewState {
    var username: String?
    var firstName: String?
    var lastName: String?
    var bio: String?
    var toolbarText: String
    var profileImageHeaderBackground: String
}