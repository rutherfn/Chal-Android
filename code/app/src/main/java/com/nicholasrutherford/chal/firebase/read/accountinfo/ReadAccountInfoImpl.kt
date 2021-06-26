package com.nicholasrutherford.chal.firebase.read.accountinfo

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.nicholasrutherford.chal.firebase.USERS

class ReadAccountInfoImpl : ReadAccountInfo {

    private val ref = FirebaseDatabase.getInstance().getReference(USERS)
    private val uid = FirebaseAuth.getInstance().uid ?: ""


    override fun getAccountInfo() {
        ref.child(uid).addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                TODO("Not yet implemented")
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}