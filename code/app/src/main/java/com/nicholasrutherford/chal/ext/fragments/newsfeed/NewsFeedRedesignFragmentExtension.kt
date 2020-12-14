package com.nicholasrutherford.chal.ext.fragments.newsfeed

import com.nicholasrutherford.chal.databinding.FragmentRedesignMyFeedBinding
import com.nicholasrutherford.chal.room.entity.firebasekey.FirebaseKeyEntity

interface NewsFeedRedesignFragmentExtension {
    fun bindAdapter(bind: FragmentRedesignMyFeedBinding)
    fun convertFirebaseKeysEntity()
    fun initViewModel(firebaseEntityList: List<FirebaseKeyEntity>)
    fun updateTypefaces(bind: FragmentRedesignMyFeedBinding)
    fun clickListeners(bind: FragmentRedesignMyFeedBinding)
    fun containerId(): Int
    fun updateView(bind: FragmentRedesignMyFeedBinding)
}