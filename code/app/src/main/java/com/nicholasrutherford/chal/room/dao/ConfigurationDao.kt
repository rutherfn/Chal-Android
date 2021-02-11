package com.nicholasrutherford.chal.room.dao

import androidx.room.*
import com.nicholasrutherford.chal.room.entity.ConfigurationEntity

@Dao
interface ConfigurationDao {
    @Query("SELECT * FROM configuration")
    suspend fun getAll(): List<ConfigurationEntity>

    @Insert
    suspend fun insertAll(vararg configuration: ConfigurationEntity)

    @Delete
    suspend fun delete(configuration: ConfigurationEntity)

    @Update
    suspend fun updateAndroidDebug(vararg configuration: ConfigurationEntity)
}