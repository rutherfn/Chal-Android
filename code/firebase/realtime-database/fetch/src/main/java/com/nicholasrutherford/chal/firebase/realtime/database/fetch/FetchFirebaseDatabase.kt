package com.nicholasrutherford.chal.firebase.realtime.database.fetch

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

interface FetchFirebaseDatabase {
    val database: FirebaseDatabase

    fun fetchAllUsersDatabaseReference(uid: String): DatabaseReference
}