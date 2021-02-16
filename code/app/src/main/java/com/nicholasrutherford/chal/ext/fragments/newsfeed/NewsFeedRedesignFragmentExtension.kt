package com.nicholasrutherford.chal.ext.fragments.newsfeed

import com.nicholasrutherford.chal.data.responses.NewsFeedResponse
import com.nicholasrutherford.chal.databinding.FragmentRedesignMyFeedBinding
import com.nicholasrutherford.chal.room.entity.challengesposts.ChallengesPostsEntity

interface NewsFeedRedesignFragmentExtension {
    fun bindAdapter(bind: FragmentRedesignMyFeedBinding,  newsFeedList: List<NewsFeedResponse>)
    //fun convertFirebaseKeysEntity()
   // fun initViewModel(activeChallengesPostsList: List<ChallengesPostsEntity>)
    fun updateTypefaces(bind: FragmentRedesignMyFeedBinding)
    fun clickListeners(bind: FragmentRedesignMyFeedBinding)
    fun containerId(): Int
    fun updateView(bind: FragmentRedesignMyFeedBinding)
}
