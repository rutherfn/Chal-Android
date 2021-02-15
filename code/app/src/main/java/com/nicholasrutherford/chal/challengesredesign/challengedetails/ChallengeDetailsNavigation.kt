package com.nicholasrutherford.chal.challengesredesign.challengedetails

import android.content.Context
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.nicholasrutherford.chal.ViewState
import com.nicholasrutherford.chal.data.realdata.Challenges

interface ChallengeDetailsNavigation : ViewState {
    fun showAlert(message: String, title: String, fragmentActivity: FragmentActivity)
    fun showAcProgress(fragmentActivity: FragmentActivity)
    fun showChallengeDetails(fragmentManager: FragmentManager, context: Context, challenge: Challenges)
    fun hideAcProgress()
}
