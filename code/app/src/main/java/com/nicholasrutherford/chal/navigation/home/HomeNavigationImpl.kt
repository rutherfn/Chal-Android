package com.nicholasrutherford.chal.navigation.home

import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nicholasrutherford.chal.fragments.challengePostFragment
import com.nicholasrutherford.chal.fragments.myProfileFragment
import com.nicholasrutherford.chal.navigation.NavigationOverride
import com.nicholasrutherford.chal.screens.home.HomeNavigation

class HomeNavigationImpl() : HomeNavigation {
    override val navigationOverride = NavigationOverride()

    override fun showProfileFragment(fragmentManager: FragmentManager, id: Int, bottomNavigation: BottomNavigationView) {
        navigationOverride.showFragmentWithBottomNavigationDismissed(myProfileFragment, fragmentManager, id, bottomNavigation)
    }

    override fun showChallengePostFragment(fragmentManager: FragmentManager, id: Int, bottomNavigation: BottomNavigationView) {
        navigationOverride.showFragmentWithBottomNavigationDismissed(challengePostFragment, fragmentManager, id, bottomNavigation)
    }

}