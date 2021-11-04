package com.nicholarutherford.chal.more

import android.app.Application
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nicholarutherford.chal.more.databinding.FragmentMoreBinding
import com.nicholasrutherford.chal.ui.base_fragment.BaseFragment
import com.nicholasrutherford.chal.ui.typefaces.Typefaces
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MoreFragment @Inject constructor(): BaseFragment<FragmentMoreBinding>(
    FragmentMoreBinding::inflate) {

    @Inject
    lateinit var typefaces: Typefaces

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
        lifecycleScope.launch {
            collectShouldShowAlertResult(
                this@MoreFragment.id,
                viewModel.alert,
                viewModel._alert
            )
        }
    }

    override fun updateTypefaces() {
        binding?.let { binding ->
            typefaces.setTextViewHeaderBoldTypeface(binding.clMore.tvChalMenu)

            typefaces.setTextViewBodyBoldTypeface(binding.clMore.tvMyProfile)
            typefaces.setTextViewBodyBoldTypeface(binding.clMore.tvUploadPost)
            typefaces.setTextViewBodyBoldTypeface(binding.clMore.tvSettings)
            typefaces.setTextViewBodyBoldTypeface(binding.clMore.tvChat)
            typefaces.setTextViewBodyBoldTypeface(binding.clMore.tvMyFeed)
            typefaces.setTextViewBodyBoldTypeface(binding.clMore.tvChallenges)

            typefaces.setTextViewSubHeaderBoldTypeface(binding.clMore.btnSignOutAccount)
            typefaces.setTextViewSubHeaderBoldTypeface(binding.clMore.tvAllRightsReserved)
        }
    }

    override fun collectAlertAsUpdated() = Unit

    override fun onListener() {
        binding?.let { binding ->
            binding.clMore.cvMyProfile.setOnClickListener {
                viewModel.onMyProfileClicked()
            }
            binding.clMore.cvUploadPost.setOnClickListener {
                viewModel.onUploadProgressClicked()
            }
            binding.clMore.cvSettings.setOnClickListener {
                viewModel.onSettingsClicked()
            }
            binding.clMore.cvChat.setOnClickListener {
                viewModel.onChatClicked()
            }
            binding.clMore.cvMyFeed.setOnClickListener {
                viewModel.onMyFeetClicked()
            }
            binding.clMore.cvChallenges.setOnClickListener {
                viewModel.onChallengesClicked()
            }
            binding.clMore.cvDebug.setOnClickListener {
                viewModel.onDebugClicked()
            }
            binding.clMore.cvReportBug.setOnClickListener {
                viewModel.onReportBugClicked()
            }
            binding.clMore.btnSignOutAccount.setOnClickListener {
                viewModel.onSignOutAccountClicked()
            }
        }
    }

    override fun updateView() {
        binding?.let { binding ->
            val options = RequestOptions()
                .placeholder(R.drawable.circle)
                .error(R.drawable.circle)

            Glide.with(this).load(viewModel.viewState.toolbarImage).apply(options)
                .into(binding.clMore.cvMyProfilePic)
        }
    }
}