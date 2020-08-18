package com.nicholasrutherford.chal.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.databinding.FragmentChallengesBinding
import com.nicholasrutherford.chal.helpers.Typeface
import com.nicholasrutherford.chal.recycler.adapters.SearchChallenges
import com.squareup.picasso.Picasso

class ChallengesFragment : Fragment() {

    private var mView: View? = null
    private val typeface = Typeface()

    // adapters
    private var adapterSearchChallenges: SearchChallenges? = null
    private var binding: FragmentChallengesBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentChallengesBinding.inflate(layoutInflater)
        main()
        return binding?.root
    }

    private fun main() {
        setupSomethingUp()
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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }
}