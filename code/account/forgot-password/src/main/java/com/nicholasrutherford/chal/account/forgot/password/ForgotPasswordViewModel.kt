package com.nicholasrutherford.chal.account.forgot.password

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import com.nicholasrutherford.chal.firebase.auth.ChalFirebaseAuth
import com.nicholasrutherford.chal.ui.base_vm.BaseViewModel

class ForgotPasswordViewModel @ViewModelInject constructor(
    private val application: Application,
    private val firebaseAuth: ChalFirebaseAuth
) : BaseViewModel() {

    val viewState = ForgotPasswordViewStateImpl()

    inner class ForgotPasswordViewStateImpl: ForgotPasswordViewState {
        override var errorForgotPasswordVisible = false
    }
}