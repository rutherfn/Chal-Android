package com.nicholasrutherford.chal.account.login

import android.app.Application
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.nicholasrutherford.chal.account.login.databinding.FragmentLoginBinding
import com.nicholasrutherford.chal.helper.fragment.visibleOrGone
import com.nicholasrutherford.chal.ui.base_fragment.BaseFragment
import com.nicholasrutherford.chal.ui.typefaces.Typefaces
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment @Inject constructor() : BaseFragment<FragmentLoginBinding>(
    FragmentLoginBinding::inflate) {

    @Inject
    lateinit var typeface: Typefaces

    @Inject
    lateinit var application: Application

    private val viewModel: LoginViewModel by viewModels()

    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    override fun updateTypefaces() {
        typeface.setTextViewHeaderBoldTypeface(binding.tvTitle)
        typeface.setTextViewSubHeaderItalicTypeface(binding.tvSubTitle)

        typeface.setTextViewSubHeaderBoldTypeface(binding.tvEmail)
        typeface.setTextViewSubHeaderBoldTypeface(binding.tvPassword)

        typeface.setTextViewSubHeaderRegularTypeface(binding.btLogIn)

        typeface.setTextViewBodyBoldTypeface(binding.tvForgotPassword)
        typeface.setTextViewBodyBoldTypeface(binding.tvSignUp)
    }

    override fun collectViewStateAsUpdated() {
        lifecycleScope.launch {
            viewModel.viewStateUpdated.collect { isUpdated ->
                if (isUpdated) {
                    updateView()
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

    override fun onListener() {
        binding.etEmail.setOnEditorActionListener { _, actionId, _ ->

            if (actionId == EditorInfo.IME_ACTION_DONE) {
                view?.hideKeyboard()
            }
            false
        }

        binding.etEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                viewModel.updateEmailAfterTextChanged(binding.etEmail.text.toString())
            }
        })

        binding.etPassword.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                view?.hideKeyboard()
            }
            viewModel.passwordEditAction(binding.etEmail.text.toString(), binding.etPassword.text.toString(), actionId)
            false
        }

        binding.btLogIn.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            viewModel.onLogInClicked(email = email, password = password)

            val emptyString = application.getString(R.string.empty_string)
            binding.etEmail.setText(emptyString)
            binding.etPassword.setText(emptyString)
        }
        binding.tvSignUp.setOnClickListener {
            viewModel.onSignUpClicked()
        }
        binding.tvForgotPassword.setOnClickListener {
            viewModel.onForgotPasswordClicked()
        }
    }

    override fun updateView() {
        binding.tvErrorEmail.visibleOrGone = viewModel.viewState.emailErrorTextVisible
        binding.ivErrorEmail.visibleOrGone = viewModel.viewState.emailErrorImageVisible
    }

}