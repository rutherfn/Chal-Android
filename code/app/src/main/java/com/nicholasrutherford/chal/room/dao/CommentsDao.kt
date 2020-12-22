package com.nicholasrutherford.chal.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.nicholasrutherford.chal.room.entity.comments.CommentsEntity

@Dao
interface CommentsDao {
    @Query("SELECT * FROM comments")
    fun readAllComments(): LiveData<List<CommentsEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addComments(commentsEntity: CommentsEntity)

    @Query("DELETE FROM comments")
    suspend fun deleteAllComments()

    @Delete
    suspend fun deleteComment(commentsEntity: CommentsEntity)

    @Update
    suspend fun updateComment(commentsEntity: CommentsEntity)
}