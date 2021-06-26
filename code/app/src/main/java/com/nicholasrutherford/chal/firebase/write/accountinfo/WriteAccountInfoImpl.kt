package com.nicholasrutherford.chal.firebase.write.accountinfo

import com.google.firebase.database.FirebaseDatabase
import com.nicholasrutherford.chal.data.firebase.AccountInfo
import com.nicholasrutherford.chal.firebase.USERS
import com.nicholasrutherford.chal.firebase.ageAccountPath
import com.nicholasrutherford.chal.firebase.bioAccountPath
import com.nicholasrutherford.chal.firebase.firstNameAccountPath
import com.nicholasrutherford.chal.firebase.lastNameAccountPath
import com.nicholasrutherford.chal.firebase.usernameAccountPath
import timber.log.Timber

class WriteAccountInfoImpl : WriteAccountInfo {

    private val ref = FirebaseDatabase.getInstance().getReference(USERS)

    override fun updateAccountInfo(accountInfo: AccountInfo) {
        updateUsername(username = accountInfo.username)
        updateFirstName(firstName = accountInfo.firstName)
        updateLastName(lastName = accountInfo.lastName)
        updateBio(bio = accountInfo.bio)
        updateAge(age = accountInfo.age)
    }

    override fun updateUsername(username: String) {
        ref.child(usernameAccountPath()).setValue(username)
            .addOnFailureListener {
                Timber.d("Error writing Firebase field $username to ${usernameAccountPath()}")
            }
    }

    override fun updateFirstName(firstName: String) {
        ref.child(firstNameAccountPath()).setValue(firstName)
            .addOnFailureListener {
                Timber.d("Error writing Firebase field $firstName to ${firstNameAccountPath()}")
            }
    }

    override fun updateLastName(lastName: String) {
        ref.child(lastNameAccountPath()).setValue(lastName)
            .addOnFailureListener {
                Timber.d("Error writing Firebase field $lastName to ${lastNameAccountPath()}")
            }
    }

    override fun updateBio(bio: String) {
        ref.child(bioAccountPath()).setValue(bio)
            .addOnFailureListener {
                Timber.d("Error writing Firebase field $bio to ${bioAccountPath()}")
            }
    }

    override fun updateAge(age: Int) {
        ref.child(ageAccountPath()).setValue(age)
            .addOnFailureListener {
                Timber.d("Error writing Firebase field $age to ${ageAccountPath()}")
            }
    }

}