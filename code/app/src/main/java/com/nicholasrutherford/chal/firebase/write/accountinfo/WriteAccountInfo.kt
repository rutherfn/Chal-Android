package com.nicholasrutherford.chal.firebase.write.accountinfo

import com.nicholasrutherford.chal.data.firebase.AccountInfo
import com.nicholasrutherford.chal.firebase.ChalFirebase

interface WriteAccountInfo : ChalFirebase {
    fun updateAccountInfo(accountInfo: AccountInfo)
    fun updateUsername(username: String)
    fun updateFirstName(firstName: String)
    fun updateLastName(lastName: String)
    fun updateBio(bio: String)
    fun updateAge(age: Int)
}