package com.nicholasrutherford.chal.newsfeed

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nicholasrutherford.chal.ChalRoom
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.MainActivity
import com.nicholasrutherford.chal.databinding.FragmentRedesignMyFeedBinding
import com.nicholasrutherford.chal.ext.fragments.newsfeed.NewsFeedRedesignFragmentExtension
import com.nicholasrutherford.chal.helpers.Typeface
import com.nicholasrutherford.chal.room.entity.firebasekey.FirebaseKeyEntity

class NewsFeedRedesignFragment (private val mainActivity: MainActivity, private val appContext: Context) : Fragment(),
        NewsFeedRedesignFragmentExtension {

    private var newsFeedRedesignAdapter: NewsFeedRedesignAdapter? = null
    private val typeface = Typeface()
    private var btNavigation: BottomNavigationView? = null
    private var viewModel: NewsFeedRedesignViewModel? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val bind = FragmentRedesignMyFeedBinding.inflate(layoutInflater)
        convertFirebaseKeysEntity()

        updateTypefaces(bind)
        bindAdapter(bind)
        clickListeners(bind)
        updateView(bind)

        return bind.root
    }
    override fun bindAdapter(bind: FragmentRedesignMyFeedBinding) {
        bind.rvNewsFeedRedesign.isNestedScrollingEnabled = false
        bind.rvNewsFeedRedesign.layoutManager = LinearLayoutManager(activity)

        newsFeedRedesignAdapter = NewsFeedRedesignAdapter(appContext)
        bind.rvNewsFeedRedesign.adapter = newsFeedRedesignAdapter
    }

    override fun updateTypefaces(bind: FragmentRedesignMyFeedBinding) {
        typeface.setTypefaceForHeaderBold(bind.tbRedesignChallenges.tvTitle, appContext)
        typeface.setTypefaceForSubHeaderBold(bind.clEndOfChallenges.tvEndOfChallenges, appContext)
    }

    override fun clickListeners(bind: FragmentRedesignMyFeedBinding) {
    }

    override fun containerId(): Int {
        return R.id.container
    }

    override fun updateView(bind: FragmentRedesignMyFeedBinding) {
        bind.tbRedesignChallenges.tvTitle.text = viewModel?.viewState?.toolbarName
//        Picasso.get().load(viewModel?.viewState?.toolbarImage).into(bind.tbRedesignChallenges.cvProfile)
    }

    override fun convertFirebaseKeysEntity() {
        val chalRoom = ChalRoom(mainActivity.application)
        chalRoom.readAllFirebaseKeys.observe(viewLifecycleOwner, Observer { firebaseEntityList ->
            initViewModel(firebaseEntityList)
        })

    }

    override fun initViewModel(firebaseEntityList: List<FirebaseKeyEntity>) {
        btNavigation = (activity as MainActivity).binding?.bvNavigation
        btNavigation?.let { bottomNavigationView ->
            viewModel = fragmentManager?.let { fragmentManager ->
                NewsFeedRedesignViewModel(
                    mainActivity,
                    appContext,
                    fragmentManager,
                    containerId(),
                    bottomNavigationView,
                    firebaseEntityList
                )
            }
        }
    }

}