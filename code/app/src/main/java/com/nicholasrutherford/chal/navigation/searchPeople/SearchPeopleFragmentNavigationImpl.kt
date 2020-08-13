package com.nicholasrutherford.chal.navigation.searchPeople

import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nicholasrutherford.chal.fragments.OtherUserProfileFragment
import com.nicholasrutherford.chal.helpers.visibleOrGone

class SearchPeopleFragmentNavigationImpl : SearchPeopleFragmentNavigation {
    override fun showOtherUserProfileFragment(bottomNavigationView: BottomNavigationView, isClicked: Boolean, fragmentManager: FragmentManager, id: Int, otherUserProfileFragment: OtherUserProfileFragment) {
        if(isClicked) {
            bottomNavigationView.visibleOrGone = false
            fragmentManager.beginTransaction()
                .replace(id, otherUserProfileFragment,
                    otherUserProfileFragment::javaClass.name)
                .commit()
        }
    }

}