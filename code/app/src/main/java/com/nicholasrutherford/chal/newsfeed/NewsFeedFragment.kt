package com.nicholasrutherford.chal.newsfeed

import android.app.Application
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.data.responses.CurrentActiveChallengesResponse
import com.nicholasrutherford.chal.data.responses.NewsFeedResponse
import com.nicholasrutherford.chal.data.responses.post.PostListResponse
import com.nicholasrutherford.chal.databinding.FragmentRedesignMyFeedBinding
import com.nicholasrutherford.chal.ext.fragments.newsfeed.NewsFeedRedesignFragmentExt
import com.nicholasrutherford.chal.helpers.PeekingLinearLayoutManager
import com.nicholasrutherford.chal.helpers.Typeface
import com.nicholasrutherford.chal.helpers.visibleOrGone
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsFeedFragment @Inject constructor(private val application: Application) :
    DaggerFragment(),
    NewsFeedRedesignFragmentExt {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)
            .get(NewsFeedViewModel::class.java)
    }

    private var challengesHeaderAdapter: ChallengesHeaderAdapter? = null
    private var newsFeedRedesignAdapter: NewsFeedRedesignAdapter? = null

    private val typeface = Typeface()

    private var currentUserNewsFeedList: List<NewsFeedResponse> = ArrayList()
    private var allActiveNewsFeedList: List<PostListResponse> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val bind = FragmentRedesignMyFeedBinding.inflate(layoutInflater)

        collectResults(bind)

        updateTypefaces(bind)
        clickListeners(bind)

        bind.tbMyFeed.inflateMenu(R.menu.navigation_news_feed)

        return bind.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.navigation_refresh) {
            viewModel.onRefreshTabClicked()
            return true
        } else if (item.itemId == R.id.navigation_add_progress) {
            return true
        }
        return true
    }

    override fun collectResults(bind: FragmentRedesignMyFeedBinding) {
        // lifecycleScope.launch {
        //     viewModel.userNewsFeed.collect { newsFeedList ->
        //         currentUserNewsFeedList = newsFeedList
        //     }
        // }
        lifecycleScope.launch {
            viewModel.currentUserActiveChallenges.collect { activeChallengesList ->
                if (activeChallengesList.isNotEmpty()) {
                    viewModel.updateMyChallengesVisible(activeChallengesList)
                    updateView(bind)
                    bindHeaderAdapter(bind, activeChallengesList)
                }
            }
        }
        lifecycleScope.launch {
            viewModel.postList.collect { newsFeedList ->
                if (newsFeedList.isNotEmpty()) {
                    allActiveNewsFeedList = newsFeedList
                    bind.clEndOfFeed.tvEndOfFeed.visibleOrGone =
                        viewModel.viewState.isEndOfNewsFeedVisible
                    bindAdapter(bind, newsFeedList)
                }
            }
        }
        lifecycleScope.launch {
            viewModel.isViewStateUpdated.collect { isUpdated ->
                if (isUpdated) {
                    updateView(bind)
                    viewModel._isViewStateUpdated.value = false // we call this because once the view state is done updating, it needs to go back to its original value.
                }
            }
        }
    }

    override fun updateTypefaces(bind: FragmentRedesignMyFeedBinding) {
        typeface.setTypefaceForSubHeaderBold(bind.clEndOfFeed.tvEndOfFeed, application.applicationContext)
        typeface.setTypefaceForSubHeaderBold(bind.tvMyChallenges, application.applicationContext)

        typeface.setTypefaceForBodyBold(bind.clChallengeFeed.btnAll, application.applicationContext)
        typeface.setTypefaceForBodyBold(bind.clChallengeFeed.btnFriends, application.applicationContext)
        typeface.setTypefaceForBodyBold(bind.clChallengeFeed.btnMyPosts, application.applicationContext)
    }

    override fun bindHeaderAdapter(bind: FragmentRedesignMyFeedBinding, listOfActiveChallenges: List<CurrentActiveChallengesResponse>) {
        bind.rvChallengeHeader.isNestedScrollingEnabled = viewModel.viewState.isNestedScrollEnabled

        bind.rvChallengeHeader.layoutManager = PeekingLinearLayoutManager(application.applicationContext, RecyclerView.HORIZONTAL)
        bind.rvChallengeHeader.itemAnimator = DefaultItemAnimator()

        challengesHeaderAdapter = ChallengesHeaderAdapter(application.applicationContext, viewModel, listOfActiveChallenges)
        bind.rvChallengeHeader.adapter = challengesHeaderAdapter
    }
    override fun bindAdapter(bind: FragmentRedesignMyFeedBinding, newsFeedList: List<PostListResponse>) {
        bind.rvNewsFeedRedesign.isNestedScrollingEnabled = viewModel.viewState.isNestedScrollEnabled
        bind.rvNewsFeedRedesign.layoutManager = LinearLayoutManager(activity)

        newsFeedRedesignAdapter = NewsFeedRedesignAdapter(application.applicationContext, newsFeedList, viewModel)
        bind.rvNewsFeedRedesign.adapter = newsFeedRedesignAdapter
    }

    override fun clickListeners(bind: FragmentRedesignMyFeedBinding) {
        bind.clChallengeFeed.btnAll.setOnClickListener {
            if (allActiveNewsFeedList.isNotEmpty()) {
                viewModel.allClicked()
                bindAdapter(bind, allActiveNewsFeedList)
            }
        }
        bind.clChallengeFeed.btnFriends.setOnClickListener {
            viewModel.friendsClicked()
        }
        bind.clChallengeFeed.btnMyPosts.setOnClickListener {
            if (allActiveNewsFeedList.isNotEmpty()) {
                viewModel.myPostsClicked()
              //  bindAdapter(bind, currentUserNewsFeedList)
            }
        }
        bind.clFriendsEmptyState.btnAddFriendEmptyState.setOnClickListener {
            viewModel.onAddFriendsEmptyStateClicked()
        }

        bind.tbMyFeed.setOnMenuItemClickListener {
            it?.let { item ->
                if (item.itemId == R.id.navigation_refresh) {
                    viewModel.onRefreshTabClicked()
                } else if (item.itemId == R.id.navigation_add_progress) {
                    viewModel.onAddProgressTabClicked()
                }
                true
            } == true
        }
    }

    override fun updateView(bind: FragmentRedesignMyFeedBinding) {
        bind.tvMyChallenges.visibleOrGone = viewModel.viewState.myChallengesVisible
        bind.clEndOfFeed.tvEndOfFeed.visibleOrGone = viewModel.viewState.isEndOfNewsFeedVisible

        bind.rvNewsFeedRedesign.visibleOrGone = viewModel.viewState.recyclerNewsFeedVisible
        bind.clFriendsEmptyState.clAddFriend.visibleOrGone = viewModel.viewState.addFriendsEmptyStateVisible

        bind.clChallengeFeed.btnAll.setTextColor(Color.parseColor(viewModel.viewState.btnAllTextColor))
        bind.clChallengeFeed.btnFriends.setTextColor(Color.parseColor(viewModel.viewState.btnFriendsTextColor))
        bind.clChallengeFeed.btnMyPosts.setTextColor(Color.parseColor(viewModel.viewState.btnMyPostsTextColor))

        bind.clChallengeFeed.btnAll.background = ContextCompat.getDrawable(application.applicationContext, viewModel.viewState.btnAllBackgroundResId)
        bind.clChallengeFeed.btnFriends.background = ContextCompat.getDrawable(application.applicationContext, viewModel.viewState.btnFriendsBackgroundResId)
        bind.clChallengeFeed.btnMyPosts.background = ContextCompat.getDrawable(application.applicationContext, viewModel.viewState.btnMyPostsBackgroundResId)
    }
}
