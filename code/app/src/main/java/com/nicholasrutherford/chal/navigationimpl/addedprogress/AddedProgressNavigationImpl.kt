package com.nicholasrutherford.chal.navigationimpl.addedprogress

import android.app.Application
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.addedProgress.AddedProgressNavigation
import com.nicholasrutherford.chal.main.MainActivity
import com.nicholasrutherford.chal.newsfeed.NewsFeedFragment
import javax.inject.Inject

class AddedProgressNavigationImpl @Inject constructor(
    private val application: Application,
    private val activity: MainActivity
) : AddedProgressNavigation {

    override fun pop() = activity.supportFragmentManager.popBackStack()

    override fun showNewsFeed() {
        activity.supportFragmentManager.beginTransaction()
            .replace(
                R.id.container,
                NewsFeedFragment(application),
                NewsFeedFragment(application)::javaClass.name
            )
            .addToBackStack(null)
            .commit()
    }

}