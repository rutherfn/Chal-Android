package com.nicholasrutherford.chal.screens.home

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
import com.nicholasrutherford.chal.databinding.FragmentHomeBinding
import com.nicholasrutherford.chal.fragments.FragmentExt
import com.nicholasrutherford.chal.helpers.Helper
import com.nicholasrutherford.chal.helpers.Typeface
import com.nicholasrutherford.chal.navigation.home.HomeNavigationImpl
import com.squareup.picasso.Picasso

class HomeFragment : Fragment(), FragmentExt {

    private var homeAdapter: HomeAdapter? = null
    val navigation = HomeNavigationImpl()
    private var typeface = Typeface()
    private var helper = Helper()
    private var screenContext: Context? = null
    private var binding: FragmentHomeBinding? = null
    private var btNavigation: BottomNavigationView? = null
    private var viewModel: HomeViewModel? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        btNavigation = (activity as MainActivity).binding?.bvNavigation!!
        bind()
        updateFragment()
        clickListeners()
        return binding?.root
    }

    override fun onAttach(context: Context) {
        screenContext = context
        super.onAttach(context)
    }

    private fun setupAdapter() {
        val viewModel = activity?.let { HomeViewModel(fragmentManager!!, containerId(), it, btNavigation!!) }
        homeAdapter = context?.let { HomeAdapter(viewModel!!, it) }
        binding?.rvHome?.adapter = homeAdapter
    }

    override fun bind() {
        binding?.rvHome?.isNestedScrollingEnabled = false
        binding?.rvHome?.layoutManager = LinearLayoutManager(activity)
        setupAdapter()
    }

    override fun updateFragment() {
        Picasso.get().load(R.drawable.willplaceholder).into(binding?.tbHome?.cvProfile)

        binding?.tbHome?.tvTitle?.let {
            typeface.setTypefaceForHeaderBold(it, it.context)
            helper.setTextViewColor(it.context, it, R.color.colorSmokeWhite)
        }
        // if data needs to get updated coming back to this fragment, call the methods here. or if data needs to get set
    }

    override fun clickListeners() {
        binding?.tbHome?.tvTitle?.setOnClickListener { fragmentManager?.let { it1 ->
            btNavigation?.let { it2 ->
                navigation.showProfileFragment(
                    it1, containerId(), it2
                )
            }
        } }
        binding?.tbHome?.ivPlus?.setOnClickListener {  navigation.showChallengePostFragment(fragmentManager!!, containerId(), btNavigation!!) }
    }

    override fun updateColors() {
    }

    override fun updateTypefaces() {
    }

    override fun containerId(): Int {
        return R.id.container
    }

}