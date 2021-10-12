package com.nicholasrutherford.chal.main.news.feed

import com.nicholasrutherford.chal.main.navigation.Navigator
import javax.inject.Inject

class NewsFeedNavigationImpl @Inject constructor() : NewsFeedNavigation {

    @Inject
    lateinit var navigator: Navigator

    override fun showUploadProgress() = navigator.navigate(R.id.nav_graph_upload_progress)
}