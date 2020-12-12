package com.nicholasrutherford.chal

import android.app.Application
import com.nicholasrutherford.chal.room.ChalDatabase
import com.nicholasrutherford.chal.room.repos.FirebaseKeyRepository

class ChalRoom(application: Application) {
    // firebase key dao setup
    val firebaseKeyDao = ChalDatabase.getDatabase(application).firebaseKeyDao()
    val firebaseKeyRepository = FirebaseKeyRepository(firebaseKeyDao)
    val readAllFirebaseKeys = firebaseKeyRepository.readAllKeys
}