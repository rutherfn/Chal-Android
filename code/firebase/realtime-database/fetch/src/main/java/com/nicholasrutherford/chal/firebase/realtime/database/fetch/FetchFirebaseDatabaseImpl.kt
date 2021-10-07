package com.nicholasrutherford.chal.firebase.realtime.database.fetch

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.nicholasrutherford.chal.helper.constants.userDatabaseReference
import javax.inject.Inject

class FetchFirebaseDatabaseImpl @Inject constructor(): FetchFirebaseDatabase {

    override val database = FirebaseDatabase.getInstance()

    override fun fetchAllUsersDatabaseReference(uid: String): DatabaseReference {
        return database.getReference(userDatabaseReference(uid))
    }
}