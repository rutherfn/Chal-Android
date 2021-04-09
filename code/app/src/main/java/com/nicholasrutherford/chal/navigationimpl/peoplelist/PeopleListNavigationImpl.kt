package com.nicholasrutherford.chal.navigationimpl.peoplelist

import com.nicholasrutherford.chal.peoplelist.PeopleListFragment
import com.nicholasrutherford.chal.peoplelist.PeopleListNavigation
import javax.inject.Inject

class PeopleListNavigationImpl @Inject constructor(private val fragment: PeopleListFragment) : PeopleListNavigation {

    override fun pop() { fragment.fragmentManager?.popBackStack() }
}
