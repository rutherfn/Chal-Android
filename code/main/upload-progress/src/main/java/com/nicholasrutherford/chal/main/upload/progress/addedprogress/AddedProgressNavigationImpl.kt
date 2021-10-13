package com.nicholasrutherford.chal.main.upload.progress.addedprogress

import com.nicholasrutherford.chal.main.navigation.Navigator
import com.nicholasrutherford.chal.main.upload.progress.R
import javax.inject.Inject

class AddedProgressNavigationImpl @Inject constructor() : AddedProgressNavigation {

    @Inject
    lateinit var navigator: Navigator

    override fun onNavigateBack() = navigator.navigateBack()

    override fun onShowNewsFeed() {

    }

}