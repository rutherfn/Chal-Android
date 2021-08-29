package com.nicholasrutherford.chal.account.redesignlogin

import android.app.Application
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicholasrutherford.chal.KeyboardImpl
import com.nicholasrutherford.chal.Networkimpl
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.firebase.auth.ChalFirebaseAuthImpl
import com.nicholasrutherford.chal.firebase.auth.LoginStatus
import com.nicholasrutherford.chal.navigationimpl.login.RedesignLoginNavigationImpl
import kotlinx.android.synthetic.main.fragment_login.view.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@Suppress("MagicNumber")
const val AT_SYMBOL = "@"

class RedesignLoginViewModel @Inject constructor(
    private val application: Application,
    private val navigation: RedesignLoginNavigationImpl,
    private val keyboard: KeyboardImpl,
    private val network: Networkimpl,
    private val firebaseAuth: ChalFirebaseAuthImpl
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

    private fun isEmailEmpty(email: String): Boolean {
        val empty = application.getString(R.string.empty_string)
        return email == empty
    }

    private fun isEmailMeetRequirements(email: String): Boolean {
        val dotCom = application.getString(R.string.dot_com)
        return email.contains(AT_SYMBOL) && email.contains(dotCom)
    }

    private fun isPasswordEmpty(password: String): Boolean {
        val empty = application.getString(R.string.empty_string)
        return password == empty
    }

    fun updateEmailAfterTextChanged(email: String) {
        when {
            isEmailMeetRequirements(email) -> {
                emailErrorNotVisible()
            }
            isEmailEmpty(email) -> {
                emailErrorNotVisible()
            }
            else -> {
                emailErrorVisible()
            }
        }
    }

    fun passwordEditAction(etEmail: EditText, etPassword: EditText, actionId: Int, email: String) {
        if (actionId == EditorInfo.IME_ACTION_DONE && !isEmailEmpty(email) && isEmailMeetRequirements(email)) {
            onLogInClicked(etEmail, etPassword)
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

    fun onLogInClicked(etEmail: EditText, etPassword: EditText) {
        keyboard.hideKeyBoard()

        if (isEmailEmpty(etEmail.text.toString()) || isPasswordEmpty(etPassword.etPassword.toString())) {
            alertErrorMessage = application.applicationContext.getString(R.string.error_fields_are_not_correct_log_in)
            showUserLoginErrorAlert()
        } else {
            if (!isUserReadyToLogIn()) {
                showUserLoginErrorAlert()
            } else {
                navigation.showAcProgress()

                val email = etEmail.text.toString()
                val password = etPassword.text.toString()

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
        navigation.forgotPassword()
    }

    class RedesignLoginViewStateImpl : RedesignLoginViewState {
        override var emailErrorImageVisible: Boolean = false
        override var emailErrorTextVisible: Boolean = false
    }
}