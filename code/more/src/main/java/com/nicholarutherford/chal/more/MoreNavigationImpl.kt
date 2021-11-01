package com.nicholarutherford.chal.more

import com.nicholasrutherford.chal.main.navigation.Navigator
import com.nicholasrutherford.chal.shared.preference.remove.RemoveSharedPreference
import javax.inject.Inject

class MoreNavigationImpl @Inject constructor(
    private val removeSharedPreference: RemoveSharedPreference
) : MoreNavigation {

    @Inject
    lateinit var navigator: Navigator

    override fun showUploadProgress() {
        navigator.navigate(R.id.nav_graph_upload_progress)
    }

    override fun showLogin(loginNavigationId: Int) {

        // known bug that needs to get fixed
        navigator.navigate(loginNavigationId)
        removeSharedPreference.removeLoginNavigationId()
    }

    override fun showDebug() = navigator.navigate(R.id.nav_graph_debug)

    override fun showProfile() = navigator.navigate(R.id.nav_graph_profile)
}