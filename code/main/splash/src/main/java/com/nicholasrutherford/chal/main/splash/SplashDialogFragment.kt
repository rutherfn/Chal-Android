package com.nicholasrutherford.chal.main.splash

import android.os.Bundle
import android.view.View
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import androidx.fragment.app.viewModels
import com.nicholasrutherford.chal.main.splash.databinding.DialogFragmentSplashBinding
import com.nicholasrutherford.chal.ui.base_fragment.BaseDialogFragment

@AndroidEntryPoint
class SplashDialogFragment @Inject constructor() : BaseDialogFragment<DialogFragmentSplashBinding>(
    DialogFragmentSplashBinding::inflate) {

    private val viewModel: SplashViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.checkIfUserIsSignedIn()
    }

    override fun updateTypefaces() = Unit // not used

    override fun collectAlertAsUpdated() = Unit  // not used

    override fun onListener() = Unit // not used

    override fun updateView() {
        binding.ivSplashLogo.setImageResource(viewModel.viewState.splashImageRes)
    }
}