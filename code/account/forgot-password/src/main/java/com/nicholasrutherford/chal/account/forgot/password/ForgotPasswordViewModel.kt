package com.nicholasrutherford.chal.account.forgot.password

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import com.nicholasrutherford.chal.account.validation.AccountValidation
import com.nicholasrutherford.chal.firebase.auth.ChalFirebaseAuth
import com.nicholasrutherford.chal.ui.base_vm.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ForgotPasswordViewModel @ViewModelInject constructor(
    private val application: Application,
    private val firebaseAuth: ChalFirebaseAuth,
    private val accountValidation: AccountValidation
) : BaseViewModel() {

    var alertTitle = application.getString(R.string.empty_string)
    var alertMessage = application.getString(R.string.empty_string)

    private val _passwordResetEmailStatus = MutableStateFlow(PasswordResetEmailStatus.NONE)
    val passwordResetEmailStatus: StateFlow<PasswordResetEmailStatus> = _passwordResetEmailStatus

    val viewState = ForgotPasswordViewStateImpl()

    fun alertUpdated(title: String, message: String) {
        alertTitle = title
        alertMessage = message
        setShouldShowAlertAsUpdated()
    }

    fun onViewFromPasswordResetEmailStatus(status: PasswordResetEmailStatus) {
        setShouldShowDismissProgressAsUpdated()
        if (status == PasswordResetEmailStatus.SUCCESSFUL) {
            alertUpdated(
                title = application.getString(R.string.email_sent),
                message = application.getString(
                    R.string.email_to_reset_password_sent_please_check_your_email_for_directions_on_how_to_reset_password
                )
            )
        } else if (status == PasswordResetEmailStatus.ERROR) {
            alertUpdated(
                title = application.getString(R.string.email_does_not_exist),
                message = application.getString(R.string.current_email_does_not_exist_please_try_again_with_a_valid_email_register_to_chal)
            )
        }

        _passwordResetEmailStatus.value = PasswordResetEmailStatus.NONE
    }

    internal fun showErrorEmail() {
        viewState.errorForgotPasswordVisible = true
        setViewStateAsUpdated()
    }

   internal  fun hideErrorEmail() {
        viewState.errorForgotPasswordVisible = false
        setViewStateAsUpdated()
    }

    fun checkIfEmailIsEnteredCorrectly(email: String) {
        if (accountValidation.isEmailMeetRequirements(email)) {
            hideErrorEmail()
        } else {
            showErrorEmail()
        }
    }

    fun onEmailEditAction(email: String, actionId: Int) {
        if (accountValidation.isEmailValid(email)) {
            onDoneClicked(email)
        }
    }

    fun onDoneClicked(resetEmail: String) {
        if (viewState.errorForgotPasswordVisible) {
            alertUpdated(
                title = application.getString(R.string.not_a_valid_email),
                message = application.getString(R.string.sorry_but_this_is_not_a_valid_email_please_enter_a_valid_email_and_try_again)
            )
        } else {
            setShouldShowProgressAsUpdated()
            attemptToSendPasswordEmail(resetEmail)
        }
    }

    fun attemptToSendPasswordEmail(resetEmail: String) {
        firebaseAuth.auth.sendPasswordResetEmail(resetEmail)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    _passwordResetEmailStatus.value = PasswordResetEmailStatus.SUCCESSFUL
                } else {
                    _passwordResetEmailStatus.value = PasswordResetEmailStatus.ERROR
                }
            }.addOnFailureListener {
                _passwordResetEmailStatus.value = PasswordResetEmailStatus.ERROR
            }
    }

    inner class ForgotPasswordViewStateImpl: ForgotPasswordViewState {
        override var errorForgotPasswordVisible = false
    }
}