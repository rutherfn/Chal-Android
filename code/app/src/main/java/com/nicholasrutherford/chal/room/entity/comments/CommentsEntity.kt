package com.nicholasrutherford.chal.room.entity.comments

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comments")
data class CommentsEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo(name = "description")
    var description: String,
    @ColumnInfo(name = "username")
    var username: String,
    @ColumnInfo(name = "imageUrl")
    var imageUrl: String,
    @ColumnInfo(name = "numberOfLikes")
    var numberOfLikes: Int
)