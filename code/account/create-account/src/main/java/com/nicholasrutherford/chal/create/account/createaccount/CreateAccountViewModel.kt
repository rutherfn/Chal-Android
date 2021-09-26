package com.nicholasrutherford.chal.create.account.createaccount

import android.app.Application
import android.os.Handler
import android.os.Looper
import androidx.hilt.lifecycle.ViewModelInject
import com.nicholasrutherford.chal.account.validation.AccountValidation
import com.nicholasrutherford.chal.create.account.R
import com.nicholasrutherford.chal.firebase.auth.ChalFirebaseAuth
import com.nicholasrutherford.chal.helper.constants.LOADING_DELAY
import com.nicholasrutherford.chal.ui.base_vm.BaseViewModel

class CreateAccountViewModel @ViewModelInject constructor(
    private val accountValidation: AccountValidation,
    private val application: Application,
    private val chalFirebaseAuth: ChalFirebaseAuth,
    private val navigation: CreateAccountNavigation
) : BaseViewModel() {

    var alertTitle = application.getString(R.string.empty_string)
    var alertMessage = application.getString(R.string.empty_string)

    private var isEmailEmpty: Boolean = false
    private var isEmailCorrectFormat: Boolean = false

    private var isPasswordEmpty: Boolean = false
    private var isPasswordCorrectFormat: Boolean = false

    val viewState = CreateAccountViewStateImpl()

    private fun checkEmail(email: String) {
        when {
            accountValidation.isEmailValid(email) -> {
                isEmailEmpty = false
                isEmailCorrectFormat = true
            }
            accountValidation.isEmailEmpty(email) -> {
                isEmailEmpty = true
                isEmailCorrectFormat = false
            }
            else -> {
                isEmailEmpty = false
                isEmailCorrectFormat = false
            }
        }
    }

    fun showOrDismissEmailError(email: String) {
        checkEmail(email)

        if (!isEmailEmpty && isEmailCorrectFormat) {
            emailErrorNotVisible()
        } else if (isEmailEmpty && !isEmailCorrectFormat) {
            emailErrorNotVisible()
        } else {
            emailErrorVisible()
        }

        setViewStateAsUpdated()
    }

    private fun checkPassword(password: String) {
        if (accountValidation.isUpperCasePassword(password) && accountValidation.isPasswordContainsDigit(password)) {
            isPasswordEmpty = false
            isPasswordCorrectFormat = true
        } else if (accountValidation.isPasswordEmpty(password)) {
            isPasswordEmpty = true
            isPasswordCorrectFormat = true
        } else {
            isPasswordEmpty = false
            isPasswordCorrectFormat = false
        }
    }

    fun showOrDismissErrorPassword(password: String) {
        checkPassword(password)

        if (!isPasswordEmpty && isPasswordCorrectFormat) {
            passwordErrorNotVisible()
        } else if (isPasswordEmpty && !isPasswordCorrectFormat) {
            passwordErrorNotVisible()
        } else {
            passwordErrorVisible()
        }

        setViewStateAsUpdated()
    }

    fun showOrDismissErrorUsername(username: String) {
        viewState.usernameErrorVisible = accountValidation.isUsernameEmpty(username)
        setViewStateAsUpdated()
    }

    private fun setAlertCopy(title: String, message: String) {
        alertTitle = title
        alertMessage = message
    }

    private fun showErrorCreateAccountAlert(title: String, message: String) {
        setShouldShowDismissProgressAsUpdated()
        setAlertCopy(title, message)
        setShouldShowAlertAsUpdated()
    }

    private fun isAllFieldsEnteredCorrectly(): Boolean {
        return !viewState.usernameErrorVisible &&
            !viewState.emailErrorVisible &&
            !viewState.passwordErrorVisible
    }

    fun onClickOnContinue(email: String, username: String, password: String) {
        if (accountValidation.isEmailEmpty(email)) {
            showOrDismissEmailError(email)
        }
        if (accountValidation.isPasswordEmpty(password)) {
            showOrDismissErrorPassword(password)
        }
        if (accountValidation.isUsernameEmpty(username)) {
            viewState.usernameErrorVisible = accountValidation.isUsernameEmpty(username)
            setViewStateAsUpdated()
        }
        if (isAllFieldsEnteredCorrectly()) {
            setShouldShowProgressAsUpdated()

            chalFirebaseAuth.auth.fetchSignInMethodsForEmail(email)
                .addOnCompleteListener {
                    val isNewUser = it.result?.signInMethods?.isEmpty() ?:  true

                    if (isNewUser) {
                        Handler(Looper.getMainLooper()).postDelayed({
                            setShouldShowDismissProgressAsUpdated()
                            navigation.showUploadPhoto(username, email, password)
                        }, LOADING_DELAY.toLong())
                    } else {
                        showErrorCreateAccountAlert(
                            title = application.getString(R.string.error_cant_create_account),
                            message = application.getString(R.string.error_email_already_exists)
                        )
                    }
                }
        } else {
            showErrorCreateAccountAlert(
                title = application.getString(R.string.error_cant_create_account),
                message = application.getString(R.string.error_fields_are_not_correct_create_account)
            )
        }
    }

    private fun emailErrorVisible() {
        viewState.emailErrorVisible = true
    }

    private fun emailErrorNotVisible() {
        viewState.emailErrorVisible = false
    }

    private fun passwordErrorVisible() {
        viewState.passwordErrorVisible = true
    }

    private fun passwordErrorNotVisible() {
        viewState.passwordErrorVisible = false
    }

    fun onBackClicked() = navigation.pop()

    inner class CreateAccountViewStateImpl : CreateAccountViewState {
        override var toolbarText: String = application.getString(R.string.back)
        override var emailErrorVisible: Boolean = false
        override var passwordErrorVisible: Boolean = false
        override var usernameErrorVisible: Boolean = false
    }
}