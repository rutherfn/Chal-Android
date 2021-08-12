package com.nicholasrutherford.chal.firebase.write.accountinfo

import com.nicholasrutherford.chal.data.firebase.AccountInfo
import com.nicholasrutherford.chal.firebase.ChalFirebase

interface WriteAccountInfo : ChalFirebase {
    fun updateAccountInfo(uid: String, accountInfo: AccountInfo)
    fun updateUsername(uid: String, username: String)
    fun updateFirstName(uid: String, firstName: String)
    fun updateLastName(uid: String, lastName: String)
    fun updateBio(uid: String, bio: String)
    fun updateAge(uid: String, age: Int)
}