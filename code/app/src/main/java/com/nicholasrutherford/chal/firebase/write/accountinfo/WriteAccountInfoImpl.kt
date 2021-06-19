package com.nicholasrutherford.chal.firebase.write.accountinfo

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.nicholasrutherford.chal.data.firebase.AccountInfo
import com.nicholasrutherford.chal.firebase.AGE
import com.nicholasrutherford.chal.firebase.BIO
import com.nicholasrutherford.chal.firebase.FIRST_NAME
import com.nicholasrutherford.chal.firebase.LAST_NAME
import com.nicholasrutherford.chal.firebase.USERNAME
import com.nicholasrutherford.chal.firebase.USERS

class WriteAccountInfoImpl () : WriteAccountInfo {

    val uid = FirebaseAuth.getInstance().uid ?: ""
    val ref = FirebaseDatabase.getInstance().getReference(USERS)

    override fun updateAccountInfo(accountInfo: AccountInfo) {
        updateUsername(username = accountInfo.username)
        updateFirstName(firstName = accountInfo.firstName)
        updateLastName(lastName = accountInfo.lastName)
        updateBio(bio = accountInfo.bio)
        updateAge(age = accountInfo.age)
    }

    override fun updateUsername(username: String) {
        ref.child("$uid/$USERNAME").setValue(username)
    }

    override fun updateFirstName(firstName: String) {
        ref.child("$uid/$FIRST_NAME").setValue(firstName)
    }

    override fun updateLastName(lastName: String) {
        ref.child("$uid/$LAST_NAME").setValue(lastName)
    }

    override fun updateBio(bio: String) {
        ref.child("$uid/$BIO").setValue(bio)
    }

    override fun updateAge(age: Int) {
        ref.child("$uid/$AGE").setValue(age)
    }
}