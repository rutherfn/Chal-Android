package com.nicholasrutherford.chal.account.forgot.password

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.nicholasrutherford.chal.account.forgot.password.databinding.DialogFragmentForgotPasswordBinding
import com.nicholasrutherford.chal.helper.fragment.visibleOrGone
import com.nicholasrutherford.chal.ui.base_fragment.BaseDialogFragment
import com.nicholasrutherford.chal.ui.typefaces.Typefaces
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ForgotPasswordDialogFragment @Inject constructor(): BaseDialogFragment<DialogFragmentForgotPasswordBinding>(
    DialogFragmentForgotPasswordBinding::inflate) {

    @Inject
    lateinit var typeface: Typefaces

    @Inject
    lateinit var application: Application

    private val viewModel: ForgotPasswordViewModel by viewModels()

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
        lifecycleScope.launch {
            viewModel.passwordResetEmailStatus.collect { status ->
                viewModel.onViewFromPasswordResetEmailStatus(status)
            }
        }
        collectAlertAsUpdated()
    }

    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    override fun updateTypefaces() {
        typeface.setTextViewHeaderBoldTypeface(binding.tvForgotYourPassword)

        binding.tbForgotPassword.tbStock.setTitleTextAppearance(application, R.style.ToolbarTextAppearance)

        typeface.setTextViewSubHeaderRegularTypeface(binding.tvPleaseEnterYour)
        typeface.setTextViewSubHeaderRegularTypeface(binding.tvToResetPassword)

        typeface.setTextViewSubHeaderLightTypeface(binding.btnDone)
        typeface.setTextViewBodyItalicTypeface(binding.tvErrorEmailForgotPassword)
    }

    override fun collectAlertAsUpdated() {
        lifecycleScope.launch {
            viewModel.shouldShowAlert.collect { isShouldShowAlert ->
                if (isShouldShowAlert) {
                    showAlert(
                        title = viewModel.alertTitle,
                        message = viewModel.alertMessage
                    )
                    viewModel._shouldShowAlert.value = false
                }
            }
        }
    }

    override fun onListener() {
        binding.etTypeEmail.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                view?.hideKeyboard()
                val email = binding.etTypeEmail.text.toString()
                viewModel.onEmailEditAction(email = email, actionId = actionId)
            }

            false
        }
        binding.etTypeEmail.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val email = binding.etTypeEmail.text.toString()
                viewModel.checkIfEmailIsEnteredCorrectly(email)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit
        })
        binding.btnDone.setOnClickListener {
            view?.hideKeyboard()
            val resetEmail = binding.etTypeEmail.text.toString()
            viewModel.onDoneClicked(resetEmail)
        }
        binding.tbForgotPassword.tbStock.setOnClickListener {
            viewModel.onBackClicked()
        }
    }

    override fun updateView() {
        binding.tbForgotPassword.tbStock.setNavigationIcon(viewModel.toolbarBackImage)
        binding.tbForgotPassword.tbStock.title = viewModel.viewState.toolbarText

        binding.tvErrorEmailForgotPassword.visibleOrGone = viewModel.viewState.errorForgotPasswordVisible
    }
}