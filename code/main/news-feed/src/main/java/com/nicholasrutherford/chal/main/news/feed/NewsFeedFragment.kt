package com.nicholasrutherford.chal.main.news.feed

import android.os.Bundle
import android.view.View
import com.nicholasrutherford.chal.main.news.feed.databinding.FragmentNewsFeedBinding
import com.nicholasrutherford.chal.ui.base_fragment.BaseFragment
import com.nicholasrutherford.chal.ui.typefaces.Typefaces
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NewsFeedFragment @Inject constructor() : BaseFragment<FragmentNewsFeedBinding>(
    FragmentNewsFeedBinding::inflate) {

    @Inject
    lateinit var typefaces: Typefaces

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun updateTypefaces() {

    }

    override fun collectAlertAsUpdated() {
    }

    override fun onListener() {
    }

    override fun updateView() {
    }
}