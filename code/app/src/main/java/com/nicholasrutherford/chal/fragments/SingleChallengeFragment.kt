package com.nicholasrutherford.chal.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nicholasrutherford.chal.databinding.FragmentSingleChallengesBinding
import com.nicholasrutherford.chal.helpers.Helper
import com.nicholasrutherford.chal.helpers.Typeface

// declarations
private val typeface = Typeface()
private val helper = Helper()

class SingleChallengeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentSingleChallengesBinding.inflate(layoutInflater)
        main(binding)
        return binding.root
    }

    private fun main(binding: FragmentSingleChallengesBinding) {

        context.let {

        }
    }

    private fun setupToolbar(binding: FragmentSingleChallengesBinding, screenContext: Context) {

    }

}