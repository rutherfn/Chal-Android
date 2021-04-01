package com.nicholasrutherford.chal.peoplelist

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class PeopleListFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    val peopleViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)
            .get(PeopleListViewModel::class.java)
    }
}
