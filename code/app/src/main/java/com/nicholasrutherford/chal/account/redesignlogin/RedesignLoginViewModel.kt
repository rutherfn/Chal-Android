package com.nicholasrutherford.chal.account.redesignlogin

import android.app.Application
import android.os.CountDownTimer
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.nicholasrutherford.chal.KeyboardImpl
import com.nicholasrutherford.chal.Networkimpl
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.helpers.Helper
import com.nicholasrutherford.chal.navigationimpl.login.RedesignLoginNavigationImpl
import kotlinx.android.synthetic.main.fragment_login.view.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@Suppress("MagicNumber")
const val AT_SYMBOL = "@"

class RedesignLoginViewModel @Inject constructor(
    private val application: Application,
    private val navigation: RedesignLoginNavigationImpl,
    private val keyboard: KeyboardImpl,
    private val network: Networkimpl
    ) : ViewModel() {

    private val _loginSuccessState = MutableStateFlow(false)
    val loginSuccessState: StateFlow<Boolean> = _loginSuccessState

    val viewState = RedesignLoginViewStateImpl()

    private val helper = Helper()

    private var alertErrorMessage: String = ""

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

    fun emailErrorVisible() {
        viewState.emailErrorImageVisible = true
        viewState.emailErrorTextVisible = true
    }

    fun emailErrorNotVisible() {
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

                FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener {
                        it.let { task ->
                            if (task.isSuccessful) {

                                val timer = object : CountDownTimer(1000, 100) {

                                    override fun onTick(millisUntilFinished: Long) {}

                                    override fun onFinish() {
                                        _loginSuccessState.value = true
                                        navigation.hideAcProgress()
                                        navigation.loginToApp()
                                    }
                                }
                                timer.start()
                            }
                        }
                    }.addOnFailureListener {
                        navigation.hideAcProgress()
                        alertErrorMessage = application.applicationContext.getString(R.string.error_login_no_account_associated_with_email)
                        showUserLoginErrorAlert()
                        // show a alert stating that current user account doesn't exist
                    }
            }
        }
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