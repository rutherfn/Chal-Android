package com.nicholasrutherford.chal.challengesredesign.challengedetails

import androidx.fragment.app.FragmentActivity
import com.nicholasrutherford.chal.ViewState

interface ChallengeDetailsNavigation : ViewState {
    fun showAlert(message: String, title: String, fragmentActivity: FragmentActivity)
    fun showAcProgress(fragmentActivity: FragmentActivity)
    fun hideAcProgress()
}
