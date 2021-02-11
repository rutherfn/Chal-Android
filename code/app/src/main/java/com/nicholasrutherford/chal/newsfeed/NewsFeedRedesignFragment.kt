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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.nicholasrutherford.chal.ChalRoom
import com.nicholasrutherford.chal.MainActivity
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.databinding.FragmentRedesignMyFeedBinding
import com.nicholasrutherford.chal.ext.fragments.newsfeed.NewsFeedRedesignFragmentExtension
import com.nicholasrutherford.chal.firebase.USERS
import com.nicholasrutherford.chal.helpers.Typeface
import com.nicholasrutherford.chal.room.entity.challengesposts.ChallengesPostsEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class NewsFeedRedesignFragment(private val mainActivity: MainActivity, private val appContext: Context) : Fragment(),
        NewsFeedRedesignFragmentExtension {

    private val ref = FirebaseDatabase.getInstance().getReference(USERS)
    var mAuth: FirebaseAuth? = null

    private var newsFeedRedesignAdapter: NewsFeedRedesignAdapter? = null
    private val typeface = Typeface()
    private var viewModel: NewsFeedRedesignViewModel? = null

    private val _allChallengesPosts = MutableStateFlow(listOf<ChallengesPostsEntity>())
    private val allChallengesPosts: StateFlow<List<ChallengesPostsEntity>> = _allChallengesPosts

    init {
        mAuth = FirebaseAuth.getInstance()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val bind = FragmentRedesignMyFeedBinding.inflate(layoutInflater)
        newsListActiveChallengesPostsUpdate()

        lifecycleScope.launch {
            allChallengesPosts.collect { challengesPosts ->
                initViewModel(challengesPosts)
                bindAdapter(bind)
            }
        }

        updateTypefaces(bind)
        clickListeners(bind)
        updateView(bind)

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
        typeface.setTypefaceForHeaderBold(bind.tbMyFeed.tvTitle, appContext)
        typeface.setTypefaceForSubHeaderBold(bind.clEndOfFeed.tvEndOfFeed, appContext)
    }

    override fun containerId(): Int {
        return R.id.container
    }

    fun newsListActiveChallengesPostsUpdate() {
        val chalRoom = ChalRoom(mainActivity.application)

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

        lifecycleScope.launch {
            viewModel?.let { newsFeedViewModel ->
                newsFeedViewModel.allFirebaseKeys.collect {
                    newsFeedViewModel.fetchUsers(it)
                }
            }
        }

        lifecycleScope.launch {
            viewModel?.let { newsFeedViewModel ->
                newsFeedViewModel.allUsers.collect { usersLsit ->
                    println(usersLsit.size)
                }
            }
        }
    }

    override fun clickListeners(bind: FragmentRedesignMyFeedBinding) {
        bind.tbMyFeed.cvProfile.setOnClickListener {
        }
        bind.tbMyFeed.tvTitle.setOnClickListener {
        }
    }

    override fun updateView(bind: FragmentRedesignMyFeedBinding) {
        bind.tbMyFeed.tvTitle.text = viewModel?.viewState?.toolbarName

        val options = RequestOptions()
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.placeholder)

        viewModel?.let { newsFeedViewModel ->
            Glide.with(this).load(newsFeedViewModel.viewState.toolbarImage).apply(options)
                .into(bind.tbMyFeed.cvProfile)
        }
    }
}