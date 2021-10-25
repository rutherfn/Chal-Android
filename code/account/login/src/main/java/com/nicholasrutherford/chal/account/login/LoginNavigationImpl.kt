package com.nicholasrutherford.chal.account.login

import com.nicholasrutherford.chal.main.navigation.Navigator
import com.nicholasrutherford.chal.shared.preference.create.CreateSharedPreference
import javax.inject.Inject

class LoginNavigationImpl @Inject constructor(
    private val createSharedPreference: CreateSharedPreference
): LoginNavigation {

    @Inject
    lateinit var navigator: Navigator

    private fun createloginNavigationId() {
        navigator.navController.currentDestination?.id?.let { loginNavigationId ->
            createSharedPreference.createLoginNavigationId(loginNavigationId)
        }
    }

    override fun showForgotPassword() {
        navigator.navigate(R.id.nav_graph_forgot_password,)
    }

    override fun showSignIn() {
        createloginNavigationId()
        navigator.navigate(R.id.nav_graph_sign_up)
    }

    override fun showNewsFeed() {
        createloginNavigationId()
        navigator.navigate(R.id.nav_graph_news_feed)
    }
}