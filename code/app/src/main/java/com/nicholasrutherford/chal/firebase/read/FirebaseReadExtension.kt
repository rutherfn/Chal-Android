package com.nicholasrutherford.chal.firebase.read

import com.nicholasrutherford.chal.data.realdata.CurrentFriends

interface FirebaseReadExtension {
    fun getAge(): Int?
    fun getBio(): String?
    fun getEmail(): String?
    fun getFirstName(): String?
    fun getId(): Int?
    fun getLastName(): String?
    fun getPassword(): String?
    fun getUserProfilePicture(): String?
    fun getUsername(): String?

    fun getCurrentUserFriends(): List<CurrentFriends>
    fun getActiveUserChallenges()
}