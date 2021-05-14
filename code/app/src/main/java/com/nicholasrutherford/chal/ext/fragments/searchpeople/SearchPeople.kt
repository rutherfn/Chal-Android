package com.nicholasrutherford.chal.ext.fragments.searchpeople

import com.nicholasrutherford.chal.Extension
import com.nicholasrutherford.chal.databinding.FragmentSearchPeopleBinding

interface SearchPeople : Extension {
    fun bindAdapter(bind: FragmentSearchPeopleBinding) // search people list
    fun clickListeners(bind: FragmentSearchPeopleBinding) // add navigation impl
    fun updateView(bind: FragmentSearchPeopleBinding)
}