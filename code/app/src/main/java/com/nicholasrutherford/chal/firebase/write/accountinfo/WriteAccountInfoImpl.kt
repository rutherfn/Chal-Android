package com.nicholasrutherford.chal.firebase.write.accountinfo

import com.google.firebase.database.FirebaseDatabase
import com.nicholasrutherford.chal.data.firebase.AccountInfo
import com.nicholasrutherford.chal.firebase.USERS
import com.nicholasrutherford.chal.firebase.ageAccountPath
import com.nicholasrutherford.chal.firebase.bioAccountPath
import com.nicholasrutherford.chal.firebase.firstNameAccountPath
import com.nicholasrutherford.chal.firebase.lastNameAccountPath
import com.nicholasrutherford.chal.firebase.timberlog.TimberFirebaseLogImpl
import com.nicholasrutherford.chal.firebase.usernameAccountPath

class WriteAccountInfoImpl : WriteAccountInfo {

    private val ref = FirebaseDatabase.getInstance().getReference(USERS)
    private val timberFirebaseLog = TimberFirebaseLogImpl()

    override fun updateAccountInfo(accountInfo: AccountInfo) {
        updateUsername(username = accountInfo.username)
        updateFirstName(firstName = accountInfo.firstName)
        updateLastName(lastName = accountInfo.lastName)
        updateBio(bio = accountInfo.bio)
        updateAge(age = accountInfo.age)
    }

    override fun updateUsername(username: String) {
        ref.child(usernameAccountPath()).setValue(username)
            .addOnFailureListener { timberFirebaseLog.logAccountUsernameError(username) }
    }

    override fun updateFirstName(firstName: String) {
        ref.child(firstNameAccountPath()).setValue(firstName)
            .addOnFailureListener { timberFirebaseLog.logAccountFirstNameError(firstName) }
    }

    override fun updateLastName(lastName: String) {
        ref.child(lastNameAccountPath()).setValue(lastName)
            .addOnFailureListener { timberFirebaseLog.logAccountLastNameError(lastName) }
    }

    override fun updateBio(bio: String) {
        ref.child(bioAccountPath()).setValue(bio)
            .addOnFailureListener { timberFirebaseLog.logAccountBioError(bio) }
    }

    override fun updateAge(age: Int) {
        ref.child(ageAccountPath()).setValue(age)
            .addOnFailureListener { timberFirebaseLog.logAccountAgeError(age) }
    }
}