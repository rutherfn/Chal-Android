package com.nicholasrutherford.chal.newsfeed

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.nicholasrutherford.chal.ChallengeCalenderDay
import com.nicholasrutherford.chal.main.MainActivity
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.data.responses.CurrentActiveChallengesResponse
import com.nicholasrutherford.chal.data.responses.NewsFeedResponse
import com.nicholasrutherford.chal.data.responses.post.PostListResponse
import com.nicholasrutherford.chal.firebase.ACTIVE_CHALLENGES
import com.nicholasrutherford.chal.firebase.USERS
import com.nicholasrutherford.chal.firebase.read.ReadAccountFirebase
import com.nicholasrutherford.chal.firebase.read.accountinfo.ReadFirebaseFieldsImpl
import com.nicholasrutherford.chal.firebase.write.activenewchallenge.WriteNewActiveChallengeImpl
import com.nicholasrutherford.chal.navigationimpl.newsfeed.NewsFeedNavigationImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class NewsFeedViewModel @Inject constructor(private val application: Application, mainActivity: MainActivity) : ViewModel() {

    private val uid = FirebaseAuth.getInstance().uid ?: ""
    private val ref = FirebaseDatabase.getInstance().getReference(USERS)
    private var mAuth: FirebaseAuth? = null

    val viewState = NewsFeedRedesignViewStateImpl()

    private val readProfileDetailsFirebase = ReadAccountFirebase(application.applicationContext)

    private val _newsFeed = MutableStateFlow(listOf<NewsFeedResponse>())
    val newsFeed: StateFlow<List<NewsFeedResponse>> = _newsFeed

    private val _postList = MutableStateFlow(listOf<PostListResponse>())
    val postList: StateFlow<List<PostListResponse>> = _postList

    private val _isUserEnrolledInChallenge = MutableStateFlow(false)
    private val isUserEnrolledInChallenge: StateFlow<Boolean> = _isUserEnrolledInChallenge

    private val _currentUserActiveChallenges = MutableStateFlow(listOf<CurrentActiveChallengesResponse>())
    val currentUserActiveChallenges: StateFlow<List<CurrentActiveChallengesResponse>> = _currentUserActiveChallenges

    val _isViewStateUpdated = MutableStateFlow(false)
    val isViewStateUpdated: StateFlow<Boolean> = _isViewStateUpdated

    private var userEnrolledInChallenge = false

    private val readFirebaseFields = ReadFirebaseFieldsImpl()
    private val writeNewActiveChallengeImpl = WriteNewActiveChallengeImpl()

    private val challengeCurrentDay = ChallengeCalenderDay()

    val navigation = NewsFeedNavigationImpl(application, mainActivity)

    init {
       // startProgress()
        mAuth = FirebaseAuth.getInstance()

        fetchNewsFeedList()
        println("get here212121")
        updateDayOfAllActiveChallenges()

        // make a call after were done with all this data
        // that updates a challenge if its not in this current day
        // in order to keep with the program of 7 days of challenges
    }

    fun fetchNewsFeedList() {
        checkEnrolledInAChallenge()
        fetchAllPosts()

        viewModelScope.launch {
            isUserEnrolledInChallenge.collect { isEnrolled ->
                userEnrolledInChallenge = isEnrolled
            }
        }
    }

    private fun checkEnrolledInAChallenge() {
        ref.child("$uid$ACTIVE_CHALLENGES")
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {}

                override fun onDataChange(snapshot: DataSnapshot) {
                    _isUserEnrolledInChallenge.value = snapshot.exists()
                }
            })
    }

    private fun updateDayOfAllActiveChallenges() {
        var index = 0
        val activeChallengesResponseList = arrayListOf<CurrentActiveChallengesResponse>()
        ref.child("$uid$ACTIVE_CHALLENGES")
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {}

                override fun onDataChange(snapshot: DataSnapshot) {
                    for (activeChallenges in snapshot.children) {
                        activeChallenges.getValue(CurrentActiveChallengesResponse::class.java).let {
                            it?.let { activeChallenge ->
                                activeChallengesResponseList.add(activeChallenge)
                            }
                        }
                    }
                    activeChallengesResponseList.forEachIndexed { index, currentActiveChallengesResponse ->
                        if (currentActiveChallengesResponse.currentDay != challengeCurrentDay.dayInChallenge()) {
                            writeNewActiveChallengeImpl.writeCurrentDay(index.toString(), currentActiveChallengesResponse.dayOnChallenge + 1)
                        }
                    }
                }
            })
    }

    private fun fetchAllPosts() {
        readFirebaseFields.getAllPosts(_postList)
    }

    fun updateMyChallengesVisible(currentActiveChallengesList: List<CurrentActiveChallengesResponse>) {
        viewState.myChallengesVisible = currentActiveChallengesList.size > 1
    }

    fun hideAcProgress() = navigation.hideAcProgress()

    fun allClicked() {
        viewState.recyclerNewsFeedVisible = true
        viewState.addFriendsEmptyStateVisible = false
        viewState.isEndOfNewsFeedVisible = true

        viewState.btnAllTextColor = application.getString(R.string.hex_black)
        viewState.btnFriendsTextColor = application.getString(R.string.hex_white)
        viewState.btnMyPostsTextColor = application.getString(R.string.hex_white)

        viewState.btnAllBackgroundResId = R.drawable.corner_button_white
        viewState.btnFriendsBackgroundResId = R.drawable.corner_button
        viewState.btnMyPostsBackgroundResId = R.drawable.corner_button

        _isViewStateUpdated.value = true
    }

    fun friendsClicked() {
        viewState.recyclerNewsFeedVisible = false
        viewState.addFriendsEmptyStateVisible = true
        viewState.isEndOfNewsFeedVisible = false

        viewState.btnAllTextColor = application.getString(R.string.hex_white)
        viewState.btnFriendsTextColor = application.getString(R.string.hex_black)
        viewState.btnMyPostsTextColor = application.getString(R.string.hex_white)

        viewState.btnAllBackgroundResId = R.drawable.corner_button
        viewState.btnFriendsBackgroundResId = R.drawable.corner_button_white
        viewState.btnMyPostsBackgroundResId = R.drawable.corner_button

        _isViewStateUpdated.value = true
    }

    fun myPostsClicked() {
        viewState.recyclerNewsFeedVisible = true
        viewState.addFriendsEmptyStateVisible = false
        viewState.isEndOfNewsFeedVisible = true

        viewState.btnAllTextColor = application.getString(R.string.hex_white)
        viewState.btnFriendsTextColor = application.getString(R.string.hex_white)
        viewState.btnMyPostsTextColor = application.getString(R.string.hex_black)

        viewState.btnAllBackgroundResId = R.drawable.corner_button
        viewState.btnFriendsBackgroundResId = R.drawable.corner_button
        viewState.btnMyPostsBackgroundResId = R.drawable.corner_button_white

        _isViewStateUpdated.value = true
    }

    fun onAddFriendsEmptyStateClicked() {
        navigation.showPeopleList()
    }

    fun onRefreshTabClicked() {
        fetchNewsFeedList()
    }

    fun onAddProgressTabClicked() {
        if (userEnrolledInChallenge) {
            navigation.showProgress()
        } else {
            val title = application.applicationContext.getString(R.string.not_enrolled_in_challenge)
            val message = application.applicationContext.getString(R.string.not_enrolled_in_challenge_message)
            navigation.showAlert(title, message)
        }
    }

    fun generateUserPostList(allPostsList: List<PostListResponse>): List<PostListResponse> {
        val userPostList = arrayListOf(PostListResponse())
        val username = readProfileDetailsFirebase.getUsername() ?: ""

        userPostList.clear()

        allPostsList.forEach { allPosts ->
            if (allPosts.posts?.username == username) {
                userPostList.add(allPosts)
            }
        }

        return userPostList
    }

    fun startProgress() = navigation.showAcProgress()

    fun hideProgress() = navigation.hideAcProgress()

    inner class NewsFeedRedesignViewStateImpl : NewsFeedRedesignViewState {
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
    }
}
