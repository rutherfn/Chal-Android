package com.nicholasrutherford.chal.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.nicholasrutherford.chal.room.entity.firebasekey.FirebaseKeyEntity

@Dao
interface FirebaseKeyDao {
    @Query("SELECT * FROM firebase_Keys")
    fun readAllKeys(): LiveData<List<FirebaseKeyEntity>>

    @Query("SELECT * FROM firebase_Keys")
    suspend fun readAllKeysRegular(): List<FirebaseKeyEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addKey(firebaseKey: FirebaseKeyEntity)

    @Query("DELETE FROM firebase_keys")
    suspend fun deleteAllFirebaseKeys()

    @Delete
    suspend fun deleteFirebaseKey(firebaseKey: FirebaseKeyEntity)

    @Update
    suspend fun updateFirebaseKey(firebaseKey: FirebaseKeyEntity)
}