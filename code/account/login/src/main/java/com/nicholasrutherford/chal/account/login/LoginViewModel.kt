package com.nicholasrutherford.chal.account.login

import android.app.Application
import android.view.inputmethod.EditorInfo
import androidx.hilt.lifecycle.ViewModelInject
import com.nicholasrutherford.chal.Network
import com.nicholasrutherford.chal.account.validation.AccountValidation
import com.nicholasrutherford.chal.firebase.auth.ChalFirebaseAuth
import com.nicholasrutherford.chal.firebase.auth.LoginStatus
import com.nicholasrutherford.chal.ui.base_vm.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LoginViewModel @ViewModelInject constructor(
    private val application: Application,
    private val network: Network,
    private val navigation: LoginNavigation,
    private val firebaseAuth: ChalFirebaseAuth,
    private val accountValidation: AccountValidation
    ) : BaseViewModel() {

    private val _loginStatusState = MutableStateFlow(LoginStatus.UNSUCCESSFUL)
    val loginStatusState: StateFlow<LoginStatus> = _loginStatusState

    private var alertErrorMessage: String = ""

    val viewState = LoginViewStateImpl()

    fun onLoginStatesResult(status: LoginStatus) {
        when (status) {
            LoginStatus.ERROR -> {
                showLoginStatusError()
            }
            LoginStatus.UNSUCCESSFUL -> {
                showLoginStatusError()
            }
            LoginStatus.SUCCESSFUL -> {
                showloginStatusSuccess()
            }
        }
        setLoginStatusStateAsNotUpdated()
    }

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
        }
    }

    private fun emailErrorVisible() {
        viewState.emailErrorImageVisible = true
        viewState.emailErrorTextVisible = true

        setViewStateAsUpdated()
    }

    private fun emailErrorNotVisible() {
        viewState.emailErrorImageVisible = false
        viewState.emailErrorTextVisible = false
        setViewStateAsUpdated()
    }

    private fun isUserReadyToLogIn(): Boolean {
        return if (viewState.emailErrorImageVisible || viewState.emailErrorImageVisible) {
            alertErrorMessage =
                application.applicationContext.getString(R.string.error_fields_are_not_correct_log_in)
            false
        } else if (!network.isConnected()) {
            alertErrorMessage =
                application.applicationContext.getString(R.string.error_no_internet_log_in)
            false
        } else {
            true
        }
    }

    fun onLogInClicked(email: String, password: String) {
        //     keyboard.hideKeyBoard()

        if (accountValidation.isPasswordOrEmailEmpty(email, password)) {
            alertErrorMessage =
                application.applicationContext.getString(R.string.error_fields_are_not_correct_log_in)
            showUserLoginErrorAlert()
        } else {
            if (!isUserReadyToLogIn()) {
                showUserLoginErrorAlert()
            } else {
                setLoginStatusState(email, password)
            }
        }
    }

    fun setLoginStatusStateAsNotUpdated() {
        _loginStatusState.value = LoginStatus.UNSUCCESSFUL
    }

    fun setLoginStatusState(email: String, password: String) {
        firebaseAuth.auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    _loginStatusState.value = LoginStatus.SUCCESSFUL
                } else if (it.isCanceled) {
                    _loginStatusState.value = LoginStatus.UNSUCCESSFUL
                }
            }.addOnFailureListener {
                _loginStatusState.value = LoginStatus.ERROR
            }
    }

    private fun showloginStatusSuccess() {
        // navigation.hideAcProgress()
        // navigation.loginToApp()
    }

    fun showLoginStatusError() {
        //  navigation.hideAcProgress()
        alertErrorMessage =
            application.applicationContext.getString(R.string.error_login_no_account_associated_with_email)
        showUserLoginErrorAlert()
    }

    private fun showUserLoginErrorAlert() {
        //  navigation.errorLogin(errorMessageText = alertErrorMessage)
    }

    fun onSignUpClicked() {
        //    navigation.signUp()
    }

    fun onForgotPasswordClicked() {
        navigation.showForgotPassword()
    }

    inner class LoginViewStateImpl : LoginViewState {
        override var emailErrorImageVisible: Boolean = false
        override var emailErrorTextVisible: Boolean = false
    }
}
