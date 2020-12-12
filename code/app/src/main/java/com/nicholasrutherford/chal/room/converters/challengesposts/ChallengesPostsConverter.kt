package com.nicholasrutherford.chal.room.converters.challengesposts

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nicholasrutherford.chal.room.entity.challengesposts.ChallengesPostsEntity
import java.util.*
import kotlin.collections.ArrayList

class ChallengesPostsConverter {

    var gson = Gson()

    @TypeConverter
    fun fromChallengesPosts(data: String) : List<ChallengesPostsEntity>? {

        if(data == null) {
            return Collections.emptyList()
        }

        val listType = object : TypeToken<ArrayList<String>>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun challengesPostsListToString(challengesPosts: List<ChallengesPostsEntity>?): String? {
        return gson.toJson(challengesPosts)
    }
}