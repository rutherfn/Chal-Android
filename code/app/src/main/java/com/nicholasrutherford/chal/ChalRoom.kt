package com.nicholasrutherford.chal

import android.app.Application
import com.nicholasrutherford.chal.room.ChalDatabase
import com.nicholasrutherford.chal.room.repos.FirebaseKeyRepository
import com.nicholasrutherford.chal.room.repos.UserRepository

class ChalRoom(application: Application) {
    // default args of chal room
    var userAge = ""

    val firebaseKeyDao = ChalDatabase.getDatabase(application).firebaseKeyDao()
    val firebaseKeyRepository = FirebaseKeyRepository(firebaseKeyDao)
    val readAllFirebaseKeys = firebaseKeyRepository.readAllKeys

    val userDao = ChalDatabase.getDatabase(application).userDao()
    val userRepository = UserRepository(userDao)
    val readAllUsers = userRepository.readAllUsers
}