package com.nicholasrutherford.chal.challenge.detail

import android.app.Application
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.nicholasrutherford.chal.challenge.detail.databinding.FragmentChallengeDetailBinding
import com.nicholasrutherford.chal.helper.constants.*
import com.nicholasrutherford.chal.ui.base_fragment.BaseFragment
import com.nicholasrutherford.chal.ui.typefaces.Typefaces
import con.nicholasrutherford.chal.data.challenges.JoinableChallenges
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ChallengeDetailFragment @Inject constructor(): BaseFragment<FragmentChallengeDetailBinding>(
    FragmentChallengeDetailBinding::inflate) {

    @Inject
    lateinit var typefaces: Typefaces

    @Inject
    lateinit var application: Application

    private val viewModel: ChallengeDetailViewModel by viewModels()

    private var adapter: ChallengeDetailAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setSelectedAvailableChallenge(
            challengeTitle = arguments?.getString(CHALLENGE_TITLE) ?: "",
            challengeCategory = arguments?.getString(CHALLENGE_CATEGORY) ?: "",
            challengeUrl = arguments?.getString(CHALLENGE_URL) ?: "",
            challengeDesc = arguments?.getString(CHALLENGE_DESC) ?: "",
            challengeTime = arguments?.getString(CHALLENGE_TITLE) ?: "",
            challengeDuration = arguments?.getInt(CHALLENGE_DURATION) ?: 0,
            challengeCategoryNumber = arguments?.getInt(CHALLENGE_CATEGORY_NUMBER) ?: 0
        )

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

        collectAlertAsUpdated()

        lifecycleScope.launch {
            viewModel.allJoinableFilteredChallengesList.collect { joinableChallenges ->
                bindAdapter(joinableChallenges)
            }
        }
    }

    private fun bindAdapter(joinableChallenges: List<JoinableChallenges>) {
        binding?.let { binding ->
            binding.rvRelatedChallenges.isNestedScrollingEnabled = false
            binding.rvRelatedChallenges.layoutManager = LinearLayoutManager(activity)
            adapter = ChallengeDetailAdapter(
                viewModel,
                application,
                joinableChallenges,
                typefaces
            )
            binding.rvRelatedChallenges.adapter = adapter
        }
    }

    override fun updateTypefaces() {
        binding?.let { binding ->
            binding.tbhallengeDetails.tbStock.setTitleTextAppearance(
                application,
                R.style.ToolbarTextAppearance
            )

            typefaces.setTextViewSubHeaderBoldTypeface(binding.clDetailChallengeHeader.tvChallengeCategory)
            typefaces.setTextViewHeaderBoldTypeface(binding.clDetailChallengeHeader.tvChallengeTitle)
            typefaces.setTextViewBodyItalicTypeface(binding.clDetailChallengeHeader.tvChallengeDescDetails)
            typefaces.setTextViewHeaderBoldTypeface(binding.clDetailChallengeHeader.btnJoinChallenge)
        }
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

    override fun onListener() {
        binding?.let { binding ->
            binding.clDetailChallengeHeader.btnJoinChallenge.setOnClickListener {
                viewModel.onJoinChallengeClicked()
            }
            binding.tbhallengeDetails.tbStock.setOnClickListener {
                viewModel.onBackClicked()
            }
        }
    }

    override fun updateView() {
        binding?.let { binding ->
            binding.tbhallengeDetails.tbStock.setNavigationIcon(viewModel.toolbarBackImage)
            binding.tbhallengeDetails.tbStock.title =
                application.getString(R.string.challenge_details)

            binding.clDetailChallengeHeader.tvChallengeCategory.text = viewModel.viewState.category
            binding.clDetailChallengeHeader.tvChallengeTitle.text = viewModel.viewState.title
            binding.clDetailChallengeHeader.tvChallengeDescDetails.text =
                viewModel.viewState.description

            Glide.with(this).load(viewModel.viewState.categoryIcon)
                .into(binding.clDetailChallengeHeader.ivChallengeCategory)
            Glide.with(this).load(viewModel.viewState.challengeUrlImage)
                .into(binding.clDetailChallengeHeader.ivChallengesDetailsHeader)
        }
    }


}