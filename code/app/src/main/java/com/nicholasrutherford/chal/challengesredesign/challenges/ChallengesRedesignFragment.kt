package com.nicholasrutherford.chal.challengesredesign.challenges

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.activitys.MainActivity
import com.nicholasrutherford.chal.databinding.FragmentRedesignChallengesBinding
import com.nicholasrutherford.chal.ext.challengesredesign.ChallengesRedesignFragmentExtension
import com.nicholasrutherford.chal.helpers.Typeface
import com.squareup.picasso.Picasso

class ChallengesRedesignFragment (private val mainActivity: MainActivity, private val appContext: Context) : Fragment(),
        ChallengesRedesignFragmentExtension {

    private var challengesRedesignAdapter: ChallengesRedesignAdapter? = null
    private var viewModel: ChallengesRedesignViewModel? = null
    private var btNavigation: BottomNavigationView? = null
    private val typeface = Typeface()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val bind = FragmentRedesignChallengesBinding.inflate(layoutInflater)
        btNavigation = (activity as MainActivity).binding?.bvNavigation
        btNavigation?.let { bottomNavigationView ->
            viewModel = fragmentManager?.let { fragmentManager ->
                ChallengesRedesignViewModel(
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

    override fun updateTypefaces(bind: FragmentRedesignChallengesBinding) {
        typeface.setTypefaceForHeaderBold(bind.tbRedesignChallenges.tvTitle, appContext)

        typeface.setTypefaceForSubHeaderBold(bind.clChallengesHeader.tvChallenges, appContext)
        typeface.setTypefaceForBodyItalic(bind.clChallengesHeader.tvChallengesDescription, appContext)

        typeface.setTypefaceForSubHeaderBold(bind.clEndOfChallenges.tvEndOfChallenges, appContext)
    }

    override fun bindAdapter(bind: FragmentRedesignChallengesBinding) {
        bind.rvRedesignChallenges.isNestedScrollingEnabled = false
        bind.rvRedesignChallenges.layoutManager = LinearLayoutManager(activity)
        btNavigation?.let { bottomNavigationView ->
            fragmentManager?.let { fragmentManager ->
                viewModel?.let { challengesRedesignViewModel ->
                    challengesRedesignAdapter =
                        ChallengesRedesignAdapter(
                            mainActivity,
                            challengesRedesignViewModel,
                            appContext,
                            fragmentManager,
                            containerId(),
                            bottomNavigationView
                        )
                }
            }
        }
        bind.rvRedesignChallenges.adapter = challengesRedesignAdapter
    }

    override fun clickListeners(bind: FragmentRedesignChallengesBinding) {
    }

    override fun containerId(): Int {
        return R.id.container
    }

    override fun updateView(bind: FragmentRedesignChallengesBinding) {
        bind.tbRedesignChallenges.tvTitle.text = viewModel?.viewState?.toolbarName
        Picasso.get().load(viewModel?.viewState?.toolbarImage).into(bind.tbRedesignChallenges.cvProfile)

        // temporary
        Picasso.get().load("https://tsico.com/wp-content/uploads/2019/05/3-Unique-Debt-Collection-Challenges.jpg")
            .into(bind.clChallengesHeader.ivChallengesHeader)
    }

}