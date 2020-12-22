package com.nicholasrutherford.chal.room.entity.activechallenges

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.nicholasrutherford.chal.room.converters.challengesposts.ChallengesPostsConverter
import com.nicholasrutherford.chal.room.entity.challengesposts.ChallengesPostsEntity

@Entity(tableName = "active_challenges")
data class ActiveChallengesEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo(name = "nameOfChallenge")
    var nameOfChallenge: String,
    @ColumnInfo(name = "description")
    var description: String,
    @ColumnInfo(name = "numberOfDaysOfChallenge")
    var numberOfDaysOfChallenge: Int,
    @ColumnInfo(name = "challengeExpireTime")
    var challengeExpireTime: String,
    @ColumnInfo(name = "currentDayOfChallenge")
    var currentDayOfChallenge: Int,
    @ColumnInfo(name = "categoryName")
    var categoryName: String,
    @ColumnInfo(name = "activeChallengesPosts")
    @TypeConverters(ChallengesPostsConverter::class)
    var activeChallengesPosts: List<ChallengesPostsEntity>?
)