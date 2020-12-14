package com.nicholasrutherford.chal.room.entity.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.nicholasrutherford.chal.room.converters.activechallenges.CurrentActiveChallengesConverters
import com.nicholasrutherford.chal.room.converters.friends.CurrentFriendsListConverter
import com.nicholasrutherford.chal.room.entity.activechallenges.ActiveChallengesEntity
import com.nicholasrutherford.chal.room.entity.friends.FriendsEntity

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo(name = "username")
    var username: String,
    @ColumnInfo(name = "email")
    var email: String,
    @ColumnInfo(name = "profileImageUrl")
    var profileImageUrl: String,
    @ColumnInfo(name = "password")
    var password: String,
    @ColumnInfo(name = "firstName")
    var firstName: String,
    @ColumnInfo(name = "lastName")
    var lastName: String,
    @ColumnInfo(name = "bio")
    var bio: String,
    @ColumnInfo(name = "age")
    var age: Int,
    @ColumnInfo(name = "currentFriends")
    @TypeConverters(CurrentFriendsListConverter::class)
    var currentFriends: List<FriendsEntity>?,
    @ColumnInfo(name = "currentActiveChallenges")
    @TypeConverters(CurrentActiveChallengesConverters::class)
    var activeChallengeEntities: List<ActiveChallengesEntity>?
)