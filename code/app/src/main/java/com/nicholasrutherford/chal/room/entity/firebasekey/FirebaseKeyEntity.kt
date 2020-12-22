package com.nicholasrutherford.chal.room.entity.firebasekey

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "firebase_Keys")
data class FirebaseKeyEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo(name = "key")
    var key: String
)