package com.nicholasrutherford.chal.account.redesignlogin

import android.app.Application
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicholasrutherford.chal.KeyboardImpl
import com.nicholasrutherford.chal.Networkimpl
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.account.validation.AccountValidationImpl
import com.nicholasrutherford.chal.firebase.auth.ChalFirebaseAuthImpl
import com.nicholasrutherford.chal.firebase.auth.LoginStatus
import com.nicholasrutherford.chal.navigationimpl.login.RedesignLoginNavigationImpl
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class RedesignLoginViewModel @Inject constructor(
    private val application: Application,
    private val navigation: RedesignLoginNavigationImpl,
    private val keyboard: KeyboardImpl,
    private val network: Networkimpl,
    private val firebaseAuth: ChalFirebaseAuthImpl,
    private val accountValidation: AccountValidationImpl
    ) : ViewModel() {

    init {
        viewModelScope.launch {
            firebaseAuth.loginStatusState.collect { status ->
                if (status == LoginStatus.ERROR) {
                    showLoginStatusError()
                } else if (status == LoginStatus.LOGGED_IN) {
                    showloginStatusSuccess()
                }
            }
        }
    }

    private var alertErrorMessage: String = ""

    val viewState = RedesignLoginViewStateImpl()

    fun updateEmailAfterTextChanged(email: String) {
        when {
            accountValidation.isEmailMeetRequirements(email) -> {
                emailErrorNotVisible()
            }
            accountValidation.isEmailEmpty(email) -> {
                emailErrorNotVisible()
            }
            else -> {
                emailErrorVisible()
            }
        }
    }

    fun passwordEditAction(email: String, password: String, actionId: Int) {
        if (actionId == EditorInfo.IME_ACTION_DONE && accountValidation.isEmailValid(email)) {
            onLogInClicked(email, password)
        } else if (actionId == EditorInfo.IME_ACTION_DONE) {
            keyboard.hideKeyBoard()
        }
    }

    private fun emailErrorVisible() {
        viewState.emailErrorImageVisible = true
        viewState.emailErrorTextVisible = true
    }

    private fun emailErrorNotVisible() {
        viewState.emailErrorImageVisible = false
        viewState.emailErrorTextVisible = false
    }

    private fun isUserReadyToLogIn(): Boolean {
        return if (viewState.emailErrorImageVisible || viewState.emailErrorImageVisible) {
            alertErrorMessage = application.applicationContext.getString(R.string.error_fields_are_not_correct_log_in)
            false
        } else if (!network.isConnected()) {
            alertErrorMessage = application.applicationContext.getString(R.string.error_no_internet_log_in)
            false
        } else {
            true
        }
    }

    fun onLogInClicked(email: String, password: String) {
        keyboard.hideKeyBoard()

        if (accountValidation.isPasswordOrEmailEmpty(email, password)) {
            alertErrorMessage = application.applicationContext.getString(R.string.error_fields_are_not_correct_log_in)
            showUserLoginErrorAlert()
        } else {
            if (!isUserReadyToLogIn()) {
                showUserLoginErrorAlert()
            } else {
                navigation.showAcProgress()

                firebaseAuth.signInWithEmailAndPassword(email = email, password = password)
            }
        }
    }

    fun showloginStatusSuccess() {
        navigation.hideAcProgress()
        navigation.loginToApp()
    }

    fun showLoginStatusError() {
        navigation.hideAcProgress()
        alertErrorMessage = application.applicationContext.getString(R.string.error_login_no_account_associated_with_email)
        showUserLoginErrorAlert()
    }

    fun showUserLoginErrorAlert() {
        navigation.errorLogin(errorMessageText = alertErrorMessage)
    }

    fun onSignUpClicked() {
        navigation.signUp()
    }

    fun onForgotPasswordClicked() {
        navigation.showForgotPassword()
    }

    class RedesignLoginViewStateImpl : RedesignLoginViewState {
        override var emailErrorImageVisible: Boolean = false
        override var emailErrorTextVisible: Boolean = false
    }
}