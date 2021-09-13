package com.nicholasrutherford.chal.create.account

import com.nicholasrutherford.chal.main.navigation.Navigator
import javax.inject.Inject

class CreateAccountNavigationImpl @Inject constructor(): CreateAccountNavigation {

    @Inject
    lateinit var navigator: Navigator

    override fun pop() {
        navigator.navController.popBackStack()
    }
}