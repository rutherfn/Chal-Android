package com.nicholasrutherford.chal.challengesredesign.challengedetails

import com.nicholasrutherford.chal.activitys.MainActivity
import com.nicholasrutherford.chal.viewstate.ViewState

interface ChallengeDetailsNavigation : ViewState {
    fun showAcProgress(mainActivity: MainActivity)
    fun hideAcProgress()
}