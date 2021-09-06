package com.nicholasrutherford.chal.account.login

import android.app.Application
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.nicholasrutherford.chal.account.login.databinding.FragmentLoginBinding
import com.nicholasrutherford.chal.helper.fragment.visibleOrGone
import com.nicholasrutherford.chal.ui.typefaces.Typefaces
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment @Inject constructor() : Fragment() {

    @Inject
    lateinit var typeface: Typefaces
    //
    // @Inject
    // lateinit var keyboard: KeyboardImpl

    @Inject
    lateinit var application: Application

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val bind = FragmentLoginBinding.inflate(layoutInflater)
        updateTypefaces(bind)
        textChangedListener(bind)
        editActionListener(bind)
        collectViewStateUpdated(bind)
        clickListeners(bind)
        return bind.root
    }

    fun main(bind: FragmentLoginBinding) = Unit

    fun collectViewStateUpdated(bind: FragmentLoginBinding) {
        lifecycleScope.launch {
            viewModel.viewStateUpdated.collect { isUpdated ->
                if (isUpdated) {
                    updateView(bind)
                }
                viewModel.setViewStateAsNotUpdated()
            }
        }
        lifecycleScope.launch {
            viewModel.firebaseAuth.loginStatusState.collect { status ->
                viewModel.onLoginStatesResult(status)
            }
        }
    }

    fun updateTypefaces(bind: FragmentLoginBinding) {
        // typeface.setTextViewHeaderBoldTypeface(bind.tvTitle)
        // typeface.setTextViewSubHeaderItalicTypeface(bind.tvSubTitle)
        //
        // typeface.setTextViewSubHeaderBoldTypeface(bind.tvEmail)
        // typeface.setTextViewSubHeaderBoldTypeface(bind.tvPassword)
        //
        // typeface.setTextViewSubHeaderRegularTypeface(bind.btLogIn)
        //
        // typeface.setTextViewBodyBoldTypeface(bind.tvForgotPassword)
        // typeface.setTextViewBodyBoldTypeface(bind.tvSignUp)
    }

    fun textChangedListener(bind: FragmentLoginBinding) {
        bind.etEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                viewModel.updateEmailAfterTextChanged(bind.etEmail.text.toString())
            }
        })
    }

    fun editActionListener(bind: FragmentLoginBinding) {
        bind.etEmail.setOnEditorActionListener { _, actionId, _ ->

            if (actionId == EditorInfo.IME_ACTION_DONE) {
               // keyboard.hideKeyBoard()
            }
            false
        }

        bind.etPassword.setOnEditorActionListener { _, actionId, _ ->
            viewModel.passwordEditAction(bind.etEmail.text.toString(), bind.etPassword.text.toString(), actionId)
            false
        }
    }

    fun clickListeners(bind: FragmentLoginBinding) {
        bind.btLogIn.setOnClickListener {
            viewModel.onLogInClicked(bind.etEmail.text.toString(), bind.etPassword.text.toString())

            val emptyString = application.getString(R.string.empty_string)
            bind.etEmail.setText(emptyString)
            bind.etPassword.setText(emptyString)
        }
        bind.tvSignUp.setOnClickListener {
            viewModel.onSignUpClicked()
        }
        bind.tvForgotPassword.setOnClickListener {
            viewModel.onForgotPasswordClicked()
        }
    }

    fun updateView(bind: FragmentLoginBinding) {
        bind.tvErrorEmail.visibleOrGone = viewModel.viewState.emailErrorTextVisible
        bind.ivErrorEmail.visibleOrGone = viewModel.viewState.emailErrorImageVisible
    }
}