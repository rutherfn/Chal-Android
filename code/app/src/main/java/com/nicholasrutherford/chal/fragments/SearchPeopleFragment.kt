package com.nicholasrutherford.chal.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.databinding.FragmentFriendsBinding
import com.nicholasrutherford.chal.helpers.visibleOrGone
import com.nicholasrutherford.chal.recycler.adapters.SuggestedFriends
import com.nicholasrutherford.chal.viewmodels.SearchPeopleViewModel
import com.squareup.picasso.Picasso

class SearchPeopleFragment() : Fragment(), FragmentExt {

    private var binding: FragmentFriendsBinding? = null
    private var viewModel: SearchPeopleViewModel? = null
    private var adapterSuggestedFriends: SuggestedFriends? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentFriendsBinding.inflate(layoutInflater)
        viewModel = context?.let { SearchPeopleViewModel(it) }
        bind()
        clickListeners()
        return binding?.root
    }

    private fun setupRecyclerToAdapter() {
        binding?.rvFriends?.isNestedScrollingEnabled = false

        binding?.rvFriends?.layoutManager = LinearLayoutManager(activity)

        adapterSuggestedFriends = context?.let { viewModel?.viewState?.searchPeopleList?.let { it1 ->
            SuggestedFriends(it,
                it1
            )
        } }

        println(viewModel?.viewState?.searchPeopleList?.size)

        binding?.rvFriends?.adapter = adapterSuggestedFriends
    }

    override fun bind() {
        setupRecyclerToAdapter()
        Picasso.get().load(R.drawable.willplaceholder).into(binding?.tbSearch?.cvProfile)
    }

    override fun updateFragment() {

    }

    override fun clickListeners() {
        binding?.tbSearch?.ivSearch?.setOnClickListener {
            binding?.tbSearch?.clSearchToolbar?.visibleOrGone = false
            binding?.tbSearchFilter?.clSearchResultsToolbar?.visibleOrGone = true
        }
        binding?.tbSearchFilter?.svPeople?.setOnQueryTextListener(object: OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                println("Calling query")
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                println("Calling query")
                adapterSuggestedFriends?.filter?.filter(newText)
                return false
            }

        })
    }

    override fun containerId(): Int { return R.id.container }
}