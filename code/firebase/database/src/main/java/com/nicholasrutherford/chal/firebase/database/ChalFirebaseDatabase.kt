package com.nicholasrutherford.chal.firebase.database

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

interface ChalFirebaseDatabase {
    val database: FirebaseDatabase
    fun userDatabaseReference(uid: String): DatabaseReference
}