package com.nicholasrutherford.chal.room.helpers

import android.content.Context
import com.nicholasrutherford.chal.room.ChalDatabase
import com.nicholasrutherford.chal.room.entity.ConfigurationEntity

class ConfigurationHelper(context: Context) {

    // declarations
    private val db = ChalDatabase(context)

    suspend fun initDebugOnSplashIfEmpty(configurationEntity: ConfigurationEntity)  {
            if (currentConfigurationRecords().isEmpty()) {
                insertConfigurationData(configurationEntity)
            }
    }

    suspend fun currentConfigurationRecords() : List<ConfigurationEntity> {
        return db.androidDebugDao().getAll()
    }

    private suspend fun insertConfigurationData(configurationEntity: ConfigurationEntity) {
        db.androidDebugDao().insertAll(configurationEntity)
    }

}