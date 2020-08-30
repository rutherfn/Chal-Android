package com.nicholasrutherford.chal.fragments.challenges

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.activitys.MainActivity
import com.nicholasrutherford.chal.databinding.FragmentChallengesBinding
import com.nicholasrutherford.chal.fragments.editProfileFragment
import com.nicholasrutherford.chal.fragments.singleChallengeFragment
import com.nicholasrutherford.chal.helpers.Typeface
import com.nicholasrutherford.chal.helpers.visibleOrGone
import com.nicholasrutherford.chal.recycler.adapters.SearchChallenges
import com.squareup.picasso.Picasso

class ChallengesFragment : Fragment() {

    private var mView: View? = null
    private val typeface = Typeface()
    private var screenContext: Context? = null

    // adapters
    private var adapterSearchChallenges: SearchChallenges? = null
    private var binding: FragmentChallengesBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentChallengesBinding.inflate(layoutInflater)
        main()
        return binding?.root
    }

    override fun onAttach(context: Context) {
        screenContext = context
        super.onAttach(context)
    }

    private fun main() {
        setupSomethingUp()
        clickListeners()
    }

    fun setupSomethingUp() {
        Picasso.get().load(R.drawable.willplaceholder).into(binding?.tbChallenges?.cvProfile)
        binding?.tbChallenges?.tvTitle?.let { it -> typeface.setTypefaceForHeaderBold(it, it.context) }

        setRecyclerToSearchChallengesAdapt()
    }

    private fun setupRecyclerViewForSearchChallengesAdapt() {
        binding?.rvChallenges?.adapter = null

        adapterSearchChallenges = null

        binding?.rvChallenges?.isNestedScrollingEnabled = false
        binding?.rvChallenges?.layoutManager = GridLayoutManager(activity, 3)
    }

    private fun setRecyclerToSearchChallengesAdapt() {

        setupRecyclerViewForSearchChallengesAdapt()

        adapterSearchChallenges = context?.let {
            SearchChallenges(
                it
            )
        }
        binding?.rvChallenges?.adapter = adapterSearchChallenges
    }

    fun clickListeners() {
        binding?.rvChallenges?.setOnClickListener {
            showSingleChallengeFragment()
        }
        binding?.tbChallenges?.ivFilter?.setOnClickListener {
            showSingleChallengeFragment()
        }
    }

    private fun showSingleChallengeFragment() {
        fragmentManager?.beginTransaction()
            ?.replace(R.id.container, singleChallengeFragment, singleChallengeFragment::javaClass.name)
            ?.commit()

        if(screenContext != null) {
            (activity as MainActivity).binding?.bvNavigation?.visibleOrGone = false
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }
}