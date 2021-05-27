package com.nicholasrutherford.chal.ext.fragments.newsfeed

import com.nicholasrutherford.chal.data.responses.CurrentActiveChallengesResponse
import com.nicholasrutherford.chal.data.responses.NewsFeedResponse
import com.nicholasrutherford.chal.databinding.FragmentRedesignMyFeedBinding

interface NewsFeedRedesignFragmentExt {
    fun collectResults(bind: FragmentRedesignMyFeedBinding)
    fun updateTypefaces(bind: FragmentRedesignMyFeedBinding)
    fun bindHeaderAdapter(bind: FragmentRedesignMyFeedBinding, listOfActiveChallenges: List<CurrentActiveChallengesResponse>)
    fun bindAdapter(bind: FragmentRedesignMyFeedBinding, newsFeedList: List<NewsFeedResponse>)
    fun clickListeners(bind: FragmentRedesignMyFeedBinding)
    fun updateView(bind: FragmentRedesignMyFeedBinding)
}
