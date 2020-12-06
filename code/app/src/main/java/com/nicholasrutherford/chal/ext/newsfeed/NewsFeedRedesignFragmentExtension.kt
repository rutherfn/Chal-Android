package com.nicholasrutherford.chal.ext.newsfeed

import com.nicholasrutherford.chal.databinding.FragmentRedesignMyFeedBinding

interface NewsFeedRedesignFragmentExtension {
    fun bindAdapter(bind: FragmentRedesignMyFeedBinding)
    fun updateTypefaces(bind: FragmentRedesignMyFeedBinding)
    fun clickListeners(bind: FragmentRedesignMyFeedBinding)
    fun containerId(): Int
    fun updateView(bind: FragmentRedesignMyFeedBinding)
}