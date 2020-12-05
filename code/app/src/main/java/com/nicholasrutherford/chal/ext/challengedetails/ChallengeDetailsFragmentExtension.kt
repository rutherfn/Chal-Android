package com.nicholasrutherford.chal.ext.challengedetails

import com.nicholasrutherford.chal.databinding.FragmentChallengeDetailsBinding

interface ChallengeDetailsFragmentExtension {
    fun bindAdapter(bind: FragmentChallengeDetailsBinding)
    fun updateTypefaces(bind: FragmentChallengeDetailsBinding)
    fun clickListeners(bind: FragmentChallengeDetailsBinding)
    fun containerId(): Int
    fun updateView(bind: FragmentChallengeDetailsBinding)
}