package com.nicholasrutherford.chal.account.login

import com.nicholasrutherford.chal.main.navigation.Navigator
import javax.inject.Inject

class LoginNavigationImpl @Inject constructor(): LoginNavigation {

    @Inject
    lateinit var navigator: Navigator

    override fun showForgotPassword() {
        navigator.navigate(R.id.nav_graph_forgot_password)
    }
}