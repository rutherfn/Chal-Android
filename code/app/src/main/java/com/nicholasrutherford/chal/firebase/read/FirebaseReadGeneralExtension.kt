package com.nicholasrutherford.chal.firebase.read

interface FirebaseReadGeneralExtension {
    fun getAge(): Int?
    fun getBio(): String?
    fun getEmail(): String?
    fun getFirstName(): String?
    fun getId(): Int?
    fun getLastName(): String?
    fun getPassword(): String?
    fun getUserProfilePicture(): String?
    fun getUsername(): String?
}