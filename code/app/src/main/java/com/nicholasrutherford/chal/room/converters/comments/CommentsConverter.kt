package com.nicholasrutherford.chal.room.converters.comments

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nicholasrutherford.chal.room.entity.comments.CommentsEntity
import java.util.*
import kotlin.collections.ArrayList

class CommentsConverter {

    var gson = Gson()

    @TypeConverter
    fun fromComments(data: String) : List<CommentsEntity>? {
        if(data == null) {
            return Collections.emptyList()
        }

        val listType = object : TypeToken<ArrayList<CommentsEntity>>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun commentsListToString(comments: List<CommentsEntity>?): String? {
        return gson.toJson(comments)
    }
}