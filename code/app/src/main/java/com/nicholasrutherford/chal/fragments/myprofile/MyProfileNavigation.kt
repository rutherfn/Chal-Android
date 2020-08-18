package com.nicholasrutherford.chal.fragments.myprofile

import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nicholasrutherford.chal.navigation.Navigation

interface MyProfileNavigation : Navigation {
    fun showHomeProfileFragment(isClicked: Boolean, fragmentManager: FragmentManager, id: Int, bottomNavigationView: BottomNavigationView)
}