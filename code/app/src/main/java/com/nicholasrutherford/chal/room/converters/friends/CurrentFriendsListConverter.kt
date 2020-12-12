package com.nicholasrutherford.chal.room.converters.friends

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nicholasrutherford.chal.room.entity.friends.FriendsEntity
import java.util.*
import kotlin.collections.ArrayList

class CurrentFriendsListConverter {

    var gson = Gson()

    @TypeConverter
    fun fromFriends(data: String) : List<FriendsEntity>? {
        if(data == null) {
            return Collections.emptyList()
        }

        val listType = object : TypeToken<ArrayList<String>>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun friendsListToString(friends: List<FriendsEntity>?): String? {
        return gson.toJson(friends)
    }
}