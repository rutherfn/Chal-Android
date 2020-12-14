package com.nicholasrutherford.chal.room.repos

import androidx.lifecycle.LiveData
import com.nicholasrutherford.chal.room.dao.UserDao
import com.nicholasrutherford.chal.room.entity.user.UserEntity

class UserRepository (private val userDao: UserDao) {

    val readAllUsers: LiveData<List<UserEntity>> = userDao.readAllUsers()

    suspend fun readAllUsersRegular() = userDao.readAllUsersRegular()

    suspend fun addAUser(userEntity: UserEntity) = userDao.addUser(userEntity)

    suspend fun getUser(username: String) = userDao.getUser(username)

    suspend fun updateUser(userEntity: UserEntity) = userDao.updateUser(userEntity)

    suspend fun deleteAllUsers() = userDao.deleteAllUsers()
}