package com.nicholasrutherford.chal.profile.edit

import com.nicholasrutherford.chal.main.navigation.Navigator
import javax.inject.Inject

class EditProfileNavigationImpl @Inject constructor() : EditProfileNavigation {

    @Inject
    lateinit var navigator: Navigator

    override fun navigateBack() = navigator.navigateBack()
}