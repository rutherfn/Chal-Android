package com.nicholarutherford.chal.more

import android.app.Application
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.nicholarutherford.chal.more.databinding.FragmentMoreBinding
import com.nicholasrutherford.chal.ui.base_fragment.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MoreFragment @Inject constructor(): BaseFragment<FragmentMoreBinding>(
    FragmentMoreBinding::inflate) {

    @Inject
    lateinit var application: Application

    private val viewModel: MoreViewModel by viewModels()

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
    }

    override fun updateTypefaces() {
    }

    override fun collectAlertAsUpdated() {
    }

    override fun onListener() {
    }

    override fun updateView() {
    }
}