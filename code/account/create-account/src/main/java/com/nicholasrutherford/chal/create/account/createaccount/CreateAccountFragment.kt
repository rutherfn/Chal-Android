package com.nicholasrutherford.chal.create.account.createaccount

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.nicholasrutherford.chal.helper.fragment.visibleOrGone
import com.nicholasrutherford.chal.create.account.databinding.CreateAccountFragmentBinding
import com.nicholasrutherford.chal.ui.base_fragment.BaseFragment
import com.nicholasrutherford.chal.ui.typefaces.Typefaces
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class CreateAccountFragment @Inject constructor() : BaseFragment<CreateAccountFragmentBinding>(
    CreateAccountFragmentBinding::inflate) {

    @Inject
    lateinit var typeface: Typefaces

    @Inject
    lateinit var application: Application

    private val viewModel: CreateAccountViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            collectViewStateResult(viewModel.viewStateUpdated, viewModel._viewStateUpdated)
        }
        lifecycleScope.launch {
            collectShouldShowProgressResult(
                viewModel.shouldShowProgress,
                viewModel._shouldShowProgress
            )
        }
        lifecycleScope.launch {
            collectShouldDismissProgressResult(
                viewModel.shouldDismissProgress,
                viewModel._shouldDismissProgress
            )
        }
        collectAlertAsUpdated()
    }

    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    override fun updateTypefaces() {
        typeface.setTextViewHeaderBoldTypeface(binding.tvCreate)
        typeface.setTextViewHeaderBoldTypeface(binding.tvAccount)

        typeface.setTextViewSubHeaderBoldTypeface(binding.tbCreateAccount.tvTitle)

        typeface.setTextViewSubHeaderBoldTypeface(binding.tvUsername)
        typeface.setTextViewBodyBoldTypeface(binding.tvErrorUsername)

        typeface.setTextViewSubHeaderBoldTypeface(binding.tvEmail)
        typeface.setTextViewBodyBoldTypeface(binding.tvErrorEmail)

        typeface.setTextViewSubHeaderBoldTypeface(binding.tvPassword)
        typeface.setTextViewBodyBoldTypeface(binding.tvErrorPassword)
    }

    override fun collectAlertAsUpdated() {
        lifecycleScope.launch {
            viewModel.shouldShowAlert.collect { isShouldShowAlert ->
                if (isShouldShowAlert) {
                    showOkAlert(title = viewModel.alertTitle, message = viewModel.alertMessage)
                }
                viewModel._shouldShowAlert.value = false
            }
        }
    }

    private fun onDoneEditActionListener() {
        etCloseKeyboardIfUserHitsDone(binding.etEmail)
        etCloseKeyboardIfUserHitsDone(binding.etUsername)
        etCloseKeyboardIfUserHitsDone(binding.etPassword)
    }

    private fun etCloseKeyboardIfUserHitsDone(editText: EditText) {
        editText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                view?.hideKeyboard()
            }
            false
        }
    }

    override fun onListener() {
        onDoneEditActionListener()
        binding.etEmail.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val email = binding.etEmail.text.toString()
                viewModel.showOrDismissEmailError(email)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit
        })
        binding.etPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val password = binding.etPassword.text.toString()
                viewModel.showOrDismissErrorPassword(password)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit
        })

        binding.etUsername.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val username = binding.etUsername.text.toString()
                viewModel.showOrDismissErrorUsername(username)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit
        })
        binding.btnContinueCreating.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            viewModel.onClickOnContinue(email, username, password)
        }
        binding.tbCreateAccount.ibBack.setOnClickListener {
            viewModel.onBackClicked()
        }
    }

    override fun updateView() {
        binding.ivErrorUsername.visibleOrGone = viewModel.viewState.usernameErrorVisible
        binding.tvErrorUsername.visibleOrGone = viewModel.viewState.usernameErrorVisible

        binding.ivErrorEmail.visibleOrGone = viewModel.viewState.emailErrorVisible
        binding.tvErrorEmail.visibleOrGone = viewModel.viewState.emailErrorVisible

        binding.ivErrorPassword.visibleOrGone = viewModel.viewState.passwordErrorVisible
        binding.tvErrorPassword.visibleOrGone = viewModel.viewState.passwordErrorVisible
    }
}