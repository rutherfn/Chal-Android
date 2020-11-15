package com.nicholasrutherford.chal.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.activitys.MainActivity
import com.nicholasrutherford.chal.databinding.FragmentFriendsBinding
import com.nicholasrutherford.chal.helpers.Helper
import com.nicholasrutherford.chal.helpers.Typeface
import com.nicholasrutherford.chal.helpers.visibleOrGone
import com.nicholasrutherford.chal.recycler.adapters.SuggestedFriends
import com.nicholasrutherford.chal.viewmodels.SearchPeopleViewModel
import com.squareup.picasso.Picasso

class SearchPeopleFragment() : Fragment(), FragmentExt {

    private var typeface = Typeface()
    private var helper = Helper()
    private var binding: FragmentFriendsBinding? = null
    private var viewModel: SearchPeopleViewModel? = null
    private var adapterSuggestedFriends: SuggestedFriends? = null
    private var btNavigation: BottomNavigationView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentFriendsBinding.inflate(layoutInflater)
        btNavigation = (activity as MainActivity).binding?.bvNavigation!!
        viewModel = context?.let { fragmentManager?.let { it1 ->
            SearchPeopleViewModel(
                btNavigation!!, it,
                it1, containerId(), otherUserProfileFragment)
        } }
        bind()
        clickListeners()
        return binding?.root
    }

    private fun setupRecyclerToAdapter() {
        binding?.rvFriends?.isNestedScrollingEnabled = false

        binding?.rvFriends?.layoutManager = LinearLayoutManager(activity)

        adapterSuggestedFriends = context?.let { viewModel?.viewState?.searchPeopleList?.let { it1 ->
            SuggestedFriends(it,
                it1, 
                viewModel
            )
        } }

        binding?.rvFriends?.adapter = adapterSuggestedFriends
    }

    override fun bind() {
        setupRecyclerToAdapter()
        context?.let {
            binding?.tbSearch?.tvTitle?.let { it1 -> typeface.setTypefaceForHeaderBold(it1, it) }

            binding?.tbSearch?.tvTitle?.let { it1 ->
                helper.setTextViewColor(it,
                    it1, R.color.colorSmokeWhite)
            }
        }
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

    override fun updateColors() {
    }

    override fun updateTypefaces() {
    }

    override fun containerId(): Int { return R.id.container }
}