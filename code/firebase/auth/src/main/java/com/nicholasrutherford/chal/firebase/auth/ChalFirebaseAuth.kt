package com.nicholasrutherford.chal.firebase.auth

interface ChalFirebaseAuth  {
    var isLoggedIn: Boolean
    var uid: String?
    fun signInWithEmailAndPassword(email: String, password: String)
    fun setLoginStatusStateAsNotUpdated()
    fun sendPasswordResetEmail(resetEmail: String)
    fun setPasswordResetEmailStateAsNotUpdated()
}