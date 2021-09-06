package com.nicholasrutherford.chal.main.splash

import com.nicholasrutherford.chal.main.navigation.Navigator
import javax.inject.Inject

class SplashNavigationImpl @Inject constructor(
): SplashNavigation {

    @Inject
    lateinit var navigator: Navigator

    override fun showHome() {
        // show home
    }

    override fun showlogin() {
       navigator.navigate(R.id.nav_graph_login)
    }
}