package com.nicholasrutherford.chal.firebase.auth

import com.google.firebase.auth.FirebaseAuth

interface ChalFirebaseAuth  {
    val auth: FirebaseAuth
    val isLoggedIn: Boolean
    val uid: String?
    fun getSignInWithEmailAndPasswordLoginStatus(email: String, password: String): LoginStatus
    fun sendPasswordResetEmail(resetEmail: String)
    fun setPasswordResetEmailStateAsNotUpdated()
}