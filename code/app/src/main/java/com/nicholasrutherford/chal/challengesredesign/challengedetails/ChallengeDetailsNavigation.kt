package com.nicholasrutherford.chal.challengesredesign.challengedetails

import com.nicholasrutherford.chal.activitys.MainActivity
import com.nicholasrutherford.chal.ViewState

interface ChallengeDetailsNavigation : ViewState {
    fun showAcProgress(mainActivity: MainActivity)
    fun hideAcProgress()
}