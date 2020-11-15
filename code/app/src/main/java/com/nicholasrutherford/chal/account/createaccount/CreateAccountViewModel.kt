package com.nicholasrutherford.chal.account.createaccount

import android.content.Context
import android.os.CountDownTimer
import androidx.lifecycle.ViewModel
import com.nicholasrutherford.chal.navigationimpl.CreateAccountNavigationImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CreateAccountViewModel(private val createAccountActivity: CreateAccountActivity, private val appContext: Context) : ViewModel() {

    private val _createAccountSuccessState = MutableStateFlow(false)
    val createAccountSuccessState: StateFlow<Boolean> = _createAccountSuccessState

    private var isEmailEmpty: Boolean = false
    private var isEmailCorrectFormat: Boolean = false

    private var isPasswordEmpty: Boolean = false
    private var isPasswordCorrectFormat: Boolean = false

    private val navigation = CreateAccountNavigationImpl()

    val viewState = CreateAccountViewStateImpl()

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

    private fun checkEmail(email: String) {

        if(email.contains("@") && email.contains(".com")) {
            isEmailEmpty = false
            isEmailCorrectFormat = true
        } else if(email == "") {
            isEmailEmpty = true
            isEmailCorrectFormat = false
        } else {
            isEmailEmpty = false
            isEmailCorrectFormat = false
        }
    }

    fun showOrDismissErrorEmail(email: String) {
        checkEmail(email)

        if(!isEmailEmpty && isEmailCorrectFormat) {
            emailErrorNotVisible()
        } else if(isEmailEmpty && !isEmailCorrectFormat) {
            emailErrorNotVisible()
        } else {
            emailErrorVisible()
        }
    }

    fun containsDigit(password: String): Boolean {
        for (c in password.toCharArray()) {
            if (Character.isDigit(c)) {
                return true
            }
        }
        return false
    }

    private fun checkPassword(password: String) {
        val hasUpperCase = password != password.toLowerCase()

        if(hasUpperCase && containsDigit(password)) {
            isPasswordEmpty = false
            isPasswordCorrectFormat = true
        } else if(password == "") {
            isPasswordEmpty = true
            isPasswordCorrectFormat = false
        } else {
            isPasswordEmpty = false
            isPasswordCorrectFormat = false
        }
    }

    fun showOrDismissErrorPassword(password: String) {
        checkPassword(password)

        if(!isPasswordEmpty && isPasswordCorrectFormat) {
            passwordErrorNotVisible()
        } else if(isPasswordEmpty && !isPasswordCorrectFormat) {
            passwordErrorNotVisible()
        } else {
            passwordErrorVisible()
        }
    }

    fun showOrDismissErrorUsername(username: String) {
        viewState.usernameErrorVisible = username == ""
    }

    private fun isAllFieldsEnteredCorrectly() : Boolean {
        return !viewState.usernameErrorVisible && !viewState.emailErrorVisible && !viewState.passwordErrorVisible
    }

    fun onClickOnContinue(email: String, username: String, password: String) {
        // before this get release to beta users,
        // were going to need to check to make sure the username and email,
        // are not stored in firebase realtime database.
        // if one of them are, tell them to use a different one(they should not be able to continue the workflow,
        // if that error occurs.
        if(isAllFieldsEnteredCorrectly()) {
            println("here we go")
            navigation.showAcProgress(createAccountActivity)

            val timer = object : CountDownTimer(1000, 100) {
                override fun onFinish() {
                    _createAccountSuccessState.value = true
                    navigation.hideAcProgress()
                    navigation.uploadPhoto(username, email, password, createAccountActivity)
                }

                override fun onTick(millisUntilFinished: Long) {}

            }
            timer.start()

            // show a loading animation, and then take the user back to where they want to go
        } else {
            println("here we go nope")
            navigation.showAcProgress(createAccountActivity)
            showOrDismissErrorEmail(email)
            showOrDismissErrorUsername(username)
            showOrDismissErrorPassword(password)

            navigation.hideAcProgress()
            // show alert for errors
        }
    }


    inner class CreateAccountViewStateImpl: CreateAccountViewState {
        override var usernameErrorVisible = false

        override var emailErrorVisible = false

        override var passwordErrorVisible = false
}

}