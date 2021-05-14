package com.nicholasrutherford.chal.people

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.nicholasrutherford.chal.databinding.FragmentSearchPeopleBinding
import com.nicholasrutherford.chal.ext.fragments.searchpeople.SearchPeople
import com.nicholasrutherford.chal.helpers.Helper
import com.nicholasrutherford.chal.helpers.visibleOrGone
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class SearchPeopleFragment @Inject constructor(private val application: Application) :
    DaggerFragment(),
    SearchPeople {

    private var isBackStack = false

    private val helper = Helper()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    val searchPeopleViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)
            .get(SearchPeopleViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val bind = FragmentSearchPeopleBinding.inflate(layoutInflater)
        isBackStack = arguments?.getBoolean(helper.IS_BACK_STACK) ?: false

        bindAdapter(bind)
        clickListeners(bind)
        updateView(bind)

        return bind.root
    }

    override fun bindAdapter(bind: FragmentSearchPeopleBinding) {

    }

    override fun clickListeners(bind: FragmentSearchPeopleBinding) {
    }

    override fun updateView(bind: FragmentSearchPeopleBinding) {
        bind.tbSearchPeople.tvTitle.text = searchPeopleViewModel.viewState.toolbarName
        bind.tbSearchPeople.ibBack.visibleOrGone = isBackStack
    }
}