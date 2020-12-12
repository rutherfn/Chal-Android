package com.nicholasrutherford.chal.room.converters.activechallenges

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nicholasrutherford.chal.room.entity.activechallenges.ActiveChallengesEntity
import java.util.*
import kotlin.collections.ArrayList

class CurrentActiveChallengesConverters {

    var gson = Gson()

    @TypeConverter
    fun fromActiveChallenges(data: String) : List<ActiveChallengesEntity>? {

        if(data == null) {
            return Collections.emptyList()
        }

        val listType = object : TypeToken<ArrayList<String>>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun activeChallengesListToString(activeChallenges: List<ActiveChallengesEntity>?): String? {
        return gson.toJson(activeChallenges)
    }
}