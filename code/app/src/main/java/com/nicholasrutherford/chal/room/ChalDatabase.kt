package com.nicholasrutherford.chal.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nicholasrutherford.chal.room.converters.activechallenges.CurrentActiveChallengesConverters
import com.nicholasrutherford.chal.room.converters.challengesposts.ChallengesPostsConverter
import com.nicholasrutherford.chal.room.converters.comments.CommentsConverter
import com.nicholasrutherford.chal.room.converters.friends.CurrentFriendsListConverter
import com.nicholasrutherford.chal.room.dao.*
import com.nicholasrutherford.chal.room.entity.ConfigurationEntity
import com.nicholasrutherford.chal.room.entity.activechallenges.ActiveChallengesEntity
import com.nicholasrutherford.chal.room.entity.challengesposts.ChallengesPostsEntity
import com.nicholasrutherford.chal.room.entity.comments.CommentsEntity
import com.nicholasrutherford.chal.room.entity.firebasekey.FirebaseKeyEntity
import com.nicholasrutherford.chal.room.entity.friends.FriendsEntity
import com.nicholasrutherford.chal.room.entity.user.UserEntity

@Database(
    entities = [ActiveChallengesEntity::class, ChallengesPostsEntity::class,  ConfigurationEntity::class, CommentsEntity::class,  FirebaseKeyEntity::class, FriendsEntity::class,  UserEntity::class],
    version = 6,
    exportSchema = false
)
@TypeConverters(ChallengesPostsConverter::class, CommentsConverter::class,  CurrentActiveChallengesConverters::class, CurrentFriendsListConverter::class)

abstract class ChalDatabase : RoomDatabase() {
    // debug
    abstract fun androidDebugDao(): ConfigurationDao

    // firebase keys
    abstract fun firebaseKeyDao(): FirebaseKeyDao

    // firebase db
    abstract fun activeChallengeDao(): ActiveChallengesDao
    abstract fun challengesPostsDao(): ChallengesPostsDao
    abstract fun commentsDao(): CommentsDao
    abstract fun friendsDao(): FriendsDao
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: ChalDatabase? = null

        fun getDatabase(context: Context): ChalDatabase {
            val tempInstance = INSTANCE

            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ChalDatabase::class.java,
                    "chal_database"
                ).fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                return instance
            }
        }
    }

    }