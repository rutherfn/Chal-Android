package com.nicholasrutherford.chal.firebase.auth

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class ChalFirebaseAuthImpl @Inject constructor() : ChalFirebaseAuth {

    private val _loginStatusState = MutableStateFlow(LoginStatus.NONE)
    val loginStatusState: StateFlow<LoginStatus> = _loginStatusState

    private val _sendPasswordResetEmailState = MutableStateFlow(SendPasswordResetEmailStatus.NONE)
    val sendPasswordResetEmailState: StateFlow<SendPasswordResetEmailStatus> = _sendPasswordResetEmailState

    val firebaseAuth = FirebaseAuth.getInstance()

    override var isLoggedIn: Boolean = firebaseAuth.currentUser != null

    override var uid: String? = firebaseAuth.uid

    override fun signInWithEmailAndPassword(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                    if (it.isSuccessful) {
                        _loginStatusState.value = LoginStatus.LOGGED_IN
                }
            }.addOnFailureListener {
                _loginStatusState.value = LoginStatus.ERROR
            }
    }

    override fun sendPasswordResetEmail(resetEmail: String) {
        firebaseAuth.sendPasswordResetEmail(resetEmail)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    _sendPasswordResetEmailState.value = SendPasswordResetEmailStatus.SUCCESSFUL
                } else {
                    _sendPasswordResetEmailState.value = SendPasswordResetEmailStatus.ERROR
                }
            }.addOnFailureListener {
                _sendPasswordResetEmailState.value = SendPasswordResetEmailStatus.ERROR
            }
    }

    override fun setPasswordResetEmailStateAsNotUpdated() {
        _sendPasswordResetEmailState.value = SendPasswordResetEmailStatus.NONE
    }
}