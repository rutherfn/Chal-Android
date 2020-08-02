package com.nicholasrutherford.chal.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.activitys.MainActivity
import com.nicholasrutherford.chal.activitys.SettingsActivity
import com.nicholasrutherford.chal.databinding.FragmentHomeBinding
import com.nicholasrutherford.chal.helpers.Helper
import com.nicholasrutherford.chal.helpers.Typeface
import com.nicholasrutherford.chal.helpers.visibleOrGone
import com.nicholasrutherford.chal.recycler.adapters.HomeAdapter
import com.nicholasrutherford.chal.viewmodels.HomeViewModel

class HomeFragment : Fragment() {

    private var homeAdapter: HomeAdapter? = null
    private var typeface = Typeface()
    private var helper = Helper()
    private val myProfileFragment = MyProfileFragment()

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
        context?.let {
            typeface.setTypefaceForHeaderBold(binding.tbHome.tvTitle, it)
            typeface.setTypefaceForBodyBold(binding.tbHome.tvSubTitle, it)

            helper.setTextViewColor(it, binding.tbHome.tvTitle, R.color.colorSmokeWhite)
            helper.setTextViewColor(it, binding.tbHome.tvSubTitle, R.color.colorSmokeWhite)
        }
    }

    private fun toolbarClickListeners(binding: FragmentHomeBinding) {
        binding.tbHome.tvTitle.setOnClickListener { showProfileFragment() }
        binding.tbHome.tvSubTitle.setOnClickListener { startSettingsUpActivity() }
    }

    private fun showProfileFragment() {
        fragmentManager?.beginTransaction()
            ?.replace(R.id.container, myProfileFragment, myProfileFragment.javaClass.simpleName)
            ?.commit()

        if(screenContext != null) {
            (activity as MainActivity).binding?.bvNavigation?.visibleOrGone = false
        }
    }

    private fun startSettingsUpActivity() {
        val intent = Intent(screenContext, SettingsActivity::class.java)

        startActivity(intent)
        activity?.finish()
    }

    private fun main(binding: FragmentHomeBinding) {
        setupToolbar(binding)

        toolbarClickListeners(binding)

        binding.rvHome.isNestedScrollingEnabled = false

        binding.rvHome.layoutManager = LinearLayoutManager(activity)

        val viewModel = HomeViewModel()

        homeAdapter = context?.let { HomeAdapter(viewModel, it) }
        binding.rvHome.adapter = homeAdapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}