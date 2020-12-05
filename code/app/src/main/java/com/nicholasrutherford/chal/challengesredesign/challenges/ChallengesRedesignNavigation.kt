package com.nicholasrutherford.chal.challengesredesign.challenges

import android.content.Context
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nicholasrutherford.chal.activitys.MainActivity

interface ChallengesRedesignNavigation {
    fun showChallengeDetails(activity: MainActivity, context: Context, isClicked: Boolean, fragmentManager: FragmentManager, id: Int, bottomNavigationView: BottomNavigationView)
}