package com.nicholasrutherford.chal.challengesredesign.challengedetails

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.MainActivity
import com.nicholasrutherford.chal.databinding.FragmentChallengeDetailsBinding
import com.nicholasrutherford.chal.ext.fragments.challengedetails.ChallengeDetailsFragmentExtension
import com.nicholasrutherford.chal.helpers.Typeface

class ChallengeDetailsFragment (private val mainActivity: MainActivity, private val appContext: Context): Fragment(),
        ChallengeDetailsFragmentExtension {

    private var relatedChallengesAdapter: RelatedChallengesAdapter? = null
    private var typeface = Typeface()
    private var viewModel: ChallengeDetailsViewModel? = null
    private var btNavigation: BottomNavigationView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val bind = FragmentChallengeDetailsBinding.inflate(layoutInflater)
        btNavigation = (activity as MainActivity).binding?.bvNavigation
        btNavigation?.let { bottomNavigationView ->
            viewModel = fragmentManager?.let { fragmentManager ->
                ChallengeDetailsViewModel(
                    mainActivity,
                    appContext,
                    fragmentManager,
                    containerId(),
                    bottomNavigationView
                )
            }
        }

        updateTypefaces(bind)
        bindAdapter(bind)
        clickListeners(bind)
        updateView(bind)

        return bind.root
    }

    override fun bindAdapter(bind: FragmentChallengeDetailsBinding) {
        bind.rvRelatedChallenges.isNestedScrollingEnabled = false
        bind.rvRelatedChallenges.layoutManager = LinearLayoutManager(activity)
        relatedChallengesAdapter = RelatedChallengesAdapter(appContext)
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

    override fun clickListeners(bind: FragmentChallengeDetailsBinding) {
        bind.clDetailsChallengeHeader.btnJoinChallenge.setOnClickListener {
            viewModel?.onJoinChallengeClicked()
        }
    }

    override fun updateView(bind: FragmentChallengeDetailsBinding) {

        bind.tbRedesignChallengeDetails.tvTitle.text = "Challenge Details"

        // placeholder temporary
        Glide.with(this).load("https://daveasprey.com/wp-content/uploads/2015/03/meditation_sun.jpg")
            .into(bind.clDetailsChallengeHeader.ivChallengesDetailsHeader)

    }
}