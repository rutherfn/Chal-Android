package com.nicholasrutherford.chal.room.entity.challengesposts

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.nicholasrutherford.chal.room.converters.comments.CommentsConverter
import com.nicholasrutherford.chal.room.entity.comments.CommentsEntity

@Entity(tableName = "challenges_posts")
data class ChallengesPostsEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "description")
    var description: String,
    @ColumnInfo(name = "category")
    var category: Int,
    @ColumnInfo(name = "imageUrl")
    var imageUrl: String,
    @ColumnInfo(name = "currentDay")
    var currentDay: Int,
    @ColumnInfo(name = "comments")
    @TypeConverters(CommentsConverter::class)
    var comments: List<CommentsEntity>?
)