package com.nicholasrutherford.chal.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nicholasrutherford.chal.helpers.visibleOrGone

class NavigationOverride {

    fun showFragmentWithBottomNavigationDismissed(fragment: Fragment, fragmentManager: FragmentManager, id: Int, bottomNavigation: BottomNavigationView) {
        fragmentManager.beginTransaction()
            .replace(id, fragment, fragment.javaClass.simpleName)
            .commit()
        bottomNavigation.visibleOrGone = false
    }
}