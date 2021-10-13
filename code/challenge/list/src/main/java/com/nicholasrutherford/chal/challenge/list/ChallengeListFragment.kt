package com.nicholasrutherford.chal.challenge.list

import android.app.Application
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nicholasrutherford.chal.challenge.list.databinding.FragmentChallengeListBinding
import com.nicholasrutherford.chal.ui.base_fragment.BaseFragment
import com.nicholasrutherford.chal.ui.typefaces.Typefaces
import com.squareup.picasso.Picasso
import con.nicholasrutherford.chal.data.challenges.JoinableChallenges
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ChallengeListFragment @Inject constructor(): BaseFragment<FragmentChallengeListBinding>(
    FragmentChallengeListBinding::inflate) {

    @Inject
    lateinit var typefaces: Typefaces

    @Inject
    lateinit var application: Application

    private val viewModel: ChallengeListViewModel by viewModels()

    private var adapter: ChallengeListAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            collectViewStateResult(viewModel.viewStateUpdated, viewModel._viewStateUpdated)
        }
        lifecycleScope.launch {
            viewModel.allJoinableChallengesList.collect { joinableChallenges ->
                bindChallengeListAdapter(joinableChallenges)
            }
        }

        collectAlertAsUpdated()
    }

    private fun bindChallengeListAdapter(joinableChallenges: List<JoinableChallenges>) {
        binding?.let { binding ->
            binding.rvChallengesList.isNestedScrollingEnabled = false
            binding.rvChallengesList.layoutManager = LinearLayoutManager(activity)
            adapter = ChallengeListAdapter(
                viewModel,
                application,
                joinableChallenges,
                typefaces
            )
            binding.rvChallengesList.adapter = adapter
        }
    }

    override fun updateTypefaces() {
        binding?.let { binding ->
            typefaces.setTextViewHeaderBoldTypeface(binding.tbChallenges.tvTitle)

            typefaces.setTextViewSubHeaderBoldTypeface(binding.clChallengesHeader.tvChallenges)
            typefaces.setTextViewHeaderBoldTypeface(binding.clChallengesHeader.tvChallengesDescription)

            typefaces.setTextViewSubHeaderBoldTypeface(binding.clEndOfChallenges.tvEndOfChallenges)
        }
    }

    override fun collectAlertAsUpdated() {
        lifecycleScope.launch {
            viewModel.shouldShowAlert.collect { isShouldShowAlert ->
                if (isShouldShowAlert) {
                    showOkAlert(title = viewModel.alertTitle, message = viewModel.alertDescription)
                }
                viewModel.setShouldShowAlertAsNotUpdated()
            }
        }
    }

    override fun onListener() {
        binding?.let { binding ->
            binding.tbChallenges.ivUploadChallenges.setOnClickListener { viewModel.onUploadChallengeClicked() }
        }
    }

    override fun updateView() {
        binding?.let { binding ->
            binding.tbChallenges.tvTitle.text = viewModel.viewState.toolbarName

            val options = RequestOptions()
                .placeholder(R.drawable.circle)
                .error(R.drawable.circle)

            Glide.with(this).load(viewModel.viewState.toolbarImage).apply(options)
                .into(binding.tbChallenges.cvProfile)

            Picasso.get().load(viewModel.viewState.challengeHeaderImageUrl)
                .into(binding.clChallengesHeader.ivChallengesHeader)
            Glide.with(this).load(R.drawable.ic_question)
                .into(binding.tbChallenges.ivUploadChallenges)
        }
    }
}