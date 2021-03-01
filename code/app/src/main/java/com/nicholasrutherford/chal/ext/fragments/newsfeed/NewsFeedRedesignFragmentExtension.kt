package com.nicholasrutherford.chal.ext.fragments.newsfeed

import com.nicholasrutherford.chal.data.responses.CurrentActiveChallengesResponse
import com.nicholasrutherford.chal.data.responses.NewsFeedResponse
import com.nicholasrutherford.chal.databinding.FragmentRedesignMyFeedBinding

interface NewsFeedRedesignFragmentExtension {
    fun bindHeaderAdapter(bind: FragmentRedesignMyFeedBinding, listOfActiveChallenges: List<CurrentActiveChallengesResponse>)
    fun bindAdapter(bind: FragmentRedesignMyFeedBinding, newsFeedList: List<NewsFeedResponse>)
    fun updateTypefaces(bind: FragmentRedesignMyFeedBinding)
    fun clickListeners(bind: FragmentRedesignMyFeedBinding)
    fun containerId(): Int
    fun updateView(bind: FragmentRedesignMyFeedBinding)
}
