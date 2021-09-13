package com.nicholasrutherford.chal.firebase.auth

import com.google.firebase.auth.FirebaseAuth

interface ChalFirebaseAuth  {
    val auth: FirebaseAuth
    val isLoggedIn: Boolean
    val uid: String?
}