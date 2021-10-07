package com.nicholasrutherford.chal.main.news.feed

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.nicholasrutherford.chal.main.news.feed.databinding.FragmentNewsFeedBinding
import com.nicholasrutherford.chal.ui.base_fragment.BaseFragment
import com.nicholasrutherford.chal.ui.typefaces.Typefaces
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class NewsFeedFragment @Inject constructor() : BaseFragment<FragmentNewsFeedBinding>(
    FragmentNewsFeedBinding::inflate) {

    @Inject
    lateinit var typefaces: Typefaces

    private val viewModel: NewsFeedViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            collectViewStateResult(viewModel.viewStateUpdated, viewModel._viewStateUpdated)
        }
        lifecycleScope.launch {
            collectShouldDismissProgressResult(
                viewModel.shouldDismissProgress,
                viewModel._shouldDismissProgress
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

    override fun collectAlertAsUpdated() {
        lifecycleScope.launch {
            viewModel.shouldShowAlert.collect { isShouldShowAlert ->
                if (isShouldShowAlert) {
                    showOkAlert(title = viewModel.alertTitle, message = viewModel.alertMessage)
                }
                viewModel.setShouldShowAlertAsNotUpdated()
            }
        }
    }

    override fun updateTypefaces() {

    }

    override fun onListener() {
    }

    override fun updateView() {

    }
}