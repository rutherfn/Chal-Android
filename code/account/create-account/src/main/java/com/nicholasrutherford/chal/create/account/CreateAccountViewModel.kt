package com.nicholasrutherford.chal.create.account

import android.app.Application
import android.os.CountDownTimer
import androidx.hilt.lifecycle.ViewModelInject
import com.nicholasrutherford.chal.account.validation.AccountValidation
import com.nicholasrutherford.chal.ui.base_vm.BaseViewModel

class CreateAccountViewModel @ViewModelInject constructor(
    private val accountValidation: AccountValidation,
    private val application: Application,
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
        if (accountValidation.isEmailValid(email)) {
            isEmailEmpty = false
            isEmailCorrectFormat = true
        } else if (accountValidation.isEmailEmpty(email)) {
            isEmailEmpty = true
            isEmailCorrectFormat = false
        } else {
            isEmailEmpty = false
            isEmailCorrectFormat = false
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

    fun showErrorCreateAccountAlert() {
        setShouldShowDismissProgressAsUpdated()
        alertTitle = application.getString(R.string.error_cant_create_account)
        alertMessage = application.getString(R.string.error_fields_are_not_correct_create_account)
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
        // before this get release to beta users,
        // were going to need to check to make sure the username and email,
        // are not stored in firebase realtime database.
        // if one of them are, tell them to use a different one(they should not be able to continue the workflow,
        // if that error occurs.
        if (isAllFieldsEnteredCorrectly()) {
            setShouldShowProgressAsUpdated()

            val timer = object : CountDownTimer(1000, 100) {
                override fun onFinish() {
                    setShouldShowDismissProgressAsUpdated()
                    // ask the user if they want to upload a profile picture
                    // if they dont, we continue to create a profile
                    // if they do, we take them to upload picture
                    println("it works ")
                }

                override fun onTick(millisUntilFinished: Long) {}
            }
            timer.start()
        } else {
            showErrorCreateAccountAlert()
        }
    }

    fun emailErrorVisible() {
        viewState.emailErrorVisible = true
    }

    fun emailErrorNotVisible() {
        viewState.emailErrorVisible = false
    }

    fun passwordErrorVisible() {
        viewState.passwordErrorVisible = true
    }

    fun passwordErrorNotVisible() {
        viewState.passwordErrorVisible = false
    }

    fun onBackClicked() = navigation.pop()

    inner class CreateAccountViewStateImpl : CreateAccountViewState {
        override var emailErrorVisible: Boolean = false
        override var passwordErrorVisible: Boolean = false
        override var usernameErrorVisible: Boolean = false
    }
}