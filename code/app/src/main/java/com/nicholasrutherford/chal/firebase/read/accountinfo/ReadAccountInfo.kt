package com.nicholasrutherford.chal.firebase.read.accountinfo

import com.nicholasrutherford.chal.firebase.ChalFirebase

interface ReadAccountInfo : ChalFirebase {
    fun getAccountInfo()
}