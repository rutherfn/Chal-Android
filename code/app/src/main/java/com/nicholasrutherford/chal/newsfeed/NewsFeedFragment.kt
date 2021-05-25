package com.nicholasrutherford.chal.newsfeed

import android.app.Application
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.auth.FirebaseAuth
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.data.responses.CurrentActiveChallengesResponse
import com.nicholasrutherford.chal.data.responses.NewsFeedResponse
import com.nicholasrutherford.chal.databinding.FragmentRedesignMyFeedBinding
import com.nicholasrutherford.chal.ext.fragments.newsfeed.NewsFeedRedesignFragmentExtension
import com.nicholasrutherford.chal.helpers.PeekingLinearLayoutManager
import com.nicholasrutherford.chal.helpers.Typeface
import com.nicholasrutherford.chal.helpers.visibleOrGone
import com.nicholasrutherford.chal.navigationimpl.newsfeed.NewsFeedNavigationImpl
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsFeedFragment @Inject constructor(private val application: Application) :
    DaggerFragment(),
    NewsFeedRedesignFragmentExtension {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    val newsFeedViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)
            .get(NewsFeedViewModel::class.java)
    }

    private var mAuth: FirebaseAuth? = null

    private var challengesHeaderAdapter: ChallengesHeaderAdapter? = null
    private var newsFeedRedesignAdapter: NewsFeedRedesignAdapter? = null

    private val typeface = Typeface()

    private var currentUserNewsFeedList: List<NewsFeedResponse> = ArrayList()
    private var allActiveNewsFeedList: List<NewsFeedResponse> = ArrayList()

    init {
        mAuth = FirebaseAuth.getInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val bind = FragmentRedesignMyFeedBinding.inflate(layoutInflater)

        collectUserNewsFeedListResults()
        collectActiveChallengesResults(bind)
        collectNewsFeedResults(bind)

        updateTypefaces(bind)
        clickListeners(bind, NewsFeedNavigationImpl(application, this))
        updateView(bind)

        bind.tbMyFeed.inflateMenu(R.menu.navigation_news_feed)

        return bind.root
    }

    override fun collectUserNewsFeedListResults() {
        lifecycleScope.launch {
            newsFeedViewModel.userNewsFeed.collect { newsFeedList ->
                currentUserNewsFeedList = newsFeedList
            }
        }
    }

    override fun collectActiveChallengesResults(bind: FragmentRedesignMyFeedBinding) {
        lifecycleScope.launch {
                newsFeedViewModel.currentUserActiveChallenges.collect { activeChallengesList ->
                    newsFeedViewModel.updateMyChallengesVisible(activeChallengesList)

                    updateView(bind)
                    bindHeaderAdapter(bind, activeChallengesList)
                }
        }
    }

    override fun collectNewsFeedResults(bind: FragmentRedesignMyFeedBinding) {
             lifecycleScope.launch {
                 newsFeedViewModel.newsFeed.collect { newsFeedList ->
                     allActiveNewsFeedList = newsFeedList
                     bind.clEndOfFeed.tvEndOfFeed.visibleOrGone = newsFeedViewModel.viewState.isEndOfNewsFeedVisible
                     bindAdapter(bind, newsFeedList)
                 }
        }
    }

    override fun bindHeaderAdapter(bind: FragmentRedesignMyFeedBinding, listOfActiveChallenges: List<CurrentActiveChallengesResponse>) {
        bind.rvChallengeHeader.isNestedScrollingEnabled = false

        bind.rvChallengeHeader.layoutManager = PeekingLinearLayoutManager(application.applicationContext, RecyclerView.HORIZONTAL)
        bind.rvChallengeHeader.itemAnimator = DefaultItemAnimator()

        challengesHeaderAdapter = ChallengesHeaderAdapter(application.applicationContext, newsFeedViewModel, listOfActiveChallenges)
        bind.rvChallengeHeader.adapter = challengesHeaderAdapter
    }
    override fun bindAdapter(bind: FragmentRedesignMyFeedBinding, newsFeedList: List<NewsFeedResponse>) {
        bind.rvNewsFeedRedesign.isNestedScrollingEnabled = false
        bind.rvNewsFeedRedesign.layoutManager = LinearLayoutManager(activity)

        newsFeedRedesignAdapter = NewsFeedRedesignAdapter(application.applicationContext, newsFeedList,  newsFeedViewModel)
        bind.rvNewsFeedRedesign.adapter = newsFeedRedesignAdapter
    }

    override fun updateTypefaces(bind: FragmentRedesignMyFeedBinding) {
      //  typeface.setTypefaceForHeaderBold(bind.tbMyFeed.tvTitle, application.applicationContext)
        typeface.setTypefaceForSubHeaderBold(bind.clEndOfFeed.tvEndOfFeed, application.applicationContext)
        typeface.setTypefaceForSubHeaderBold(bind.tvMyChallenges, application.applicationContext)

        typeface.setTypefaceForBodyBold(bind.clChallengeFeed.btnAll, application.applicationContext)
        typeface.setTypefaceForBodyBold(bind.clChallengeFeed.btnFriends, application.applicationContext)
        typeface.setTypefaceForBodyBold(bind.clChallengeFeed.btnMyPosts, application.applicationContext)
    }

    override fun containerId(): Int {
        return R.id.container
    }

    override fun clickListeners(bind: FragmentRedesignMyFeedBinding, newsFeedNavigationImpl: NewsFeedNavigationImpl) {
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
            newsFeedNavigationImpl.showPeopleList()
        }
        // bind.tbMyFeed.ivUploadChallenges.setOnClickListener { viewModel?.onUploadProgressClicked() }
    }

    private fun updateButtonsAllClicked(bind: FragmentRedesignMyFeedBinding) {
        bind.clChallengeFeed.btnAll.setTextColor(Color.parseColor("#000000"))
        bind.clChallengeFeed.btnFriends.setTextColor(Color.parseColor("#FFFFFF"))
        bind.clChallengeFeed.btnMyPosts.setTextColor(Color.parseColor("#FFFFFF"))

        bind.clChallengeFeed.btnAll.background = ContextCompat.getDrawable(application.applicationContext, R.drawable.corner_button_white)
        bind.clChallengeFeed.btnFriends.background = ContextCompat.getDrawable(application.applicationContext, R.drawable.corner_button)
        bind.clChallengeFeed.btnMyPosts.background = ContextCompat.getDrawable(application.applicationContext, R.drawable.corner_button)
    }

    private fun updateButtonsFriendsClicked(bind: FragmentRedesignMyFeedBinding) {
        bind.clChallengeFeed.btnAll.setTextColor(Color.parseColor("#FFFFFF"))
        bind.clChallengeFeed.btnFriends.setTextColor(Color.parseColor("#000000"))
        bind.clChallengeFeed.btnMyPosts.setTextColor(Color.parseColor("#FFFFFF"))

        bind.clChallengeFeed.btnAll.background = ContextCompat.getDrawable(application.applicationContext, R.drawable.corner_button)
        bind.clChallengeFeed.btnFriends.background = ContextCompat.getDrawable(application.applicationContext, R.drawable.corner_button_white)
        bind.clChallengeFeed.btnMyPosts.background = ContextCompat.getDrawable(application.applicationContext, R.drawable.corner_button)
    }

    private fun updateButtonsMyPostsClicked(bind: FragmentRedesignMyFeedBinding) {
        bind.clChallengeFeed.btnAll.setTextColor(Color.parseColor("#FFFFFF"))
        bind.clChallengeFeed.btnFriends.setTextColor(Color.parseColor("#FFFFFF"))
        bind.clChallengeFeed.btnMyPosts.setTextColor(Color.parseColor("#000000"))

        bind.clChallengeFeed.btnAll.background = ContextCompat.getDrawable(application.applicationContext, R.drawable.corner_button)
        bind.clChallengeFeed.btnFriends.background = ContextCompat.getDrawable(application.applicationContext, R.drawable.corner_button)
        bind.clChallengeFeed.btnMyPosts.background = ContextCompat.getDrawable(application.applicationContext, R.drawable.corner_button_white)
    }

    override fun updateView(bind: FragmentRedesignMyFeedBinding) {
        bind.tvMyChallenges.visibleOrGone = newsFeedViewModel.viewState.myChallengesVisible
       // bind.tbMyFeed.tvTitle.text = newsFeedViewModel.viewState.toolbarName

        val options = RequestOptions()
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.placeholder)

        bind.clEndOfFeed.tvEndOfFeed.visibleOrGone = newsFeedViewModel.viewState.isEndOfNewsFeedVisible

        // Glide.with(this).load(newsFeedViewModel.viewState.toolbarImage).apply(options)
        //         .into(bind.tbMyFeed.cvProfile)
        // Glide.with(application.applicationContext).load(R.drawable.ic_add).into(bind.tbMyFeed.ivUploadChallenges)
    }
}
