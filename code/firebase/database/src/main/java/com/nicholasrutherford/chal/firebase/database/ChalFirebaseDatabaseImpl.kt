package com.nicholasrutherford.chal.firebase.database

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import javax.inject.Inject

class ChalFirebaseDatabaseImpl @Inject constructor(): ChalFirebaseDatabase {

    override val database = FirebaseDatabase.getInstance()

    override fun userDatabaseReference(uid: String): DatabaseReference {
        return database.getReference("/users/$uid")
    }
}