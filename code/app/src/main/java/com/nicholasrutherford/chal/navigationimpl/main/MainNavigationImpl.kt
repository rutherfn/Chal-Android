package com.nicholasrutherford.chal.navigationimpl.main

import android.app.Application
import com.nicholasrutherford.chal.challengesredesign.challenges.ChallengesRedesignFragment
import com.nicholasrutherford.chal.main.MainActivity
import com.nicholasrutherford.chal.main.MainNavigation
import com.nicholasrutherford.chal.more.MoreFragment
import com.nicholasrutherford.chal.navigationimpl.challengeredesign.container
import com.nicholasrutherford.chal.newsfeed.NewsFeedFragment
import javax.inject.Inject

class MainNavigationImpl @Inject constructor(private val application: Application, private val mainActivity: MainActivity) : MainNavigation {

    override fun showChallenges() {
        mainActivity.supportFragmentManager.beginTransaction()
            .replace(
                container,
                ChallengesRedesignFragment(application),
                ChallengesRedesignFragment(application)::javaClass.name
            )
            .addToBackStack(null)
            .commit()
    }

    override fun showMewsFeed(backStack: String?) {
        mainActivity.supportFragmentManager.beginTransaction()
            .replace(
                container,
                NewsFeedFragment(application),
                NewsFeedFragment(application)::javaClass.name
            )
            .addToBackStack(backStack)
            .commit()
    }

    override fun showMore() {
        mainActivity.supportFragmentManager.beginTransaction()
            .replace(
                container,
                MoreFragment(application),
                MoreFragment(application)::javaClass.name
            )
            .addToBackStack(null)
            .commit()
    }
}