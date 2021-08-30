package com.nicholasrutherford.chal.account.forgotpasswordredesign

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicholasrutherford.chal.firebase.auth.ChalFirebaseAuthImpl
import com.nicholasrutherford.chal.firebase.auth.SendPasswordResetEmailStatus
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class ForgotPasswordRedesignViewModel @Inject constructor(
    private val firebaseAuth: ChalFirebaseAuthImpl,
    private val navigation: ForgotPasswordRedesignNavigationImpl
) : ViewModel() {

    val viewState = ForgotPasswordRedesignViewStateImpl()

    init {
        viewModelScope.launch {
            firebaseAuth.sendPasswordResetEmailState.collect { status ->
                if (status == SendPasswordResetEmailStatus.ERROR) {
                    // do something here
                } else if (status == SendPasswordResetEmailStatus.SUCCESSFUL) {

                }
            }
        }
    }

    fun navigationToLogin() = navigation.showLogin()

    fun showErrorEmail() { viewState.errorForgotPasswordVisible = true }

    fun hideErrorEmail() { viewState.errorForgotPasswordVisible = false }

    inner class ForgotPasswordRedesignViewStateImpl : ForgotPasswordRedesignViewState {
        override var errorForgotPasswordVisible = false
    }
}