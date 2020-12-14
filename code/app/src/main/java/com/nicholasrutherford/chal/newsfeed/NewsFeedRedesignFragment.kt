package com.nicholasrutherford.chal.newsfeed

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nicholasrutherford.chal.ChalRoom
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.MainActivity
import com.nicholasrutherford.chal.databinding.FragmentRedesignMyFeedBinding
import com.nicholasrutherford.chal.ext.fragments.newsfeed.NewsFeedRedesignFragmentExtension
import com.nicholasrutherford.chal.helpers.Typeface
import com.nicholasrutherford.chal.room.entity.challengesposts.ChallengesPostsEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class NewsFeedRedesignFragment (private val mainActivity: MainActivity, private val appContext: Context) : Fragment(),
        NewsFeedRedesignFragmentExtension {

    private var newsFeedRedesignAdapter: NewsFeedRedesignAdapter? = null
    private val typeface = Typeface()
    private var btNavigation: BottomNavigationView? = null
    private var viewModel: NewsFeedRedesignViewModel? = null

    private val _allChallengesPosts = MutableStateFlow(listOf<ChallengesPostsEntity>())
    private val allChallengesPosts: StateFlow<List<ChallengesPostsEntity>> = _allChallengesPosts

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val bind = FragmentRedesignMyFeedBinding.inflate(layoutInflater)
        newsListActiveChallengesPostsUpdate()

        lifecycleScope.launch {
            allChallengesPosts?.collect {
                initViewModel(it)
                bindAdapter(bind)
            }
        }

        updateTypefaces(bind)
        clickListeners(bind)
        updateView(bind)

        // test functionality
        return bind.root
    }
    override fun bindAdapter(bind: FragmentRedesignMyFeedBinding) {
        bind.rvNewsFeedRedesign.isNestedScrollingEnabled = false
        bind.rvNewsFeedRedesign.layoutManager = LinearLayoutManager(activity)

        viewModel?.let { newsFeedRedesignViewModel ->
            newsFeedRedesignAdapter = NewsFeedRedesignAdapter(appContext, newsFeedRedesignViewModel)
            bind.rvNewsFeedRedesign.adapter = newsFeedRedesignAdapter
        }
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

        val options = RequestOptions()
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.placeholder)

        viewModel?.let { newsFeedViewModel ->
            Glide.with(this).load(newsFeedViewModel.viewState.toolbarImage).apply(options)
                .into(bind.tbRedesignChallenges.cvProfile)
        }
    }

    fun newsListActiveChallengesPostsUpdate() {
        val chalRoom  = ChalRoom(mainActivity.application)

        lifecycleScope.launch {
            chalRoom.userRepository.readAllUsersRegular().forEach { users ->
                users.activeChallengeEntities?.forEach { challengesPosts ->
                    challengesPosts.activeChallengesPosts?.let { challengesPostsList ->
                        _allChallengesPosts.value = challengesPostsList
                    }
                }
            }
        }
    }

    override fun convertFirebaseKeysEntity() {
    }

    override fun initViewModel(activeChallengesPostsList: List<ChallengesPostsEntity>) {
        viewModel = NewsFeedRedesignViewModel(mainActivity, appContext, activeChallengesPostsList)
    }

}