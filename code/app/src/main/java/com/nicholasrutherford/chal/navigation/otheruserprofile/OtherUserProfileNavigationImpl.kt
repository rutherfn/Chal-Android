package com.nicholasrutherford.chal.navigation.otheruserprofile

import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nicholasrutherford.chal.fragments.searchPeopleFragment
import com.nicholasrutherford.chal.helpers.visibleOrGone


class OtherUserProfileNavigationImpl : OtherUserProfileNavigation {
    override fun showSearchPeopleFragment(isClicked: Boolean, fragmentManager: FragmentManager, id: Int, bottomNavigationView: BottomNavigationView) {
        if(isClicked) {
            bottomNavigationView.visibleOrGone = true
            fragmentManager.beginTransaction()
                .replace(id, searchPeopleFragment,
                    searchPeopleFragment::javaClass.name)
                .commit()
        }
    }

}