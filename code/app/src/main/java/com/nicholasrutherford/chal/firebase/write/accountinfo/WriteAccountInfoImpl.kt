package com.nicholasrutherford.chal.firebase.write.accountinfo

import com.google.firebase.database.FirebaseDatabase
import com.nicholasrutherford.chal.data.firebase.AccountInfo
import com.nicholasrutherford.chal.firebase.USERS
import com.nicholasrutherford.chal.firebase.ageAccountPath
import com.nicholasrutherford.chal.firebase.bioAccountPath
import com.nicholasrutherford.chal.firebase.challengeBannerTypePath
import com.nicholasrutherford.chal.firebase.firstNameAccountPath
import com.nicholasrutherford.chal.firebase.lastNameAccountPath
import com.nicholasrutherford.chal.firebase.usernameAccountPath
import timber.log.Timber

class WriteAccountInfoImpl : WriteAccountInfo {

    private val ref = FirebaseDatabase.getInstance().getReference(USERS)

    override fun updateAccountInfo(uid: String, accountInfo: AccountInfo) {
        updateUsername(uid = uid, username = accountInfo.username)
        updateFirstName(uid = uid,firstName = accountInfo.firstName)
        updateLastName(uid = uid, lastName = accountInfo.lastName)
        updateBio(uid = uid, bio = accountInfo.bio)
        updateAge(uid = uid, age = accountInfo.age)
    }

    override fun updateUsername(uid: String, username: String) {
        ref.child(usernameAccountPath(uid)).setValue(username)
            .addOnFailureListener {
                Timber.d("Error writing Firebase field $username to ${usernameAccountPath(uid)}")
            }
    }

    override fun updateFirstName(uid: String, firstName: String) {
        ref.child(firstNameAccountPath(uid)).setValue(firstName)
            .addOnFailureListener {
                Timber.d("Error writing Firebase field $firstName to ${firstNameAccountPath(uid)}")
            }
    }

    override fun updateLastName(uid: String, lastName: String) {
        ref.child(lastNameAccountPath(uid)).setValue(lastName)
            .addOnFailureListener {
                Timber.d("Error writing Firebase field $lastName to ${lastNameAccountPath(uid)}")
            }
    }

    override fun updateBio(uid: String, bio: String) {
        ref.child(bioAccountPath(uid)).setValue(bio)
            .addOnFailureListener {
                Timber.d("Error writing Firebase field $bio to ${bioAccountPath(uid)}")
            }
    }

    override fun updateAge(uid: String, age: Int) {
        ref.child(ageAccountPath(uid)).setValue(age)
            .addOnFailureListener {
                Timber.d("Error writing Firebase field $age to ${ageAccountPath(uid)}")
            }
    }

    override fun updateChallengeBannerType(uid: String, bannerType: Int) {
        println(bannerType)
        ref.child(challengeBannerTypePath(uid)).setValue(bannerType)
            .addOnFailureListener {
                Timber.d("Error writing Firebase field $bannerType to ${challengeBannerTypePath(uid)}")
            }
    }
}