package com.nicholasrutherford.chal.account.sign.up

import android.app.Application
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.nicholasrutherford.chal.account.sign.up.databinding.SignUpFragmentBinding
import com.nicholasrutherford.chal.create.account.R
import com.nicholasrutherford.chal.ui.base_fragment.BaseFragment
import com.nicholasrutherford.chal.ui.typefaces.Typefaces
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SignUpFragment @Inject constructor(): BaseFragment<SignUpFragmentBinding>(
    SignUpFragmentBinding::inflate) {

    @Inject
    lateinit var typeface: Typefaces

    @Inject
    lateinit var application: Application

    private val viewModel: SignUpViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateToobarUI()
    }

    private fun updateToobarUI() {
        binding?.let { binding ->
            colorSmokeWhite?.let { whiteColor ->
                binding.tbSignUp.tbStock.setBackgroundColor(whiteColor)
            }
            colorBlack?.let { blackColor ->
                binding.tbSignUp.tbStock.setTitleTextColor(blackColor)
            }
        }
    }

    override fun updateTypefaces() {
        binding?.let { binding ->
            typeface.setTextViewHeaderBoldTypeface(binding.tvSignUp)
            typeface.setTextViewHeaderRegularTypeface(binding.tvSubHeader)

            binding.tbSignUp.tbStock.setTitleTextAppearance(
                application,
                R.style.ToolbarTextAppearance
            )

            typeface.setTextViewBodyBoldTypeface(binding.tvAlreadyHaveAccount)
            typeface.setTextViewBodyBoldTypeface(binding.tvLogin)
        }
    }

    override fun collectAlertAsUpdated() = Unit

    override fun onListener() {
        binding?.let { binding ->
            binding.tbSignUp.tbStock.setOnClickListener {
                viewModel.onBackClicked()
            }
            binding.btnSignUpRegular.setOnClickListener {
                viewModel.onContinueWithEmailClicked()
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
    }

    override fun updateView() {
        binding?.let { binding ->
            binding.tbSignUp.tbStock.setNavigationIcon(viewModel.toolbarBlackBackImage)
            binding.tbSignUp.tbStock.title = viewModel.viewState.toolbarText
        }
    }
}