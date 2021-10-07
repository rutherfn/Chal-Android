package com.nicholasrutherford.chal.main.news.feed

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.nicholasrutherford.chal.data.post.PostListResponse
import com.nicholasrutherford.chal.firebase.realtime.database.create.CreateFirebaseDatabase
import com.nicholasrutherford.chal.firebase.realtime.database.fetch.FetchFirebaseDatabase
import com.nicholasrutherford.chal.shared.preference.fetch.FetchSharedPreference
import com.nicholasrutherford.chal.ui.base_vm.BaseViewModel
import con.nicholasrutherford.chal.data.challenges.ActiveChallengesListResponse
import con.nicholasrutherford.chal.data.challenges.ChallengeBanner
import con.nicholasrutherford.chal.data.challenges.banner.ChallengeBannerType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class NewsFeedViewModel @ViewModelInject constructor(
    private val createFirebaseDatabase: CreateFirebaseDatabase,
    private val fetchFirebaseDatabase: FetchFirebaseDatabase,
    private val fetchSharedPreference: FetchSharedPreference,
    private val application: Application
) : BaseViewModel() {

    private var username = application.getString(R.string.empty_string)
    private var isUserEnrolledInAChallenge = false

    private val _loggedInUsername = MutableStateFlow(application.getString(R.string.empty_string))
    val loggedInUsername: StateFlow<String> = _loggedInUsername

    private val _isUserEnrolledInChallenge = MutableStateFlow(false)
    private val isUserEnrolledInChallenge: StateFlow<Boolean> = _isUserEnrolledInChallenge

    private val _postList = MutableStateFlow(listOf<PostListResponse>())
    val postList: StateFlow<List<PostListResponse>> = _postList

    private val _allActiveChallengesList = MutableStateFlow(listOf<ActiveChallengesListResponse>())
    val allActiveChallengesList: StateFlow<List<ActiveChallengesListResponse>> = _allActiveChallengesList

    private val _challengeBannerType = MutableStateFlow(0)
    private val challengeBannerType: StateFlow<Int> = _challengeBannerType

    var alertTitle = application.getString(R.string.empty_string)
    var alertMessage = application.getString(R.string.empty_string)

    private var bannerType = ChallengeBannerType.NONE

    val viewState = NewsFeedViewStateImpl()

    init {
        viewModelScope.launch {
            loggedInUsername.collect { loggedInUsername ->
                username = loggedInUsername
                setViewStateAsUpdated()
            }
        }

        viewModelScope.launch {
            isUserEnrolledInChallenge.collect { isEnrolled ->
                isUserEnrolledInAChallenge = isEnrolled
            }
        }

        viewModelScope.launch {
            challengeBannerType.collect { bannerTypeValue ->
                setCurrentBannerType(bannerTypeValue)
            }
        }

        fetchLoggedInUsername()

        fetchUserEnrolledInAChallenge()
        fetchAllPosts()
        fetchAllActiveChallenges()
    }

    private fun fetchLoggedInUsername() = fetchFirebaseDatabase.fetchLoggedInUsername(_loggedInUsername)

    private fun fetchUserEnrolledInAChallenge() = fetchFirebaseDatabase.fetchIsUserEnrolledInAChallenge(_isUserEnrolledInChallenge)

    private fun fetchAllPosts() = fetchFirebaseDatabase.fetchAllPosts(_postList)

    private fun fetchAllActiveChallenges() = fetchFirebaseDatabase.fetchAllChallenges(_allActiveChallengesList)

    private fun fetchChallengeBannerType() = fetchFirebaseDatabase.fetchChallengeBannerType(_challengeBannerType)

    private fun setCurrentBannerType(bannerTypeValue: Int) {
        bannerType = when (bannerTypeValue) {
            ChallengeBannerType.JOINED_CHALLENGE.value -> {
                ChallengeBannerType.JOINED_CHALLENGE
            }
            ChallengeBannerType.JUST_POSTED.value -> {
                ChallengeBannerType.JUST_POSTED
            }
            ChallengeBannerType.LAST_DAY.value -> {
                ChallengeBannerType.LAST_DAY
            }
            else -> {
                ChallengeBannerType.NONE
            }
        }

        updateBannerState()
    }

    private fun updateBannerState() {
        val bannerTitle = fetchSharedPreference.fetchChallengeBannerTypeTitleSharedPreference()
        val bannerDesc = fetchSharedPreference.fetchChallengeBannerTypeDescription()

        val challengeBanner = if (bannerType == ChallengeBannerType.NONE) {
            ChallengeBanner(
                title = null,
                description = null,
                isVisible = false,
                isCloseable = false
            )
        } else {
            ChallengeBanner(
                title = bannerTitle,
                description = bannerDesc,
                isVisible = !bannerTitle.isNullOrEmpty() && !bannerDesc.isNullOrEmpty(),
                isCloseable = true
            )
        }

        viewState.bannerTitle = challengeBanner.title
        viewState.bannerDescription = challengeBanner.description
        viewState.bannerVisible = challengeBanner.isVisible
        viewState.bannerIsCloseable = challengeBanner.isCloseable
    }

    inner class NewsFeedViewStateImpl : NewsFeedViewState {
        override var addFriendsEmptyStateVisible: Boolean = false
        override var btnAllTextColor: String = application.getString(R.string.hex_black)
        override var btnAllBackgroundResId: Int = R.drawable.corner_button_white
        override var btnFriendsTextColor: String = application.getString(R.string.hex_white)
        override var btnFriendsBackgroundResId: Int = R.drawable.corner_button
        override var btnMyPostsTextColor: String = application.getString(R.string.hex_white)
        override var btnMyPostsBackgroundResId: Int = R.drawable.corner_button
        override var isNestedScrollEnabled: Boolean = false
        override var myChallengesVisible: Boolean = false
        override var recyclerNewsFeedVisible: Boolean = true
        override var isEndOfNewsFeedVisible: Boolean = false

        override var bannerVisible: Boolean = false
        override var bannerTitle: String? = null
        override var bannerDescription: String? = null
        override var bannerIsCloseable: Boolean = false
    }
}