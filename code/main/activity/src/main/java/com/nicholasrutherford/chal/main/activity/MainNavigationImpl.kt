package com.nicholasrutherford.chal.main.activity

import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import com.nicholasrutherford.chal.main.navigation.Navigator
import javax.inject.Inject

class MainNavigationImpl @Inject constructor() : MainNavigation {

    @Inject
    lateinit var navigator: Navigator

    override fun showNewsFeed() = navigator.navigate(R.id.nav_graph_news_feed)

    override fun showChallengesList() = navigator.navigate(R.id.nav_graph_challenge_list)

    override fun showMore() = navigator.navigate(R.id.nav_graph_more)
}