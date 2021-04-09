package com.nicholasrutherford.chal.ext.fragments.newsfeed

import com.nicholasrutherford.chal.data.responses.CurrentActiveChallengesResponse
import com.nicholasrutherford.chal.data.responses.NewsFeedResponse
import com.nicholasrutherford.chal.databinding.FragmentRedesignMyFeedBinding
import com.nicholasrutherford.chal.navigationimpl.newsfeed.NewsFeedNavigationImpl

interface NewsFeedRedesignFragmentExtension {
    // collect functions
    fun collectUserNewsFeedListResults()
    fun collectActiveChallengesResults(bind: FragmentRedesignMyFeedBinding)
    fun collectNewsFeedResults(bind: FragmentRedesignMyFeedBinding)

    fun bindHeaderAdapter(bind: FragmentRedesignMyFeedBinding, listOfActiveChallenges: List<CurrentActiveChallengesResponse>)
    fun bindAdapter(bind: FragmentRedesignMyFeedBinding, newsFeedList: List<NewsFeedResponse>)
    fun updateTypefaces(bind: FragmentRedesignMyFeedBinding)
    fun clickListeners(bind: FragmentRedesignMyFeedBinding, newsFeedNavigationImpl: NewsFeedNavigationImpl)
    fun containerId(): Int
    fun updateView(bind: FragmentRedesignMyFeedBinding)
}
