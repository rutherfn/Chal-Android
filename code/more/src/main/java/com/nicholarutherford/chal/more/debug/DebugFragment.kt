package com.nicholarutherford.chal.more.debug

import android.app.Application
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.nicholarutherford.chal.more.R
import com.nicholarutherford.chal.more.databinding.FragmentDebugBinding
import com.nicholasrutherford.chal.ui.base_fragment.BaseFragment
import com.nicholasrutherford.chal.ui.typefaces.Typefaces
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class DebugFragment @Inject constructor() : BaseFragment<FragmentDebugBinding>(
    FragmentDebugBinding::inflate) {

    @Inject
    lateinit var typefaces: Typefaces

    @Inject
    lateinit var application: Application

    private val viewModel: DebugViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            collectViewStateResult(viewModel.viewStateUpdated, viewModel._viewStateUpdated)
        }
    }

    override fun updateTypefaces() {
        binding?.let { binding ->
            typefaces.setTextViewSubHeaderBoldTypeface(binding.clDebug.swEnableChallengeMode)
            typefaces.setTextViewSubHeaderBoldTypeface(binding.clDebug.swTurnOnAllFeatures)
            typefaces.setTextViewSubHeaderBoldTypeface(binding.clDebug.swShowDeviceNotifications)
            typefaces.setTextViewSubHeaderBoldTypeface(binding.clDebug.swShowUnActivatedAccount)
            typefaces.setTextViewSubHeaderBoldTypeface(binding.clDebug.swShowOnBoarding)
        }
    }

    override fun collectAlertAsUpdated() = Unit

    override fun onListener() {
        binding?.let { binding ->
            binding.tbDebug.tbStock.setOnClickListener {
                viewModel.onBackClicked()
            }

            binding.clDebug.swEnableChallengeMode.setOnCheckedChangeListener { _, isChecked ->
                viewModel.onCheckedEnableChallengeMode(isChecked)
            }
            binding.clDebug.swTurnOnAllFeatures.setOnCheckedChangeListener { _, isChecked ->
                viewModel.onCheckedTurnOnAllFeatures(isChecked)
            }
            binding.clDebug.swShowDeviceNotifications.setOnCheckedChangeListener { _, isChecked ->
                viewModel.onCheckedShowDeviceNotification(isChecked)
            }
            binding.clDebug.swShowUnActivatedAccount.setOnCheckedChangeListener { _, isChecked ->
                viewModel.onCheckedUnActivatedAccount(isChecked)
            }
            binding.clDebug.swShowOnBoarding.setOnCheckedChangeListener { _, isChecked ->
                viewModel.onCheckedOnBoarding(isChecked)
            }
        }
    }

    override fun updateView() {
        binding?.let { binding ->
            binding.tbDebug.tbStock.setNavigationIcon(viewModel.toolbarBackImage)
            binding.tbDebug.tbStock.title =
                application.getString(R.string.debug)

            binding.clDebug.swEnableChallengeMode.isChecked =
                viewModel.viewState.isEnableChallengeModeChecked
            binding.clDebug.swTurnOnAllFeatures.isChecked =
                viewModel.viewState.isTurnOnAllFeaturesChecked
            binding.clDebug.swShowDeviceNotifications.isChecked =
                viewModel.viewState.isShowDeviceNotificationsIsChecked
            binding.clDebug.swShowUnActivatedAccount.isChecked =
                viewModel.viewState.isUnActivatedAccountIsChecked
            binding.clDebug.swShowOnBoarding.isChecked =
                viewModel.viewState.isOnBoardingChcked
        }
    }
}