package com.nicholasrutherford.chal.navigation.otheruserprofile

import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nicholasrutherford.chal.navigation.Navigation

interface OtherUserProfileNavigation : Navigation {
    fun showSearchPeopleFragment(isClicked: Boolean, fragmentManager: FragmentManager, id: Int, bottomNavigationView: BottomNavigationView)
}