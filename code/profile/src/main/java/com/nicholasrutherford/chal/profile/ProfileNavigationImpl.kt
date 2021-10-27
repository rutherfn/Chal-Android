package com.nicholasrutherford.chal.profile

import com.nicholasrutherford.chal.main.navigation.Navigator
import javax.inject.Inject

class ProfileNavigationImpl @Inject constructor(): ProfileNavigation  {

    @Inject
    lateinit var navigator: Navigator

    override fun showEditProfile() = navigator.navigate(R.id.nav_graph_edit_profile)

    override fun showPop() = navigator.navigateBack()
}