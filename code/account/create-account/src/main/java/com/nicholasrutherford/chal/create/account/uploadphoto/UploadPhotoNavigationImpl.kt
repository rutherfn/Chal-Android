package com.nicholasrutherford.chal.create.account.uploadphoto

import com.nicholasrutherford.chal.create.account.R
import com.nicholasrutherford.chal.main.navigation.Navigator
import javax.inject.Inject

class UploadPhotoNavigationImpl @Inject constructor(): UploadPhotoNavigation {

    @Inject
    lateinit var navigator: Navigator

    override fun showNewsFeed() = navigator.navigate(R.id.nav_graph_news_feed)
}