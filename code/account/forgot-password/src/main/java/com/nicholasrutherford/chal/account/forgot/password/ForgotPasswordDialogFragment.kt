package com.nicholasrutherford.chal.account.forgot.password

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.nicholasrutherford.chal.account.forgot.password.databinding.DialogFragmentForgotPasswordBinding
import com.nicholasrutherford.chal.ui.base_fragment.BaseDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ForgotPasswordDialogFragment @Inject constructor(): BaseDialogFragment<DialogFragmentForgotPasswordBinding>(
    DialogFragmentForgotPasswordBinding::inflate) {

    @Inject
    lateinit var navigation: ForgotPasswordNavigation

    private val viewModel: ForgotPasswordViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun updateTypefaces() {
        TODO("Not yet implemented")
    }

    override fun collectAlertAsUpdated() {
        TODO("Not yet implemented")
    }

    override fun onListener() {
        TODO("Not yet implemented")
    }

    override fun updateView() {
        TODO("Not yet implemented")
    }
}