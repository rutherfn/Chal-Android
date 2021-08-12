package com.nicholasrutherford.chal.ext.fragments.newsfeed

import com.nicholasrutherford.chal.data.responses.activechallenges.ActiveChallengesListResponse
import com.nicholasrutherford.chal.data.responses.post.PostListResponse
import com.nicholasrutherford.chal.databinding.FragmentRedesignMyFeedBinding

interface NewsFeedRedesignFragmentExt {
    fun collectResults(bind: FragmentRedesignMyFeedBinding)
    fun updateTypefaces(bind: FragmentRedesignMyFeedBinding)
    fun bindHeaderAdapter(bind: FragmentRedesignMyFeedBinding, listOfActiveChallenges: List<ActiveChallengesListResponse>)
    fun bindAdapter(bind: FragmentRedesignMyFeedBinding, newsFeedList: List<PostListResponse>)
    fun clickListeners(bind: FragmentRedesignMyFeedBinding)
    fun updateView(bind: FragmentRedesignMyFeedBinding)
}
