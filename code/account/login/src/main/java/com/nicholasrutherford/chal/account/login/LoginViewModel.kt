package com.nicholasrutherford.chal.account.login

import android.app.Application
import android.view.inputmethod.EditorInfo
import androidx.hilt.lifecycle.ViewModelInject
import com.nicholasrutherford.chal.Network
import com.nicholasrutherford.chal.account.validation.AccountValidation
import com.nicholasrutherford.chal.firebase.auth.ChalFirebaseAuth
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

    private val _loginStatusState = MutableStateFlow(LoginStatus.NONE)
    val loginStatusState: StateFlow<LoginStatus> = _loginStatusState

    var alertErrorTitle: String = application.applicationContext.getString(R.string.error_cant_log_in)
    var alertErrorMessage: String = ""

    val viewState = LoginViewStateImpl()

    fun onLoginStatesResult(status: LoginStatus) {
        when (status) {
            LoginStatus.NONE -> {
                // nothing to do here
            }
            LoginStatus.ERROR -> {
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
        if (accountValidation.isPasswordOrEmailEmpty(email, password)) {
            alertErrorMessage =
                application.applicationContext.getString(R.string.error_fields_are_not_correct_log_in)
            setShouldShowAlertAsUpdated()
        } else {
            if (!isUserReadyToLogIn()) {
                setShouldShowAlertAsUpdated()
            } else {
                setLoginStatusState(email, password)
            }
        }
    }

    fun setLoginStatusStateAsNotUpdated() {
        _loginStatusState.value = LoginStatus.NONE
    }

    fun setLoginStatusState(email: String, password: String) {
        setShouldShowProgressAsUpdated()
        firebaseAuth.auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    _loginStatusState.value = LoginStatus.SUCCESSFUL
                } else if (it.isCanceled) {
                    _loginStatusState.value = LoginStatus.ERROR
                }
            }.addOnFailureListener {
                _loginStatusState.value = LoginStatus.ERROR
            }
    }

    private fun showloginStatusSuccess() {
        setShouldShowDismissProgressAsUpdated()
        navigation.showNewsFeed()
    }

    fun showLoginStatusError() {
        setShouldShowDismissProgressAsUpdated()
        alertErrorMessage =
            application.applicationContext.getString(R.string.error_login_no_account_associated_with_email)
        setShouldShowAlertAsUpdated()
    }

    fun onSignUpClicked() {
        navigation.showSignIn()
    }

    fun onForgotPasswordClicked() {
        navigation.showForgotPassword()
    }

    inner class LoginViewStateImpl : LoginViewState {
        override var emailErrorImageVisible: Boolean = false
        override var emailErrorTextVisible: Boolean = false
    }
}
