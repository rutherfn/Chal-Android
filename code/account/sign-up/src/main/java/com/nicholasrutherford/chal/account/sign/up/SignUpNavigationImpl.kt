package com.nicholasrutherford.chal.account.sign.up

import com.nicholasrutherford.chal.main.navigation.Navigator
import javax.inject.Inject

class SignUpNavigationImpl @Inject constructor() : SignUpNavigation {

    @Inject
    lateinit var navigator: Navigator

    override fun pop() {
        navigator.navController.popBackStack()
    }

    override fun showCreateAccount() {
        navigator.navigate(R.id.nav_graph_create_account)
    }
}