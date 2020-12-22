package com.nicholasrutherford.chal.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.nicholasrutherford.chal.room.entity.user.UserEntity

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun readAllUsers(): LiveData<List<UserEntity>>

    @Query("SELECT * FROM user")
    suspend fun readAllUsersRegular(): List<UserEntity>

    @Query("SELECT * FROM user WHERE username =:username")
    suspend fun getUser(username: String): UserEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(userEntity: UserEntity)

    @Query("DELETE FROM user")
    suspend fun deleteAllUsers()

    @Delete
    suspend fun deleteUser(userEntity: UserEntity)

    @Update
    suspend fun updateUser(userEntity: UserEntity)
}