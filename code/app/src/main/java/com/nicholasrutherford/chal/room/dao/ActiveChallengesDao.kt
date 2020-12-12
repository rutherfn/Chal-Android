package com.nicholasrutherford.chal.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.nicholasrutherford.chal.room.entity.activechallenges.ActiveChallengesEntity

@Dao
interface ActiveChallengesDao {
    @Query("SELECT * FROM active_challenges")
    fun readAllActiveChallenges(): LiveData<List<ActiveChallengesEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addActiveChallenge(activeChallengesEntity: ActiveChallengesEntity)

    @Query("DELETE FROM active_challenges")
    suspend fun deleteAllActiveChallenges()

    @Delete
    suspend fun deleteActiveChallenge(activeChallengesEntity: ActiveChallengesEntity)

    @Update
    suspend fun updateActiveChallenge(activeChallengesEntity: ActiveChallengesEntity)
}