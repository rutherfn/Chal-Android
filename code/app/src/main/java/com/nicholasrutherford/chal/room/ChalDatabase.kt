package com.nicholasrutherford.chal.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nicholasrutherford.chal.room.dao.ConfigurationDao
import com.nicholasrutherford.chal.room.entity.ConfigurationEntity

@Database(entities = [ConfigurationEntity::class], version = 1)

abstract class ChalDatabase : RoomDatabase() {
    abstract fun androidDebugDao() : ConfigurationDao

    companion object {
        @Volatile private var instance: ChalDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context)= instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also { instance = it}
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(context,
            ChalDatabase::class.java, "chal-android.db")
            .build()
    }

    }