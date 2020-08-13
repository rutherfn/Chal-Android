package com.nicholasrutherford.chal.navigation.searchPeople

import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nicholasrutherford.chal.fragments.OtherUserProfileFragment
import com.nicholasrutherford.chal.navigation.Navigation

interface SearchPeopleFragmentNavigation : Navigation {
    fun showOtherUserProfileFragment(bottomNavigationView: BottomNavigationView, isClicked: Boolean, fragmentManager: FragmentManager, id: Int, otherUserProfileFragment: OtherUserProfileFragment)
}