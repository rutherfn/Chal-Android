package com.nicholasrutherford.chal.challengesredesign.challenges

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.databinding.FragmentRedesignChallengesBinding
import com.nicholasrutherford.chal.ext.fragments.challengesredesign.ChallengesRedesignFragmentExtension
import com.nicholasrutherford.chal.helpers.Typeface
import com.squareup.picasso.Picasso

const val placeHolderImage = "https://tsico.com/wp-content/uploads/2019/05/3-Unique-Debt-Collection-Challenges.jpg"

class ChallengesRedesignFragment(private val appContext: Context) :
    Fragment(),
    ChallengesRedesignFragmentExtension {

    private var challengesRedesignAdapter: ChallengesRedesignAdapter? = null
    private var viewModel: ChallengesRedesignViewModel? = null
    private val typeface = Typeface()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val bind = FragmentRedesignChallengesBinding.inflate(layoutInflater)
        fragmentManager?.let { fragmentManager -> viewModel = ChallengesRedesignViewModel(fragmentManager, appContext, requireActivity()) }
        bindAdapter(bind)
        updateTypefaces(bind)
        clickListeners(bind)
        updateView(bind)

        return bind.root
    }

    override fun updateTypefaces(bind: FragmentRedesignChallengesBinding) {
        typeface.setTypefaceForHeaderBold(bind.tbRedesignChallenges.tvTitle, appContext)

        typeface.setTypefaceForSubHeaderBold(bind.clChallengesHeader.tvChallenges, appContext)
        typeface.setTypefaceForBodyItalic(bind.clChallengesHeader.tvChallengesDescription, appContext)

        typeface.setTypefaceForSubHeaderBold(bind.clEndOfChallenges.tvEndOfChallenges, appContext)
    }

    override fun bindAdapter(bind: FragmentRedesignChallengesBinding) {
        bind.rvRedesignChallenges.isNestedScrollingEnabled = false
        bind.rvRedesignChallenges.layoutManager = LinearLayoutManager(activity)
        viewModel?.let { challengesRedesignViewModel ->
            challengesRedesignAdapter = ChallengesRedesignAdapter(challengesRedesignViewModel, appContext, challengesRedesignViewModel.liveChallengesList)
        }
        bind.rvRedesignChallenges.adapter = challengesRedesignAdapter
    }

    override fun clickListeners(bind: FragmentRedesignChallengesBinding) {
        viewModel?.let { challengesRedesignViewModel ->
            bind.tbRedesignChallenges.ivUploadChallenges.setOnClickListener { challengesRedesignViewModel.onHelpClicked() }
        }
    }

    override fun containerId(): Int {
        return R.id.container
    }

    override fun updateView(bind: FragmentRedesignChallengesBinding) {
        bind.tbRedesignChallenges.tvTitle.text = viewModel?.viewState?.toolbarName

        val options = RequestOptions()
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.placeholder)

        viewModel?.let { challengesViewModel ->
            Glide.with(this).load(challengesViewModel.viewState.toolbarImage).apply(options)
                .into(bind.tbRedesignChallenges.cvProfile)
        }

        Picasso.get().load(placeHolderImage).into(bind.clChallengesHeader.ivChallengesHeader)
        Glide.with(appContext).load(R.drawable.ic_question).into(bind.tbRedesignChallenges.ivUploadChallenges)
    }
}
