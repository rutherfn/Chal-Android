package com.nicholasrutherford.chal.account.forgot.password

import android.app.Application
import com.nicholasrutherford.chal.main.navigation.Navigator
import javax.inject.Inject

class ForgotPasswordNavigationImpl @Inject constructor(
    private val application: Application
    ): ForgotPasswordNavigation {

    @Inject
    lateinit var navigator: Navigator

    override fun showForgotPasswordAlert(title: String, message: String) {
        println(":whole pie paid and wiating to go ")
    }
}