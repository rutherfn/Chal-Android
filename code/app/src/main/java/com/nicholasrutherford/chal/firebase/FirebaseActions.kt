package com.nicholasrutherford.chal.firebase

import com.google.firebase.auth.FirebaseAuth

private var mAuth: FirebaseAuth? = null

fun logUserOut() {
    mAuth = FirebaseAuth.getInstance()

    mAuth?.signOut()
}