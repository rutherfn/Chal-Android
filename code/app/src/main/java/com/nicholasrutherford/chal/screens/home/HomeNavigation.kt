package com.nicholasrutherford.chal.screens.home

import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nicholasrutherford.chal.navigation.Navigation
import com.nicholasrutherford.chal.navigation.NavigationOverride

interface HomeNavigation : Navigation {
    val navigationOverride: NavigationOverride
    fun showProfileFragment(fragmentManager: FragmentManager, id: Int, bottomNavigation: BottomNavigationView)
    fun showChallengePostFragment(fragmentManager: FragmentManager, id: Int, bottomNavigation: BottomNavigationView)
}