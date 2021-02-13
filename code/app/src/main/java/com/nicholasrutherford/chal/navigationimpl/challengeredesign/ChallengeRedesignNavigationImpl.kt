package com.nicholasrutherford.chal.navigationimpl.challengeredesign

import android.content.Context
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nicholasrutherford.chal.MainActivity
import com.nicholasrutherford.chal.challengeDetailsFragment
import com.nicholasrutherford.chal.challengesredesign.challenges.ChallengesRedesignNavigation
import com.nicholasrutherford.chal.helpers.visibleOrGone

class ChallengeRedesignNavigationImpl : ChallengesRedesignNavigation {
    override fun showChallengeDetails(activity: MainActivity, context: Context, isClicked: Boolean, fragmentManager: FragmentManager, id: Int, bottomNavigationView: BottomNavigationView) {
        if (isClicked) {
            bottomNavigationView.visibleOrGone = true
            fragmentManager.beginTransaction()
                .replace(
                    id,
                    challengeDetailsFragment(
                        activity,
                        context
                    ),
                    challengeDetailsFragment(
                        activity,
                        context
                    )::javaClass.name
                )
                .commit()
        }
    }
}
