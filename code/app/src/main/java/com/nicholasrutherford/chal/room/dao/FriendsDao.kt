package com.nicholasrutherford.chal.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.nicholasrutherford.chal.room.entity.friends.FriendsEntity

@Dao
interface FriendsDao {
    @Query("SELECT * FROM friends")
    fun readAllFriends(): LiveData<List<FriendsEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFriends(friendsEntity: FriendsEntity)

    @Query("DELETE FROM friends")
    suspend fun deleteAllFriends()

    @Delete
    suspend fun deleteFriend(friendsEntity: FriendsEntity)

    @Update
    suspend fun updateFriend(friendsEntity: FriendsEntity)
}