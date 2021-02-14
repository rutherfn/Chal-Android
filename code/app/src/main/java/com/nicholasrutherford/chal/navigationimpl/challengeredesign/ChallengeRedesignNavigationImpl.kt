package com.nicholasrutherford.chal.navigationimpl.challengeredesign

import android.content.Context
import androidx.fragment.app.FragmentManager
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.challengeDetailsFragment
import com.nicholasrutherford.chal.challengesredesign.challenges.ChallengesRedesignNavigation
import com.nicholasrutherford.chal.data.realdata.Challenges

const val container = R.id.container

class ChallengeRedesignNavigationImpl : ChallengesRedesignNavigation {
    override fun showChallengeDetails(fragmentManager: FragmentManager, context: Context, challenge: Challenges) {
        fragmentManager.beginTransaction()
            .replace(
                container,
                challengeDetailsFragment(context, challenge),
                challengeDetailsFragment(context, challenge)::javaClass.name
            )
            .commit()
    }
}
