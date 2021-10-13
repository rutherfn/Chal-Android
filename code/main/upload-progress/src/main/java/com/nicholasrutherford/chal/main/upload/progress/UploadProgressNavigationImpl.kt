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

    override fun showAddedProgress(challengeTitle: String, currentDay: Int) {
        val addedProgressBundle =
            bundleOf(
                CHALLENGE_ADDED_PROGRESS_TITLE to challengeTitle,
                CHALLENGE_ADDED_PROGRESS_DAY to currentDay
            )

        navigator.navigateWithBundle(addedProgressBundle, R.id.nav_graph_added_progress)
    }

}