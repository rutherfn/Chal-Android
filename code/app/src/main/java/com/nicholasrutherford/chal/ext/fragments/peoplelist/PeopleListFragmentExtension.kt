package com.nicholasrutherford.chal.ext.fragments.peoplelist

import com.nicholasrutherford.chal.data.responses.ProfileListResponse
import com.nicholasrutherford.chal.databinding.PeopleListFragmentBinding
import com.nicholasrutherford.chal.navigationimpl.peoplelist.PeopleListNavigationImpl

interface PeopleListFragmentExtension {
    fun bindAdapter(bind: PeopleListFragmentBinding, profileListResponse: MutableList<ProfileListResponse>)
    fun clickListeners(bind: PeopleListFragmentBinding, peopleListNavigationImpl: PeopleListNavigationImpl)
    fun containerId(): Int
    fun updateView(bind: PeopleListFragmentBinding)
}
