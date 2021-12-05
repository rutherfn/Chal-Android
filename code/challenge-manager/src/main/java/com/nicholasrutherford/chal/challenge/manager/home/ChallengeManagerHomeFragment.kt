package com.nicholasrutherford.chal.challenge.manager.home

import android.app.Application
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.nicholasrutherford.chal.challenge.manager.R
import com.nicholasrutherford.chal.challenge.manager.databinding.ChallengeManagerHomeFragmentBinding
import com.nicholasrutherford.chal.ui.base_fragment.BaseFragment
import com.nicholasrutherford.chal.ui.typefaces.Typefaces
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.challenge_manager_home_fragment.*
import javax.inject.Inject

@AndroidEntryPoint
class ChallengeManagerHomeFragment @Inject constructor() : BaseFragment<ChallengeManagerHomeFragmentBinding>(
    ChallengeManagerHomeFragmentBinding::inflate) {

    @Inject
    lateinit var typeface: Typefaces

    @Inject
    lateinit var application: Application

    private val viewModel: ChallengeManagerHomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun updateTypefaces() {
        binding?.let { binding ->
            binding.tbChallengeManagerHome.tbStock.setTitleTextAppearance(
                application,
                R.style.ToolbarTextAppearance
            )

            typeface.setTextViewSubHeaderBoldTypeface(binding.btnCheckAllChallenges)
        }
    }

    override fun collectAlertAsUpdated() {
    }

    override fun onListener() {
    }

    override fun updateView() {
        binding?.let { binding ->
            binding.tbChallengeManagerHome.tbStock.title = viewModel.viewState.toolbarText
        }
    }

}