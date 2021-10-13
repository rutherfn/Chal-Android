package com.nicholarutherford.chal.more

import com.nicholasrutherford.chal.main.navigation.Navigator
import javax.inject.Inject

class MoreNavigationImpl @Inject constructor() : MoreNavigation {

    @Inject
    lateinit var navigator: Navigator

    override fun showUploadProgress() = navigator.navigate(R.id.nav_graph_upload_progress)

    override fun showLogin() {

    }

    override fun showDebug() = navigator.navigate(R.id.nav_graph_debug)
}