package com.nicholasrutherford.chal.account.sign.up

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.nicholasrutherford.chal.account.sign.up.databinding.SignUpFragmentBinding
import com.nicholasrutherford.chal.ui.base_fragment.BaseFragment
import com.nicholasrutherford.chal.ui.typefaces.Typefaces
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SignUpFragment @Inject constructor(): BaseFragment<SignUpFragmentBinding>(
    SignUpFragmentBinding::inflate) {

    @Inject
    lateinit var typeface: Typefaces

    private val viewModel: SignUpViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateToobarUI()
    }

    private fun updateToobarUI() {
        colorSmokeWhite?.let { whiteColor ->
            binding.tbSignUpBackButton.clBack.setBackgroundColor(whiteColor)
        }
        colorBlack?.let { blackColor ->
            binding.tbSignUpBackButton.tvTitle.setTextColor(blackColor)
            binding.tbSignUpBackButton.ibBack.setColorFilter(blackColor)
        }
    }

    override fun updateTypefaces() {
        typeface.setTextViewHeaderBoldTypeface(binding.tvSignUp)
        typeface.setTextViewHeaderRegularTypeface(binding.tvSubHeader)

        typeface.setTextViewSubHeaderBoldTypeface(binding.tbSignUpBackButton.tvTitle)

        typeface.setTextViewBodyBoldTypeface(binding.tvAlreadyHaveAccount)
        typeface.setTextViewBodyBoldTypeface(binding.tvLogin)
    }

    override fun collectAlertAsUpdated() = Unit

    override fun onListener() {
        binding.tbSignUpBackButton.ibBack.setOnClickListener {
            viewModel.onBackClicked()
        }
        binding.tvLogin.setOnClickListener {
            viewModel.onLoginClicked()
        }
        binding.ivFacebook.setOnClickListener {
            viewModel.onFacebookClicked()
            navigateToUrl(viewModel.viewState.socialMediaUrl)
        }
        binding.ivLinkedin.setOnClickListener {
            viewModel.onLinkedinClicked()
            navigateToUrl(viewModel.viewState.socialMediaUrl)
        }
        binding.ivGram.setOnClickListener {
            viewModel.onInstagramClicked()
            navigateToUrl(viewModel.viewState.socialMediaUrl)
        }
    }

    override fun updateView() = Unit
}