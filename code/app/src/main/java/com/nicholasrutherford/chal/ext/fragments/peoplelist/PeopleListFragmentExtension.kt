package com.nicholasrutherford.chal.ext.fragments.peoplelist

import com.nicholasrutherford.chal.data.responses.PeopleListResponse
import com.nicholasrutherford.chal.databinding.PeopleListFragmentBinding
import com.nicholasrutherford.chal.navigationimpl.peoplelist.PeopleListNavigationImpl

interface PeopleListFragmentExtension {
    fun bindAdapter(bind: PeopleListFragmentBinding, peopleListResponse: MutableList<PeopleListResponse>)
    fun clickListeners(bind: PeopleListFragmentBinding, peopleListNavigationImpl: PeopleListNavigationImpl)
    fun containerId(): Int
    fun updateView(bind: PeopleListFragmentBinding)
}
