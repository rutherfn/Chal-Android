package com.nicholasrutherford.chal.main.upload.progress

import com.nicholasrutherford.chal.main.navigation.Navigator
import javax.inject.Inject

class UploadProgressNavigationImpl @Inject constructor() : UploadProgressNavigation {

    @Inject
    lateinit var navigator: Navigator

    override fun onNavigateBack() = navigator.navigateBack()

    override fun showAddedProgress() {
    }
}