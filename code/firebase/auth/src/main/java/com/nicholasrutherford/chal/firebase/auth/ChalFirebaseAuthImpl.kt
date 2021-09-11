package com.nicholasrutherford.chal.firebase.auth

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class ChalFirebaseAuthImpl @Inject constructor() : ChalFirebaseAuth {

    private val _sendPasswordResetEmailState = MutableStateFlow(SendPasswordResetEmailStatus.NONE)
    val sendPasswordResetEmailState: StateFlow<SendPasswordResetEmailStatus> = _sendPasswordResetEmailState

    override val auth = FirebaseAuth.getInstance()

    override val isLoggedIn: Boolean = auth.currentUser != null

    override val uid: String? = auth.uid

    override fun getSignInWithEmailAndPasswordLoginStatus(email: String, password: String): LoginStatus {
        val _loginStatusState = MutableStateFlow(LoginStatus.UNSUCCESSFUL)

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                        _loginStatusState.value = LoginStatus.SUCCESSFUL
                } else if (it.isCanceled) {
                    _loginStatusState.value = LoginStatus.UNSUCCESSFUL
                }
            }.addOnFailureListener {
                _loginStatusState.value = LoginStatus.ERROR
            }

        return _loginStatusState.value
    }

    override fun sendPasswordResetEmail(resetEmail: String) {
        auth.sendPasswordResetEmail(resetEmail)
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