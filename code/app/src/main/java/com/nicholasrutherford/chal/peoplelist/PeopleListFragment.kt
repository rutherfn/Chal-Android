package com.nicholasrutherford.chal.peoplelist

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.data.responses.PeopleListResponse
import com.nicholasrutherford.chal.databinding.PeopleListFragmentBinding
import com.nicholasrutherford.chal.ext.fragments.peoplelist.PeopleListFragmentExtension
import com.nicholasrutherford.chal.navigationimpl.peoplelist.PeopleListNavigationImpl
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class PeopleListFragment @Inject constructor(private val application: Application) : DaggerFragment(), PeopleListFragmentExtension {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    val peopleListViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)
            .get(PeopleListViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        val bind = PeopleListFragmentBinding.inflate(layoutInflater)

        clickListeners(bind, PeopleListNavigationImpl(this))
        updateView(bind)
        
        lifecycleScope.launch { 
            peopleListViewModel.peopleList.collect { profileListResponse ->
                bindAdapter(bind, profileListResponse)
            }
        }

        return bind.root
    }

    override fun bindAdapter(bind: PeopleListFragmentBinding,
        peopleListResponse: MutableList<PeopleListResponse>) {
        bind.rvPeopleList.isNestedScrollingEnabled = false
        bind.rvPeopleList.layoutManager = LinearLayoutManager(application.applicationContext)

        val peopleListAdapter = PeopleListAdapter(
            application,
            peopleListViewModel,
            peopleListResponse
        )

        bind.rvPeopleList.adapter = peopleListAdapter

        bind.svPeopleList.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                peopleListAdapter.filter.filter(newText)
                return true
            }
        })
    }

    override fun clickListeners(bind: PeopleListFragmentBinding,
        peopleListNavigationImpl: PeopleListNavigationImpl) {
        bind.tbPeopleList.ibMoreBack.setOnClickListener {
            peopleListNavigationImpl.pop()
        }
    }

    override fun containerId(): Int {
        return R.id.container
    }

    override fun updateView(bind: PeopleListFragmentBinding) {
        bind.tbPeopleList.tvTitle.text = application.applicationContext.getString(R.string.find_friends)
    }
}
