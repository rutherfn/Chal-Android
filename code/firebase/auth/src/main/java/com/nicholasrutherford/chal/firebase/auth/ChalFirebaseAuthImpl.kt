package com.nicholasrutherford.chal.firebase.auth

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class ChalFirebaseAuthImpl @Inject constructor() : ChalFirebaseAuth {

    private val _loginStatusState = MutableStateFlow(LoginStatus.NONE)
    val loginStatusState: StateFlow<LoginStatus> = _loginStatusState

    val firebaseAuth = FirebaseAuth.getInstance()

    override var isLoggedIn: Boolean = firebaseAuth.currentUser != null

    override var uid: String? = firebaseAuth.uid

    override fun signInWithEmailAndPassword(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                it.let { taskResult ->
                    if (taskResult.isSuccessful) {
                        _loginStatusState.value = LoginStatus.LOGGED_IN
                        // set tje flow here
                    }
                }
            }.addOnFailureListener {
                _loginStatusState.value = LoginStatus.ERROR
            }
    }
}