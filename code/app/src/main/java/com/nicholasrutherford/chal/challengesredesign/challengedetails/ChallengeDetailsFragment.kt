package com.nicholasrutherford.chal.challengesredesign.challengedetails

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.data.realdata.Challenges
import com.nicholasrutherford.chal.data.responses.CurrentActiveChallengesResponse
import com.nicholasrutherford.chal.databinding.FragmentChallengeDetailsBinding
import com.nicholasrutherford.chal.ext.fragments.challengedetails.ChallengeDetailsFragmentExtension
import com.nicholasrutherford.chal.helpers.Typeface
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ChallengeDetailsFragment(private val appContext: Context, private val challenge: Challenges) :
    Fragment(),
    ChallengeDetailsFragmentExtension {

    private var relatedChallengesAdapter: RelatedChallengesAdapter? = null
    private var typeface = Typeface()
    private var viewModel: ChallengeDetailsViewModel? = null

    private var currentActiveChallengesResponseList: List<CurrentActiveChallengesResponse>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val bind = FragmentChallengeDetailsBinding.inflate(layoutInflater)
        viewModel = ChallengeDetailsViewModel(appContext, challenge, requireActivity())
        updateTypefaces(bind)
        bindAdapter(bind)
        collectChallenges()
        clickListeners(bind)
        updateView(bind)

        return bind.root
    }

    override fun bindAdapter(bind: FragmentChallengeDetailsBinding) {
        bind.rvRelatedChallenges.isNestedScrollingEnabled = false
        bind.rvRelatedChallenges.layoutManager = LinearLayoutManager(activity)
        viewModel?.let { redesignChallengesViewModel ->
            relatedChallengesAdapter = RelatedChallengesAdapter(appContext, redesignChallengesViewModel.liveChallengesFilterList, redesignChallengesViewModel)
        }
        bind.rvRelatedChallenges.adapter = relatedChallengesAdapter
    }

    override fun updateTypefaces(bind: FragmentChallengeDetailsBinding) {
        typeface.setTypefaceForHeaderBold(bind.tbRedesignChallengeDetails.tvTitle, appContext)
        typeface.setTypefaceForSubHeaderBold(bind.clDetailsChallengeHeader.tvChallengeCategory, appContext)
        typeface.setTypefaceForHeaderBold(bind.clDetailsChallengeHeader.tvChallengeTitle, appContext)
        typeface.setTypefaceForBodyItalic(bind.clDetailsChallengeHeader.tvChallengeDescDetails, appContext)
        typeface.setTypefaceForHeaderBold(bind.clDetailsChallengeHeader.btnJoinChallenge, appContext)
    }

    override fun containerId(): Int {
        return R.id.container
    }

    private fun categoryChallengeIcon(): Int {
        return when (challenge.categoryNumber) {
            0 -> {
                R.drawable.ic_health_wellness
            }
            1 -> {
                R.drawable.ic_intellectual
            }
            else -> {
                R.drawable.ic_lifestyle
            }
        }
    }

    private fun collectChallenges() {
        lifecycleScope.launch {
            viewModel?.let { challengeDetailsViewModel ->
                challengeDetailsViewModel.activeChallengesResponse.collect { activeChallengesList ->
                    currentActiveChallengesResponseList = activeChallengesList
                }
            }
        }
    }

    override fun clickListeners(bind: FragmentChallengeDetailsBinding) {
        bind.clDetailsChallengeHeader.btnJoinChallenge.setOnClickListener {
            viewModel?.let { challengeDetailsViewModel -> challengeDetailsViewModel.onJoinChallengeClicked(currentActiveChallengesResponseList) }
        }
    }

    override fun updateView(bind: FragmentChallengeDetailsBinding) {
        bind.tbRedesignChallengeDetails.tvTitle.text = appContext.getText(R.string.challenge_details)

        bind.clDetailsChallengeHeader.tvChallengeCategory.text = challenge.category
        bind.clDetailsChallengeHeader.tvChallengeTitle.text = challenge.title
        bind.clDetailsChallengeHeader.tvChallengeDescDetails.text = challenge.desc

        Glide.with(appContext).load(categoryChallengeIcon()).into(bind.clDetailsChallengeHeader.ivChallengeCategory)
        Glide.with(appContext).load(challenge.url).into(bind.clDetailsChallengeHeader.ivChallengesDetailsHeader)
    }
}
