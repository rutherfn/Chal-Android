package com.nicholasrutherford.chal.account.forgotpasswordredesign

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.nicholasrutherford.chal.databinding.FragmentForgotPasswordBinding
import com.nicholasrutherford.chal.ext.fragments.forgotpassword.ForgotPasswordFragmentExtension
import com.nicholasrutherford.chal.helper.fragment.visibleOrGone
import com.nicholasrutherford.chal.ui.typefaces.Typefaces
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class ForgotPasswordRedesignFragment @Inject constructor(
    private val typeface: Typefaces
): DaggerFragment(), ForgotPasswordFragmentExtension {

    @Inject
    lateinit var viewmodelFactory: ViewModelProvider.Factory

    private val viewModel by lazy {
        ViewModelProvider(this, viewmodelFactory)
            .get(ForgotPasswordRedesignViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val bind = FragmentForgotPasswordBinding.inflate(layoutInflater)
        collectViewStateUpdated(bind)
        updateTypefaces(bind)
        editorActionListener(bind)
        textChangedListener(bind)
        clickListeners(bind)
        return bind.root
    }

    override fun collectViewStateUpdated(bind: FragmentForgotPasswordBinding) {
        lifecycleScope.launch {
            viewModel.viewStateUpdated.collect { isUpdated ->
                if (isUpdated) {
                    updateView(bind)
                }
                viewModel.setViewStateAsNotUpdated()
            }
        }
        lifecycleScope.launch {
            viewModel.firebaseAuth.sendPasswordResetEmailState.collect { status ->
                viewModel.updateViewFromPasswordResetEmailStatus(status)
            }
        }
    }

    override fun updateTypefaces(bind: FragmentForgotPasswordBinding) {
        typeface.setTextViewHeaderBoldTypeface(bind.tvForgotYourPassword)

        typeface.setTextViewSubHeaderRegularTypeface(bind.tvPleaseEnterYour)
        typeface.setTextViewSubHeaderRegularTypeface(bind.tvToResetPassword)

        typeface.setTextViewSubHeaderLightTypeface(bind.btnDone)
        typeface.setTextViewBodyItalicTypeface(bind.tvErrorEmailForgotPassword)
    }

    override fun editorActionListener(bind: FragmentForgotPasswordBinding) {
        bind.etTypeEmail.setOnEditorActionListener { _, actionId, _ ->
            val email = bind.etTypeEmail.text.toString()
            viewModel.onEmailEditAction(email = email, actionId = actionId)

            false
        }
    }

    override fun textChangedListener(bind: FragmentForgotPasswordBinding) {
        bind.etTypeEmail.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val email = bind.etTypeEmail.text.toString()
                viewModel.checkIfEmailIsEnteredCorrectly(email)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    override fun updateView(bind: FragmentForgotPasswordBinding) {
        bind.tvErrorEmailForgotPassword.visibleOrGone = viewModel.viewState.errorForgotPasswordVisible
    }

    override fun clickListeners(bind: FragmentForgotPasswordBinding) {
        bind.btnDone.setOnClickListener {
            val resetEmail = bind.etTypeEmail.text.toString()
            viewModel.onDoneClicked(resetEmail)
        }
    }
}