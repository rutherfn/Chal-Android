package com.nicholasrutherford.chal.viewmodels

import android.content.Context
import android.content.Intent
import android.widget.EditText
import androidx.lifecycle.ViewModel
import com.nicholasrutherford.chal.activitys.DebugActivity
import com.nicholasrutherford.chal.fragments.dialogs.DebugPasswordDialogFragment
import com.nicholasrutherford.chal.helpers.Helper
import com.nicholasrutherford.chal.viewstate.DebugPasswordViewState

class DebugPasswordViewModel(private val context: Context, private val fragment: DebugPasswordDialogFragment) : ViewModel() {

    val viewState = DebugPasswordViewStateImpl()
    val helper = Helper()

    fun onButtonDebugPasswordClicked(etDebugPassword: EditText) {
        setUserPasswordEntryValue(etDebugPassword)
        checkIfPasswordEnteredIsCorrect()
    }

    fun onCloseClicked() { fragment.dialog?.dismiss() }

    fun checkIfWereReadyToStartDebugActivity() {
        if(viewState.isUserCorrect) {
            startDebugActivity()
        }
    }

    private fun setUserPasswordEntryValue(etDebugPassword: EditText) { viewState.userPasswordEntryValue = etDebugPassword.text.toString() }

    fun checkIfPasswordEnteredIsCorrect() {
        viewState.isUserCorrect = viewState.userPasswordEntryValue == viewState.debugExistingPasswordValue
        determineErrorIsVisibleOrNot()
    }

    private fun determineErrorIsVisibleOrNot() {
        if(!viewState.isUserCorrect) {
            viewState.errorDisplayForUserVisible = true
        } else if (viewState.isUserCorrect) {
            viewState.errorDisplayForUserVisible = false
        }
    }

    private fun startDebugActivity() {
        context.let {
            val intent = Intent(it.applicationContext, DebugActivity::class.java)
            context.startActivity(intent)
        }
    }

    inner class DebugPasswordViewStateImpl(): DebugPasswordViewState {
        override var userPasswordEntryValue = ""
        override var debugExistingPasswordValue = "2131"
        override var isUserCorrect = true
        override var errorDisplayForUserVisible = false
    }
}