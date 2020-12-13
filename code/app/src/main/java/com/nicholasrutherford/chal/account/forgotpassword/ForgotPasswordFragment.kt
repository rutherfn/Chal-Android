package com.nicholasrutherford.chal.account.forgotpassword

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import com.nicholasrutherford.chal.databinding.FragmentForgotPasswordBinding
import com.nicholasrutherford.chal.ext.forgotpassword.ForgotPasswordFragmentExtension
import com.nicholasrutherford.chal.helpers.Helper
import com.nicholasrutherford.chal.helpers.Typeface
import com.nicholasrutherford.chal.helpers.visibleOrGone

class ForgotPasswordFragment(private val activity: ForgotPasswordActivity, private val appContext: Context) : Fragment(),
        ForgotPasswordFragmentExtension {

    val viewModel = ForgotPasswordViewModel(activity, appContext)
    private val typeface = Typeface()
    private val helper = Helper()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val bind = FragmentForgotPasswordBinding.inflate(layoutInflater)
        updateTypefaces(bind)
        editorActionListener(bind)
        textChangedListener(bind)
        clickListener(bind)
        return bind.root
    }

    override fun updateTypefaces(bind: FragmentForgotPasswordBinding) {
        typeface.setTypefaceForHeaderBold(bind.tvForgotYourPassword, appContext)

        typeface.setTypefaceForSubHeaderBold(bind.tvPleaseEnterYour, appContext)
        typeface.setTypefaceForSubHeaderBold(bind.tvToResetPassword, appContext)

        typeface.setTypefaceForBodyBold(bind.btnDone, appContext)
        typeface.setTypefaceForBodyBold(bind.tvErrorEmailForgotPassword, appContext)
    }

    override fun editorActionListener(bind: FragmentForgotPasswordBinding) {
        bind.etTypeEmail.setOnEditorActionListener { _, actionId, _ ->
            val email = bind.etTypeEmail.text.toString()
            if (actionId == EditorInfo.IME_ACTION_DONE && email != "" && email.contains("@") && email.contains(".com")) {
                viewModel.attemptToSendResetPassword(email)
            } else if(actionId == EditorInfo.IME_ACTION_DONE) {
                helper.hideSoftKeyBoard(activity)
            }
            false
        }
    }

    override fun textChangedListener(bind: FragmentForgotPasswordBinding) {
        bind.etTypeEmail.addTextChangedListener (object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val email = bind.etTypeEmail.text.toString()
                viewModel.checkIfEmailIsEnteredCorrectly(email)

                bind.tvErrorEmailForgotPassword.visibleOrGone = viewModel.viewState.errorForgotPasswordVisible
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    override fun clickListener(bind: FragmentForgotPasswordBinding) {
        bind.btnDone.setOnClickListener {
            val resetEmail = bind.etTypeEmail.text.toString()
            viewModel.attemptToSendResetPassword(resetEmail)
        }
    }

}