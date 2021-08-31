package com.nicholasrutherford.chal.account.forgotpasswordredesign

import android.app.Application
import android.view.inputmethod.EditorInfo
import com.nicholasrutherford.chal.KeyboardImpl
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.account.validation.AccountValidationImpl
import com.nicholasrutherford.chal.firebase.auth.ChalFirebaseAuthImpl
import com.nicholasrutherford.chal.firebase.auth.SendPasswordResetEmailStatus
import com.nicholasrutherford.chal.ui.base_vm.BaseViewModel
import javax.inject.Inject

class ForgotPasswordRedesignViewModel @Inject constructor(
    private val application: Application,
    val firebaseAuth: ChalFirebaseAuthImpl,
    private val keyboard: KeyboardImpl,
    private val navigation: ForgotPasswordRedesignNavigationImpl,
    private val accountValidation: AccountValidationImpl
) : BaseViewModel() {

    val viewState = ForgotPasswordRedesignViewStateImpl()

    fun updateViewFromPasswordResetEmailStatus(status: SendPasswordResetEmailStatus) {
        if (status == SendPasswordResetEmailStatus.SUCCESSFUL) {
            navigation.hideAcProgress()

            val title = application.getString(R.string.email_sent)
            val message = application.getString(
                R.string.email_to_reset_password_sent_please_check_your_email_for_directions_on_how_to_reset_password
            )
            showAlert(title, message)
        } else if (status == SendPasswordResetEmailStatus.ERROR) {
            navigation.hideAcProgress()

            val title = application.getString(R.string.email_does_not_exist)
            val message = application.getString(R.string.current_email_does_not_exist_please_try_again_with_a_valid_email_register_to_chal
            )
            showAlert(title, message)
        }

        firebaseAuth.setPasswordResetEmailStateAsNotUpdated()
    }

    fun showErrorEmail() {
        viewState.errorForgotPasswordVisible = true
        setViewStateAsUpdated()
    }

    fun hideErrorEmail() {
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
        if (actionId == EditorInfo.IME_ACTION_DONE && accountValidation.isEmailValid(email)) {
            onDoneClicked(email)
        } else if (actionId == EditorInfo.IME_ACTION_DONE) {
            keyboard.hideKeyBoard()
        }
    }

    private fun showAlert(title: String, message: String) {
        navigation.forgotPasswordAlert(
            title = title,
            message = message
        )
    }

    fun onDoneClicked(resetEmail: String) {
        if (viewState.errorForgotPasswordVisible) {
            val title = application.getString(R.string.not_a_valid_email)
            val message = application.getString(R.string.sorry_but_this_is_not_a_valid_email_please_enter_a_valid_email_and_try_again)
            showAlert(title, message)
        } else {
            navigation.showAcProgress()
            firebaseAuth.sendPasswordResetEmail(resetEmail = resetEmail)
        }
    }

    inner class ForgotPasswordRedesignViewStateImpl : ForgotPasswordRedesignViewState {
        override var errorForgotPasswordVisible = false
    }
}