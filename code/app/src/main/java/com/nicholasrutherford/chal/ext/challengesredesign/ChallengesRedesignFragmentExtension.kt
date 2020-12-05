package com.nicholasrutherford.chal.ext.challengesredesign

import com.nicholasrutherford.chal.databinding.FragmentRedesignChallengesBinding

interface ChallengesRedesignFragmentExtension {
    fun bindAdapter(bind: FragmentRedesignChallengesBinding)
    fun updateTypefaces(bind: FragmentRedesignChallengesBinding)
    fun clickListeners(bind: FragmentRedesignChallengesBinding)
    fun containerId(): Int
    fun updateView(bind: FragmentRedesignChallengesBinding)
}