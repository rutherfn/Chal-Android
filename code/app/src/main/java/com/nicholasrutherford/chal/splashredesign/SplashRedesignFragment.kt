package com.nicholasrutherford.chal.splashredesign

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.nicholasrutherford.chal.databinding.FragmentSplashBinding
import com.nicholasrutherford.chal.ui.base_fragment.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashRedesignFragment @Inject constructor() : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {

    private val viewModel: SplashRedesignViewModel by viewModels()

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