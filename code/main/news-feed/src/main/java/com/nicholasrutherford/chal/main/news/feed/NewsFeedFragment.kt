package com.nicholasrutherford.chal.main.news.feed

import android.app.Application
import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nicholasrutherford.chal.data.post.PostListResponse
import com.nicholasrutherford.chal.helper.fragment.PeekingLinearLayoutManager
import com.nicholasrutherford.chal.helper.fragment.visibleOrGone
import com.nicholasrutherford.chal.main.news.feed.databinding.FragmentNewsFeedBinding
import com.nicholasrutherford.chal.ui.base_fragment.BaseFragment
import com.nicholasrutherford.chal.ui.typefaces.Typefaces
import con.nicholasrutherford.chal.data.challenges.ActiveChallengesListResponse
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class NewsFeedFragment @Inject constructor() : BaseFragment<FragmentNewsFeedBinding>(
    FragmentNewsFeedBinding::inflate) {

    @Inject
    lateinit var typefaces: Typefaces

    @Inject
    lateinit var application: Application

    private val viewModel: NewsFeedViewModel by viewModels()

    private var currentUserNewsFeedList: List<PostListResponse> = emptyList()
    private var allActiveNewsFeedList: List<PostListResponse> = ArrayList()

    private var newsFeedChallengeHeaderAdapter: NewsFeedChallengeHeaderAdapter? = null
    private var newsFeedListAdapter: NewsFeedListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tbMyFeed.inflateMenu(R.menu.navigation_news_feed)

        lifecycleScope.launch {
            collectViewStateResult(viewModel.viewStateUpdated, viewModel._viewStateUpdated)
        }
        lifecycleScope.launch {
            collectShouldShowProgressResult(
                viewModel.shouldShowProgress,
                viewModel._shouldShowProgress
            )
        }
        lifecycleScope.launch {
            collectShouldDismissProgressResult(
                viewModel.shouldDismissProgress,
                viewModel._shouldDismissProgress
            )
        }
        collectAlertAsUpdated()

        lifecycleScope.launch {
            viewModel.postList.collect { newsFeedList ->
                if (newsFeedList.isNotEmpty()) {
                    allActiveNewsFeedList = newsFeedList
                    currentUserNewsFeedList = viewModel.generateUserAdapterPostList(allActiveNewsFeedList)

                    bindListAdapter(newsFeedList)
                    viewModel.waitBeforeWeDismissProgress()
                } else {
                    viewModel.setShouldShowDismissProgressAsUpdated()
                }
            }
        }

        lifecycleScope.launch {
            viewModel.allUserActiveChallengesList.collect { challengesList ->
                if (challengesList.isNotEmpty()) {
                    viewModel.updateMyChallengesVisible(challengesList)

                    viewModel.setViewStateAsUpdated()

                    bindHeaderAdapter(challengesList)
                }
            }
        }
    }

    private fun bindHeaderAdapter(listOfActiveChallenges: List<ActiveChallengesListResponse>) {
        if (binding != null) {
            binding.rvChallengeHeader.isNestedScrollingEnabled =
                viewModel.viewState.isNestedScrollEnabled
            binding.rvChallengeHeader.layoutManager =
                PeekingLinearLayoutManager(application.applicationContext, RecyclerView.HORIZONTAL)

            newsFeedChallengeHeaderAdapter = NewsFeedChallengeHeaderAdapter(
                application,
                viewModel,
                typefaces,
                listOfActiveChallenges
            )
            binding.rvNewsFeedRedesign.adapter = newsFeedChallengeHeaderAdapter
        }
    }

    private fun bindListAdapter(newsFeedList: List<PostListResponse>) {
//        binding.rvNewsFeedRedesign.isNestedScrollingEnabled = viewModel.viewState.isNestedScrollEnabled
//        binding.rvNewsFeedRedesign.layoutManager = LinearLayoutManager(activity)
//
//        newsFeedListAdapter = NewsFeedListAdapter(application, newsFeedList, typefaces, viewModel)
//        binding.rvNewsFeedRedesign.adapter = newsFeedListAdapter
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

    override fun collectAlertAsUpdated() {
        lifecycleScope.launch {
            viewModel.shouldShowAlert.collect { isShouldShowAlert ->
                if (isShouldShowAlert) {
                    showOkAlert(title = viewModel.alertTitle, message = viewModel.alertMessage)
                }
                viewModel.setShouldShowAlertAsNotUpdated()
            }
        }
    }

    override fun updateTypefaces() {
//        typefaces.setTextViewSubHeaderBoldTypeface(binding.clEndOfFeed.tvEndOfFeed)
//        typefaces.setTextViewSubHeaderBoldTypeface(binding.tvMyChallenges)
//
//        typefaces.setTextViewBodyBoldTypeface(binding.clChallengeFeed.btnAll)
//        typefaces.setTextViewBodyBoldTypeface(binding.clChallengeFeed.btnFriends)
//        typefaces.setTextViewBodyBoldTypeface(binding.clChallengeFeed.btnMyPosts)
//
//        typefaces.setTextViewHeaderBoldTypeface(binding.clNewsFeedBanner.tvTitle)
//        typefaces.setTextViewHeaderBoldTypeface(binding.clNewsFeedBanner.tvDesc)
    }

    override fun onListener() {
//        binding.clChallengeFeed.btnAll.setOnClickListener {
//            viewModel.onAllClicked()
//            bindListAdapter(allActiveNewsFeedList)
//        }
//        binding.clChallengeFeed.btnFriends.setOnClickListener {
//            viewModel.onFriendsClicked()
//        }
//        binding.clChallengeFeed.btnMyPosts.setOnClickListener {
//            viewModel.onMyPostsClicked()
//            bindListAdapter(currentUserNewsFeedList)
//        }
//        binding.clFriendsEmptyState.btnAddFriendEmptyState.setOnClickListener {
//            viewModel.onAddFriendsEmptyStateClicked()
//        }
//
//        binding.tbMyFeed.setOnMenuItemClickListener {
//            it?.let { item ->
//                if (item.itemId == R.id.navigation_refresh) {
//                    viewModel.onRefreshTabClicked()
//                } else if (item.itemId == R.id.navigation_add_progress) {
//                    viewModel.onAddProgressTabClicked()
//                }
//                true
//            } == true
//        }
//        binding.clNewsFeedBanner.clBannerType.setOnClickListener {
//            if (viewModel.viewState.bannerIsCloseable) {
//                viewModel.onBannerDismissedClicked()
//            }
//        }
    }

    override fun updateView() {
//        binding.tvMyChallenges.visibleOrGone = viewModel.viewState.myChallengesVisible
//        binding.clEndOfFeed.tvEndOfFeed.visibleOrGone = viewModel.viewState.isEndOfNewsFeedVisible
//
//        binding.rvNewsFeedRedesign.visibleOrGone = viewModel.viewState.recyclerNewsFeedVisible
//        binding.clFriendsEmptyState.clAddFriend.visibleOrGone = viewModel.viewState.addFriendsEmptyStateVisible
//
//        binding.clChallengeFeed.btnAll.setTextColor(Color.parseColor(viewModel.viewState.btnAllTextColor))
//        binding.clChallengeFeed.btnFriends.setTextColor(Color.parseColor(viewModel.viewState.btnFriendsTextColor))
//        binding.clChallengeFeed.btnMyPosts.setTextColor(Color.parseColor(viewModel.viewState.btnMyPostsTextColor))
//
//        binding.clChallengeFeed.btnAll.background = ContextCompat.getDrawable(application.applicationContext, viewModel.viewState.btnAllBackgroundResId)
//        binding.clChallengeFeed.btnFriends.background = ContextCompat.getDrawable(application.applicationContext, viewModel.viewState.btnFriendsBackgroundResId)
//        binding.clChallengeFeed.btnMyPosts.background = ContextCompat.getDrawable(application.applicationContext, viewModel.viewState.btnMyPostsBackgroundResId)
//
//        binding.clNewsFeedBanner.clBannerType.visibleOrGone = viewModel.viewState.bannerVisible
//        binding.clNewsFeedBanner.tvTitle.text = viewModel.viewState.bannerTitle
//        binding.clNewsFeedBanner.tvDesc.text = viewModel.viewState.bannerDescription
//
//        binding.clEndOfFeed.tvEndOfFeed.visibleOrGone =
//            viewModel.viewState.isEndOfNewsFeedVisible
    }

}