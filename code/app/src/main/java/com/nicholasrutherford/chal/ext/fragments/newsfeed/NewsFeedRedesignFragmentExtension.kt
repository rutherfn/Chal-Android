package com.nicholasrutherford.chal.ext.fragments.newsfeed

import com.nicholasrutherford.chal.databinding.FragmentRedesignMyFeedBinding
import com.nicholasrutherford.chal.room.entity.activechallenges.ActiveChallengesEntity

interface NewsFeedRedesignFragmentExtension {
    fun bindAdapter(bind: FragmentRedesignMyFeedBinding)
    fun convertFirebaseKeysEntity()
    fun initViewModel(activeChallengesList: List<ActiveChallengesEntity>)
    fun updateTypefaces(bind: FragmentRedesignMyFeedBinding)
    fun clickListeners(bind: FragmentRedesignMyFeedBinding)
    fun containerId(): Int
    fun updateView(bind: FragmentRedesignMyFeedBinding)
}