package com.nicholasrutherford.chal.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.activitys.MainActivity
import com.nicholasrutherford.chal.databinding.FragmentHomeBinding
import com.nicholasrutherford.chal.helpers.Helper
import com.nicholasrutherford.chal.helpers.Typeface
import com.nicholasrutherford.chal.helpers.visibleOrGone
import com.nicholasrutherford.chal.recycler.adapters.HomeAdapter
import com.nicholasrutherford.chal.viewmodels.HomeViewModel
import com.squareup.picasso.Picasso

class HomeFragment : Fragment() {

    private var homeAdapter: HomeAdapter? = null
    private var typeface = Typeface()
    private var helper = Helper()
    private var screenContext: Context? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentHomeBinding.inflate(layoutInflater)
        main(binding)
        return binding.root
    }

    override fun onAttach(context: Context) {
        screenContext = context
        super.onAttach(context)
    }

    private fun setupToolbar(binding: FragmentHomeBinding) {
        Picasso.get().load(R.drawable.willplaceholder).into(binding.tbHome.cvProfile)
        context?.let {
            typeface.setTypefaceForHeaderBold(binding.tbHome.tvTitle, it)

            helper.setTextViewColor(it, binding.tbHome.tvTitle, R.color.colorSmokeWhite)
        }
    }

    private fun toolbarClickListeners(binding: FragmentHomeBinding) {
        binding.tbHome.tvTitle.setOnClickListener { showProfileFragment() }
        binding?.tbHome.ivPlus.setOnClickListener { showChallengePostFragment() }
        //binding.tbHome.ivPlus.setOnClickListener { showEditProfileFragment() }
    }

    private fun showProfileFragment() {
        fragmentManager?.beginTransaction()
            ?.replace(R.id.container, myProfileFragment, myProfileFragment.javaClass.simpleName)
            ?.commit()

        if(screenContext != null) {
            (activity as MainActivity).binding?.bvNavigation?.visibleOrGone = false
        }
    }

    private fun showChallengePostFragment() {
        fragmentManager?.beginTransaction()
            ?.replace(R.id.container, challengePostFragment, challengePostFragment::javaClass.name)
            ?.commit()

        if(screenContext != null) {
            (activity as MainActivity).binding?.bvNavigation?.visibleOrGone = false
        }
    }

    private fun main(binding: FragmentHomeBinding) {
        setupToolbar(binding)

        toolbarClickListeners(binding)

        binding.rvHome.isNestedScrollingEnabled = false

        binding.rvHome.layoutManager = LinearLayoutManager(activity)

        val viewModel = activity?.let { HomeViewModel(it) }

        homeAdapter = context?.let { HomeAdapter(viewModel!!, it) }
        binding.rvHome.adapter = homeAdapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}