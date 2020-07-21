package com.nicholasrutherford.chal.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.recycler.adapters.SuggestedFriends

class SuggestedFriendsFragment() : Fragment() {

    private var mView: View? = null

    private lateinit var rvFriends: RecyclerView
    private lateinit var svFriends: SearchView

    private var adapterSuggestedFriends: SuggestedFriends? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.fragment_friends, container, false)
        main()
        return mView
    }

    private fun main() {

        setupIds()

        setupRecyclerViewToAdapter()
    }

    private fun setupIds() {
        rvFriends = mView!!.findViewById(R.id.rvFriends)
        svFriends = mView!!.findViewById(R.id.svFriends)
    }

    private fun setupRecyclerViewToAdapter() {

        rvFriends.isNestedScrollingEnabled = false

        rvFriends.layoutManager = LinearLayoutManager(activity)

        adapterSuggestedFriends = context?.let { SuggestedFriends(it) }
        rvFriends.adapter = adapterSuggestedFriends
    }
}