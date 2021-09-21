package com.nicholasrutherford.chal.main.splash

import com.nicholasrutherford.chal.main.navigation.Navigator
import javax.inject.Inject

class SplashNavigationImpl @Inject constructor(
): SplashNavigation {

    @Inject
    lateinit var navigator: Navigator

    override fun showNewsFeed() {
        navigator.navigate(R.id.nav_graph_news_feed)
    }

    override fun showlogin() {
        println("show login")
       navigator.navigate(R.id.nav_graph_login)
    }
}