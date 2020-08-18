package com.nicholasrutherford.chal.navigation.myprofile

import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nicholasrutherford.chal.fragments.homeFragment
import com.nicholasrutherford.chal.fragments.myprofile.MyProfileNavigation
import com.nicholasrutherford.chal.helpers.visibleOrGone

class MyProfileNavigationImpl : MyProfileNavigation {
    override fun showHomeProfileFragment(isClicked: Boolean, fragmentManager: FragmentManager, id: Int, bottomNavigationView: BottomNavigationView) {
        if(isClicked) {
            bottomNavigationView.visibleOrGone = true
            fragmentManager.beginTransaction()
                .replace(id, homeFragment,
                    homeFragment::javaClass.name)
                .commit()
        }
    }

}