package com.nicholasrutherford.chal.firebase.auth

import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class ChalFirebaseAuthImpl @Inject constructor() : ChalFirebaseAuth {

    override val auth = FirebaseAuth.getInstance()

    override val isLoggedIn: Boolean = auth.currentUser != null

    override val uid: String? = auth.uid

    override fun logUserOut() = auth.signOut()

    override fun sendEmailVerification() {
        auth.currentUser?.sendEmailVerification()
    }
}