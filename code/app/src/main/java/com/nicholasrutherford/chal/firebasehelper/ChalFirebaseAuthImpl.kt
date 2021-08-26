package com.nicholasrutherford.chal.firebasehelper

import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class ChalFirebaseAuthImpl @Inject constructor() : ChalFirebaseAuth {

    val firebaseAuth = FirebaseAuth.getInstance()

    override var isLoggedIn: Boolean = firebaseAuth.currentUser != null

    override var uid: String? = firebaseAuth.uid
}