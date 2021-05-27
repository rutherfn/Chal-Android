package com.nicholasrutherford.chal.newsfeed

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.nicholasrutherford.chal.MainActivity
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.data.firebase.FirebaseKeys
import com.nicholasrutherford.chal.data.responses.CurrentActiveChallengesResponse
import com.nicholasrutherford.chal.data.responses.NewsFeedResponse
import com.nicholasrutherford.chal.firebase.ACTIVE_CHALLENGES
import com.nicholasrutherford.chal.firebase.ACTIVE_CHALLENGES_POSTS
import com.nicholasrutherford.chal.firebase.USERS
import com.nicholasrutherford.chal.firebase.read.ReadAccountFirebase
import com.nicholasrutherford.chal.firebase.write.activechallenge.WriteActiveChallengeImpl
import com.nicholasrutherford.chal.navigationimpl.newsfeed.NewsFeedNavigationImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class NewsFeedViewModel @Inject constructor(private val application: Application, mainActivity: MainActivity) : ViewModel() {

    private val keysList: MutableList<FirebaseKeys> = ArrayList()

    private val uid = FirebaseAuth.getInstance().uid ?: ""
    private val ref = FirebaseDatabase.getInstance().getReference(USERS)
    private var mAuth: FirebaseAuth? = null

    val writeActiveChallengesFirebase = WriteActiveChallengeImpl()

    val viewState = NewsFeedRedesignViewStateImpl()
    //val newsFeedNavigationImpl = NewsFeedNavigationImpl(application)

    private val readProfileDetailsFirebase = ReadAccountFirebase(application.applicationContext)

    private val _allFirebaseKeys = MutableStateFlow(listOf<FirebaseKeys>())
    val allFirebaseKeys: StateFlow<List<FirebaseKeys>> = _allFirebaseKeys

    private val _newsFeed = MutableStateFlow(listOf<NewsFeedResponse>())
    val newsFeed: StateFlow<List<NewsFeedResponse>> = _newsFeed

    private val _userNewsFeed = MutableStateFlow(listOf<NewsFeedResponse>())
    val userNewsFeed: StateFlow<List<NewsFeedResponse>> = _userNewsFeed

    private val _isUserEnrolledInChallenge = MutableStateFlow(false)
    private val isUserEnrolledInChallenge: StateFlow<Boolean> = _isUserEnrolledInChallenge

    private val _currentUserActiveChallenges = MutableStateFlow(listOf<CurrentActiveChallengesResponse>())
    val currentUserActiveChallenges: StateFlow<List<CurrentActiveChallengesResponse>> = _currentUserActiveChallenges

    val _isViewStateUpdated = MutableStateFlow(false)
    val isViewStateUpdated: StateFlow<Boolean> = _isViewStateUpdated

    private var userEnrolledInChallenge = false

    val navigation = NewsFeedNavigationImpl(application, mainActivity)

    init {
        mAuth = FirebaseAuth.getInstance()

        fetchNewsFeedList()

        // make a call after were done with all this data
        // that updates a challenge if its not in this current day
        // in order to keep with the program of 7 days of challenges
    }

    fun fetchNewsFeedList() {
        navigation.showAcProgress()

        updateDayOfAllActiveChallenges()

        fetchKeys()
        checkEnrolledInAChallenge()
        fetchUserActiveChallenges()

        viewModelScope.launch {
            isUserEnrolledInChallenge.collect { isEnrolled ->
                userEnrolledInChallenge = isEnrolled
            }
        }

        viewModelScope.launch {
            allFirebaseKeys.collect { firebaseKeys ->
                fetchActiveChallengesPosts(firebaseKeys)
            }
        }
    }

    private fun fetchKeys() {
        viewModelScope.launch {
            ref.addValueEventListener(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    println("error")
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        for (keys in snapshot.children) {
                            keys?.key?.let { firebaseUserKey ->
                                // if (firebaseUserKey != uid) { // fetch all firebase keys that are not the current user
                                keysList.add(FirebaseKeys(firebaseUserKey))
                                //  }
                            }
                        }
                        _allFirebaseKeys.value = keysList
                    } else {
                        println("we have no users in our account error")
                    }
                }
            })
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

    fun onUploadProgressClicked() {
        if (userEnrolledInChallenge) {
         //   newsFeedNavigationImpl.showProgress()
        } else {
            val title = application.applicationContext.getString(R.string.not_enrolled_in_challenge)
            val message = application.applicationContext.getString(R.string.not_enrolled_in_challenge_message)
           // newsFeedNavigationImpl.showAlert(title, message)
        }
    }

    //fun onAddFriendsClicked() = newsFeedNavigationImpl.showPeopleList()

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
                    // activeChallengesResponseList.forEachIndexed { index, currentActiveChallengesResponse ->
                    //     if (currentActiveChallengesResponse.currentDay == dayInChallenge()) {
                    //         writeActiveChallengesFirebase.writeUserDayOnChallenge(index.toString(), currentActiveChallengesResponse.dayOnChallenge + 1)
                    //     }
                    // }
                }
            })
    }

    private fun fetchUserActiveChallenges() {
        val userNewsFeedList = arrayListOf<NewsFeedResponse>()
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
                    _currentUserActiveChallenges.value = activeChallengesResponseList

                    activeChallengesResponseList.forEachIndexed { index, _ ->
                        ref.child("$uid$ACTIVE_CHALLENGES$index$ACTIVE_CHALLENGES_POSTS").addValueEventListener(object : ValueEventListener {
                            override fun onDataChange(snapshot: DataSnapshot) {
                                for (challengesPosts in snapshot.children) {
                                    challengesPosts.getValue(NewsFeedResponse::class.java).let {
                                        it?.let { challengePost -> userNewsFeedList.add(challengePost) }
                                    }
                                }

                                _userNewsFeed.value = userNewsFeedList
                            }

                            override fun onCancelled(error: DatabaseError) {
                                println("Error")
                            }
                        })
                    }
                }
            })
    }

    private fun fetchActiveChallengesPosts(firebaseKeys: List<FirebaseKeys>) {
        val newsFeedList = arrayListOf<NewsFeedResponse>()
        val activeChallengesResponseList = arrayListOf<CurrentActiveChallengesResponse>()
        firebaseKeys.forEachIndexed { _, firebase ->
            ref.child(firebase.key).addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        ref.child("${firebase.key}$ACTIVE_CHALLENGES").addValueEventListener(object : ValueEventListener {
                            override fun onDataChange(snapshot: DataSnapshot) {
                                for (activeChallenges in snapshot.children) {
                                    activeChallenges.getValue(CurrentActiveChallengesResponse::class.java).let {
                                        it?.let { activeChallenge ->
                                            activeChallengesResponseList.add(activeChallenge)
                                        }
                                    }
                                }
                                activeChallengesResponseList.forEachIndexed { index, _ ->
                                    ref.child("${firebase.key}${ACTIVE_CHALLENGES}$index$ACTIVE_CHALLENGES_POSTS").addValueEventListener(object : ValueEventListener {
                                        override fun onDataChange(snapshot: DataSnapshot) {
                                            for (challengesPosts in snapshot.children) {
                                                challengesPosts.getValue(NewsFeedResponse::class.java).let {
                                                    it?.let { challengePost -> newsFeedList.add(challengePost) }
                                                }
                                            }

                                            viewState.isEndOfNewsFeedVisible = true
                                            _newsFeed.value = newsFeedList

                                            navigation.hideAcProgress()
                                        }

                                        override fun onCancelled(error: DatabaseError) {
                                            println("error")
                                        }
                                    })
                                }
                            }

                            override fun onCancelled(error: DatabaseError) {
                                println("error")
                            }
                        })
                    } else {
                        viewState.isEndOfNewsFeedVisible = false
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    println("error")
                }
            })
        }
    }

    fun updateMyChallengesVisible(currentActiveChallengesList: List<CurrentActiveChallengesResponse>) {
        viewState.myChallengesVisible = currentActiveChallengesList.size > 1
    }

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

    fun dayInChallenge(): Int {
        val calendar = Calendar.getInstance()

        return when (calendar[Calendar.DAY_OF_WEEK]) {
            Calendar.MONDAY -> { 0 }
            Calendar.TUESDAY -> { 1 }
            Calendar.WEDNESDAY -> { 2 }
            Calendar.THURSDAY -> { 3 }
            Calendar.FRIDAY -> { 4 }
            Calendar.SATURDAY -> { 5 }
            else -> { 6 }
        }
    }

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
