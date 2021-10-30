package com.nicholasrutherford.chal.profile

import androidx.core.os.bundleOf
import com.nicholasrutherford.chal.helper.constants.INDEX
import com.nicholasrutherford.chal.main.navigation.Navigator
import javax.inject.Inject

class ProfileNavigationImpl @Inject constructor(): ProfileNavigation  {

    @Inject
    lateinit var navigator: Navigator

    override fun showEditProfile() = navigator.navigate(R.id.nav_graph_edit_profile)

    override fun showPop() = navigator.navigateBack()

    override fun showProfileChallengePosts(index: String) {
        var bundle =
            bundleOf(
                INDEX to index
            )

        navigator.navigateWithBundle(bundle, R.id.nav_graph_profile_challenges_posts)
    }
}