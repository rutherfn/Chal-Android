package com.nicholasrutherford.chal.main.splash

import android.os.Bundle
import android.view.View
import com.nicholasrutherford.chal.main.splash.databinding.FragmentSplashBinding
import com.nicholasrutherford.chal.ui.base_fragment.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import androidx.fragment.app.viewModels

@AndroidEntryPoint
class SplashFragment @Inject constructor() : BaseFragment<FragmentSplashBinding>(
    FragmentSplashBinding::inflate) {

    private val viewModel: SplashViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.checkIfUserIsSignedIn()
    }

    override fun updateTypefaces() = Unit // not used

    override fun clickListeners() = Unit // not used

    override fun updateView() {
        binding.ivSplashLogo.setImageResource(viewModel.viewState.splashImageRes)
    }
}