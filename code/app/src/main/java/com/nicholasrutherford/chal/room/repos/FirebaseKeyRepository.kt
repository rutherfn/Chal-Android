package com.nicholasrutherford.chal.room.repos

import androidx.lifecycle.LiveData
import com.nicholasrutherford.chal.room.dao.FirebaseKeyDao
import com.nicholasrutherford.chal.room.entity.firebasekey.FirebaseKeyEntity

class FirebaseKeyRepository(private val firebaseKeyDao: FirebaseKeyDao) {

    val readAllKeys: LiveData<List<FirebaseKeyEntity>> = firebaseKeyDao.readAllKeys()

    suspend fun readAllKeysRegular() = firebaseKeyDao.readAllKeysRegular()

    suspend fun addFirebaseKey(keyEntity: FirebaseKeyEntity) {
        firebaseKeyDao.addKey(keyEntity)
    }

    suspend fun deleteAllFirebaseKeys() = firebaseKeyDao.deleteAllFirebaseKeys()
}