package com.nicholasrutherford.chal.account.login

import android.content.Context
import android.os.CountDownTimer
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.helpers.Helper
import com.nicholasrutherford.chal.navigationimpl.LoginNavigationImpl
import kotlinx.android.synthetic.main.fragment_login.view.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LoginViewModel(private val loginActivity: LoginActivity, private val appContext: Context) : ViewModel() {

    private val _loginSuccessState = MutableStateFlow(false)
    val loginSuccessState: StateFlow<Boolean> = _loginSuccessState

    val viewState = LoginViewStateImpl()

    private val navigation = LoginNavigationImpl()
    private val helper = Helper()

    private var alertErrorMessage: String = ""

    fun updateEmailAfterTextChanged(email: String) {
        if(email.contains("@") && email.contains(".com")) {
            emailErrorNotVisible()
        } else if(email == "") {
            emailErrorNotVisible()
        } else {
            emailErrorVisible()
        }
    }

    fun passwordEditAction(etEmail: EditText, etPassword: EditText, actionId: Int, email: String) {
        if (actionId == EditorInfo.IME_ACTION_DONE && email != "" && email.contains("@") && email.contains(".com")) {
            onLogInClicked(etEmail, etPassword)
        } else if(actionId == EditorInfo.IME_ACTION_DONE) {
            helper.hideSoftKeyBoard(loginActivity)
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

    fun onLogInClicked(etEmail: EditText, etPassword: EditText) {
        helper.hideSoftKeyBoard(loginActivity)

        if(etEmail.text.toString().isEmpty() || etPassword.etPassword.toString().isEmpty()) {
            alertErrorMessage = appContext.getString(R.string.error_fields_are_not_correct_log_in)
            showUserLoginErrorAlert()
        } else {
            if (!isUserReadyToLogIn()) {
                showUserLoginErrorAlert()
            } else {
                navigation.showAcProgress(loginActivity)

                val email = etEmail.text.toString()
                val password = etPassword.text.toString()

                FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener {
                        it.let { task ->
                            if (task.isSuccessful) {

                                val timer = object: CountDownTimer(1000, 100) {

                                    override fun onTick(millisUntilFinished: Long) {}

                                    override fun onFinish() {
                                        _loginSuccessState.value = true
                                        navigation.hideAcProgress()
                                        navigation.loginToApp(loginActivity)
                                    }
                                }
                                timer.start()
                            }
                        }
                    }.addOnFailureListener {
                        navigation.hideAcProgress()
                        alertErrorMessage = appContext.getString(R.string.error_login_no_account_associated_with_email)
                        showUserLoginErrorAlert()
                        // show a alert stating that current user account doesn't exist
                    }
            }
        }
    }

    private fun isUserReadyToLogIn(): Boolean {
        return if(viewState.emailErrorImageVisible || viewState.emailErrorImageVisible) {
            alertErrorMessage = appContext.getString(R.string.error_fields_are_not_correct_log_in)
            false
        } else if(!helper.isConnected()) {
            alertErrorMessage = appContext.getString(R.string.error_no_internet_log_in)
            false
        } else {
            true
        }
    }

    fun showUserLoginErrorAlert() {
        navigation.errorLogin(errorMessageText = alertErrorMessage, loginActivity = loginActivity)
    }

    fun onSignUpClicked() {
        navigation.signUp(appContext, loginActivity)
    }

    fun onForgotPasswordClicked() {
        navigation.forgotPassword(appContext, loginActivity)
    }

    class LoginViewStateImpl: LoginViewState {
        override var emailErrorImageVisible = false
        override var emailErrorTextVisible = false
    }
}