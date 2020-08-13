package com.nicholasrutherford.chal.navigation.otheruserprofile

import androidx.fragment.app.FragmentManager
import com.nicholasrutherford.chal.fragments.SearchPeopleFragment
import com.nicholasrutherford.chal.navigation.Navigation

interface OtherUserProfileNavigation : Navigation {
    fun showSearchPeopleFragment(isClicked: Boolean, fragmentManager: FragmentManager, id: Int, searchPeopleFragment: SearchPeopleFragment)
}