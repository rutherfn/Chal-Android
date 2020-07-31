package com.nicholasrutherford.chal.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.databinding.FragmentHomeBinding
import com.nicholasrutherford.chal.recycler.adapters.HomeAdapter
import com.nicholasrutherford.chal.viewmodels.HomeViewModel

class HomeFragment : Fragment() {

    private var homeAdapter: HomeAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentHomeBinding.inflate(layoutInflater)
        main(binding)
        return binding.root
    }

    private fun main(binding: FragmentHomeBinding) {
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