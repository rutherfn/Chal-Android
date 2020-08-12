package com.nicholasrutherford.chal.viewmodels

import android.content.Context
import android.content.Intent
import android.widget.EditText
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import com.nicholasrutherford.chal.activitys.debug.DebugActivity
import com.nicholasrutherford.chal.dialogfragments.DebugPasswordDialogFragment
import com.nicholasrutherford.chal.helpers.Helper
import com.nicholasrutherford.chal.navigation.debugpassword.DebugPasswordDialogNavigationImpl
import com.nicholasrutherford.chal.viewstate.DebugPasswordViewState

class DebugPasswordViewModel(private val fragmentActivity: FragmentActivity, private val context: Context, private val fragment: DebugPasswordDialogFragment, private val debugActivity: DebugActivity) : ViewModel() {

    private val debugPasswordNavigationImpl = DebugPasswordDialogNavigationImpl()
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

    private fun checkIfPasswordEnteredIsCorrect() {
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

    private fun startDebugActivity() { debugPasswordNavigationImpl.startDebugActivity(context, fragmentActivity, debugActivity )}

    inner class DebugPasswordViewStateImpl(): DebugPasswordViewState {
        override var userPasswordEntryValue = ""
        override var debugExistingPasswordValue = "2131"
        override var isUserCorrect = true
        override var errorDisplayForUserVisible = false
    }
}