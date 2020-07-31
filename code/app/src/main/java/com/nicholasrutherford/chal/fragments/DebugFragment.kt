package com.nicholasrutherford.chal.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.databinding.FragmentDebugBinding
import com.nicholasrutherford.chal.recycler.adapters.DebugAdapter
import com.nicholasrutherford.chal.viewmodels.DebugViewModel

class DebugFragment : Fragment() {

    private var debugAdapter: DebugAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentDebugBinding.inflate(layoutInflater)
        val optionsList = resources.getStringArray(R.array.debugOptions)
        main(binding, optionsList)
        return binding.root
    }

    private fun main(binding: FragmentDebugBinding, optionsList: Array<String>) {
        binding.rvDebug.isNestedScrollingEnabled = false

        binding.rvDebug.layoutManager = LinearLayoutManager(activity)

        val viewModel = DebugViewModel(optionsList)

        debugAdapter = context?.let { DebugAdapter(viewModel, it) }
        binding.rvDebug.adapter = debugAdapter
    }
}