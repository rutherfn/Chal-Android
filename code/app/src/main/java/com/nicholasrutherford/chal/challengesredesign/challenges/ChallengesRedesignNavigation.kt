package com.nicholasrutherford.chal.challengesredesign.challenges

import android.content.Context
import androidx.fragment.app.FragmentManager
import com.nicholasrutherford.chal.data.realdata.Challenges

interface ChallengesRedesignNavigation {
    fun showChallengeDetails(fragmentManager: FragmentManager, context: Context, challenge: Challenges)
}
