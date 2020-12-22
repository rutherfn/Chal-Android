package com.nicholasrutherford.chal.account.forgotpassword

import android.content.Context
import android.os.CountDownTimer
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.nicholasrutherford.chal.navigationimpl.forgotpassword.ForgotPasswordNavigationImpl

class ForgotPasswordViewModel (private val forgotPasswordActivity: ForgotPasswordActivity, private val appContext: Context) : ViewModel() {

    private val navigation = ForgotPasswordNavigationImpl()
    val viewState = ForgotPasswordViewStateImpl()

    fun navigateToLogin() = navigation.login(appContext, forgotPasswordActivity)

    fun showErrorEmail() { viewState.errorForgotPasswordVisible = true }

    fun hideErrorEmail() { viewState.errorForgotPasswordVisible = false }

    fun checkIfEmailIsEnteredCorrectly(email: String) {

        if (email.contains("@") && email.contains(".com")) {
            hideErrorEmail()
        } else if (email == "") {
            hideErrorEmail()
        } else {
            showErrorEmail()
        }
    }

    fun attemptToSendResetPassword(resetEmail: String) {
        if (viewState.errorForgotPasswordVisible) {
            navigation.forgotPasswordAlert("Not a valid email", "Sorry but that is not a valid email. Please enter a valid email, and try again", forgotPasswordActivity)
        } else {
            navigation.showAcProgress(forgotPasswordActivity)
            val timer = object: CountDownTimer(3000, 100) {

                override fun onTick(millisUntilFinished: Long) {}

                override fun onFinish() {
                    navigation.hideAcProgress()

                    val auth = FirebaseAuth.getInstance()

                    auth.sendPasswordResetEmail(resetEmail)
                        .addOnCompleteListener { task ->

                            if (task.isSuccessful) {
                                navigation.forgotPasswordAlert("Reset Password Email Sent", "Email reset password sent. Please check your email, for directions on how to reset your password.", forgotPasswordActivity)
                            } else {
                                navigation.forgotPasswordAlert("Email does not exist.", "Current email does not exist, please try again with a valid email under Chal accounts.", forgotPasswordActivity)
                            }
                        }
                }
            }
            timer.start()

            }
        }

    class ForgotPasswordViewStateImpl: ForgotPasswordViewState {
        override var errorForgotPasswordVisible = false
    }
}