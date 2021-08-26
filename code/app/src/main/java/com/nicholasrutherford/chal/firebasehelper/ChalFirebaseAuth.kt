package com.nicholasrutherford.chal.firebasehelper

import com.nicholasrutherford.chal.firebase.ChalFirebase

interface ChalFirebaseAuth : ChalFirebase {
    var isLoggedIn: Boolean
    var uid: String?
}