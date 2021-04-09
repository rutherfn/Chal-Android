package com.nicholasrutherford.chal.newsfeed

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.auth.FirebaseAuth
import com.nicholasrutherford.chal.MainActivity
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.data.responses.CurrentActiveChallengesResponse
import com.nicholasrutherford.chal.data.responses.NewsFeedResponse
import com.nicholasrutherford.chal.databinding.FragmentRedesignMyFeedBinding
import com.nicholasrutherford.chal.ext.fragments.newsfeed.NewsFeedRedesignFragmentExtension
import com.nicholasrutherford.chal.helpers.PeekingLinearLayoutManager
import com.nicholasrutherford.chal.helpers.Typeface
import com.nicholasrutherford.chal.helpers.visibleOrGone
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class NewsFeedRedesignFragment(private val mainActivity: MainActivity, private val appContext: Context) :
    Fragment(),
    NewsFeedRedesignFragmentExtension {

    private var mAuth: FirebaseAuth? = null

    private var myChallengesHeaderAdapter: MyChallengesHeaderAdapter? = null
    private var newsFeedRedesignAdapter: NewsFeedRedesignAdapter? = null

    private val typeface = Typeface()
    private var viewModel: NewsFeedRedesignViewModel? = null

    private var currentUserNewsFeedList: List<NewsFeedResponse> = ArrayList()
    private var allActiveNewsFeedList: List<NewsFeedResponse> = ArrayList()

    init {
        mAuth = FirebaseAuth.getInstance()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val bind = FragmentRedesignMyFeedBinding.inflate(layoutInflater)

        viewModel = NewsFeedRedesignViewModel(mainActivity, appContext)

        lifecycleScope.launch {
            viewModel?.let { newsFeedRedesignViewModel ->
                newsFeedRedesignViewModel.userNewsFeed.collect { newsFeedList ->
                    currentUserNewsFeedList = newsFeedList
                }
            }
        }

        lifecycleScope.launch {
            viewModel?.let { newsFeedRedesignViewModel ->
                newsFeedRedesignViewModel.currentUserActiveChallenges.collect { activeChallengesList ->
                    bind.tvMyChallenges.visibleOrGone = activeChallengesList.isNotEmpty()
                    bindHeaderAdapter(bind, activeChallengesList)
                }
            }
        }

        lifecycleScope.launch {
            viewModel?.let { newsFeedRedesignViewModel ->
                newsFeedRedesignViewModel.newsFeed.collect { newsFeedList ->
                    allActiveNewsFeedList = newsFeedList
                    bind.clEndOfFeed.tvEndOfFeed.visibleOrGone = newsFeedRedesignViewModel.viewState.isEndOfNewsFeedVisible
                    bindAdapter(bind, newsFeedList)
                }
            }
        }

        updateTypefaces(bind)
        clickListeners(bind)
        updateView(bind)

        return bind.root
    }

    override fun bindHeaderAdapter(bind: FragmentRedesignMyFeedBinding, listOfActiveChallenges: List<CurrentActiveChallengesResponse>) {
        bind.rvChallengeHeader.isNestedScrollingEnabled = false

        bind.rvChallengeHeader.layoutManager = PeekingLinearLayoutManager(appContext, RecyclerView.HORIZONTAL)
        bind.rvChallengeHeader.itemAnimator = DefaultItemAnimator()

        viewModel?.let { newsFeedRedesignViewModel ->
            myChallengesHeaderAdapter = MyChallengesHeaderAdapter(appContext, newsFeedRedesignViewModel, listOfActiveChallenges)
            bind.rvChallengeHeader.adapter = myChallengesHeaderAdapter
        }
    }
    override fun bindAdapter(bind: FragmentRedesignMyFeedBinding, newsFeedList: List<NewsFeedResponse>) {
        bind.rvNewsFeedRedesign.isNestedScrollingEnabled = false
        bind.rvNewsFeedRedesign.layoutManager = LinearLayoutManager(activity)

        viewModel?.let { newsFeedRedesignViewModel ->
            newsFeedRedesignAdapter = NewsFeedRedesignAdapter(appContext, newsFeedList, newsFeedRedesignViewModel)
            bind.rvNewsFeedRedesign.adapter = newsFeedRedesignAdapter
        }
    }

    override fun updateTypefaces(bind: FragmentRedesignMyFeedBinding) {
        typeface.setTypefaceForHeaderBold(bind.tbMyFeed.tvTitle, appContext)
        typeface.setTypefaceForSubHeaderBold(bind.clEndOfFeed.tvEndOfFeed, appContext)
        typeface.setTypefaceForSubHeaderBold(bind.clChallengeFeed.tvChallengeFeed, appContext)
        typeface.setTypefaceForSubHeaderBold(bind.tvMyChallenges, appContext)

        typeface.setTypefaceForBodyBold(bind.clChallengeFeed.btnAll, appContext)
        typeface.setTypefaceForBodyBold(bind.clChallengeFeed.btnFriends, appContext)
        typeface.setTypefaceForBodyBold(bind.clChallengeFeed.btnMyPosts, appContext)
    }

    override fun containerId(): Int {
        return R.id.container
    }

    override fun clickListeners(bind: FragmentRedesignMyFeedBinding) {
        bind.clChallengeFeed.btnAll.setOnClickListener {
            if (allActiveNewsFeedList.isNotEmpty()) {
                bind.rvNewsFeedRedesign.visibleOrGone = true
                bind.clFriendsEmptyState.clAddFriend.visibleOrGone = false
                updateButtonsAllClicked(bind)
                bindAdapter(bind, allActiveNewsFeedList)
            }
        }
        bind.clChallengeFeed.btnFriends.setOnClickListener {
            bind.rvNewsFeedRedesign.visibleOrGone = false // place holder
            bind.clFriendsEmptyState.clAddFriend.visibleOrGone = true
            bind.clEndOfFeed.tvEndOfFeed.visibleOrGone = false
            updateButtonsFriendsClicked(bind)
        }
        bind.clChallengeFeed.btnMyPosts.setOnClickListener {
            if (allActiveNewsFeedList.isNotEmpty()) {
                bind.rvNewsFeedRedesign.visibleOrGone = true
                bind.clFriendsEmptyState.clAddFriend.visibleOrGone = false
                updateButtonsMyPostsClicked(bind)
                bindAdapter(bind, currentUserNewsFeedList)
            }
        }
        bind.clFriendsEmptyState.button2.setOnClickListener {
            viewModel?.let { newsFeedRedesignViewModel ->
                newsFeedRedesignViewModel.onAddFriendsClicked()
            }
        }
        bind.tbMyFeed.cvProfile.setOnClickListener {
        }
        bind.tbMyFeed.tvTitle.setOnClickListener {
        }
        bind.tbMyFeed.ivUploadChallenges.setOnClickListener { viewModel?.onUploadProgressClicked() }
    }

    private fun updateButtonsAllClicked(bind: FragmentRedesignMyFeedBinding) {
        bind.clChallengeFeed.btnAll.setTextColor(Color.parseColor("#000000"))
        bind.clChallengeFeed.btnFriends.setTextColor(Color.parseColor("#FFFFFF"))
        bind.clChallengeFeed.btnMyPosts.setTextColor(Color.parseColor("#FFFFFF"))

        bind.clChallengeFeed.btnAll.background = ContextCompat.getDrawable(appContext, R.drawable.corner_button_white)
        bind.clChallengeFeed.btnFriends.background = ContextCompat.getDrawable(appContext, R.drawable.corner_button)
        bind.clChallengeFeed.btnMyPosts.background = ContextCompat.getDrawable(appContext, R.drawable.corner_button)
    }

    private fun updateButtonsFriendsClicked(bind: FragmentRedesignMyFeedBinding) {
        bind.clChallengeFeed.btnAll.setTextColor(Color.parseColor("#FFFFFF"))
        bind.clChallengeFeed.btnFriends.setTextColor(Color.parseColor("#000000"))
        bind.clChallengeFeed.btnMyPosts.setTextColor(Color.parseColor("#FFFFFF"))

        bind.clChallengeFeed.btnAll.background = ContextCompat.getDrawable(appContext, R.drawable.corner_button)
        bind.clChallengeFeed.btnFriends.background = ContextCompat.getDrawable(appContext, R.drawable.corner_button_white)
        bind.clChallengeFeed.btnMyPosts.background = ContextCompat.getDrawable(appContext, R.drawable.corner_button)
    }

    private fun updateButtonsMyPostsClicked(bind: FragmentRedesignMyFeedBinding) {
        bind.clChallengeFeed.btnAll.setTextColor(Color.parseColor("#FFFFFF"))
        bind.clChallengeFeed.btnFriends.setTextColor(Color.parseColor("#FFFFFF"))
        bind.clChallengeFeed.btnMyPosts.setTextColor(Color.parseColor("#000000"))

        bind.clChallengeFeed.btnAll.background = ContextCompat.getDrawable(appContext, R.drawable.corner_button)
        bind.clChallengeFeed.btnFriends.background = ContextCompat.getDrawable(appContext, R.drawable.corner_button)
        bind.clChallengeFeed.btnMyPosts.background = ContextCompat.getDrawable(appContext, R.drawable.corner_button_white)
    }

    override fun updateView(bind: FragmentRedesignMyFeedBinding) {
        bind.tbMyFeed.tvTitle.text = viewModel?.viewState?.toolbarName

        val options = RequestOptions()
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.placeholder)

        viewModel?.let { newsFeedViewModel ->
            bind.clEndOfFeed.tvEndOfFeed.visibleOrGone = newsFeedViewModel.viewState.isEndOfNewsFeedVisible
            Glide.with(this).load(newsFeedViewModel.viewState.toolbarImage).apply(options)
                .into(bind.tbMyFeed.cvProfile)
        }

        Glide.with(appContext).load(R.drawable.ic_add).into(bind.tbMyFeed.ivUploadChallenges)
    }
}
