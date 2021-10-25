package com.nicholasrutherford.chal.main.upload.progress

import androidx.core.os.bundleOf
import com.nicholasrutherford.chal.helper.constants.CHALLENGE_ADDED_PROGRESS_DAY
import com.nicholasrutherford.chal.helper.constants.CHALLENGE_ADDED_PROGRESS_TITLE
import com.nicholasrutherford.chal.main.navigation.Navigator
import javax.inject.Inject

class UploadProgressNavigationImpl @Inject constructor() : UploadProgressNavigation {

    @Inject
    lateinit var navigator: Navigator

    override fun onNavigateBack() = navigator.navigateBack()

}