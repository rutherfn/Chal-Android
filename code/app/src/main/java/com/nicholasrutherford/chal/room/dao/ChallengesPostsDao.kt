package com.nicholasrutherford.chal.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.nicholasrutherford.chal.room.entity.challengesposts.ChallengesPostsEntity

@Dao
interface ChallengesPostsDao {
    @Query("SELECT * FROM challenges_posts")
    fun readAllChallengesPosts(): LiveData<List<ChallengesPostsEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addChallengePost(challengesPostsEntity: ChallengesPostsEntity)

    @Query("DELETE FROM challenges_posts")
    suspend fun deleteAllChallengesPosts()

    @Delete
    suspend fun deleteChallengesPost(challengesPostsEntity: ChallengesPostsEntity)

    @Update
    suspend fun updateChallengePost(challengesPostsEntity: ChallengesPostsEntity)
}