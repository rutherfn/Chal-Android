package com.nicholasrutherford.chal.newsfeed

import android.content.Context
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.challengesredesign.challengedetails.STARTER_INDEX
import com.nicholasrutherford.chal.data.firebase.ActiveChallenge
import com.nicholasrutherford.chal.data.firebase.FirebaseKeys
import com.nicholasrutherford.chal.data.firebase.Friends
import com.nicholasrutherford.chal.data.firebase.User
import com.nicholasrutherford.chal.data.responses.CurrentActiveChallengesResponse
import com.nicholasrutherford.chal.data.responses.NewsFeedResponse
import com.nicholasrutherford.chal.firebase.ACTIVE_CHALLENGES
import com.nicholasrutherford.chal.firebase.ACTIVE_CHALLENGES_POSTS
import com.nicholasrutherford.chal.firebase.AGE
import com.nicholasrutherford.chal.firebase.BIO
import com.nicholasrutherford.chal.firebase.CATEGORY_NAME
import com.nicholasrutherford.chal.firebase.DESCRIPTION
import com.nicholasrutherford.chal.firebase.EMAIL
import com.nicholasrutherford.chal.firebase.FIRST_NAME
import com.nicholasrutherford.chal.firebase.LAST_NAME
import com.nicholasrutherford.chal.firebase.PASSWORD
import com.nicholasrutherford.chal.firebase.PROFILE_IMAGE
import com.nicholasrutherford.chal.firebase.TIME_CHANGE_EXPIRE
import com.nicholasrutherford.chal.firebase.USERNAME
import com.nicholasrutherford.chal.firebase.USERS
import com.nicholasrutherford.chal.firebase.read.ReadAccountFirebase
import com.nicholasrutherford.chal.navigationimpl.newsfeed.NewsFeedNavigationImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class NewsFeedRedesignViewModel(private val fragmentActivity: FragmentActivity, private val appContext: Context) : ViewModel() {

    private val keysList: MutableList<FirebaseKeys> = ArrayList()

    private val usersList: MutableList<User> = ArrayList()

    private val uid = FirebaseAuth.getInstance().uid ?: ""
    private val ref = FirebaseDatabase.getInstance().getReference(USERS)
    private var mAuth: FirebaseAuth? = null

    val viewState = NewsFeedRedesignViewStateImpl()
    val newsFeedNavigationImpl = NewsFeedNavigationImpl()

    private val readProfileDetailsFirebase = ReadAccountFirebase(appContext)

    private val _allFirebaseKeys = MutableStateFlow(listOf<FirebaseKeys>())
    val allFirebaseKeys: StateFlow<List<FirebaseKeys>> = _allFirebaseKeys

    private val _allUsers = MutableStateFlow(listOf<User>())
    val allUsers: StateFlow<List<User>> = _allUsers

    private val _newsFeed = MutableStateFlow(listOf<NewsFeedResponse>())
    val newsFeed: StateFlow<List<NewsFeedResponse>> = _newsFeed

    private val _isUserEnrolledInChallenge = MutableStateFlow(false)
    private val isUserEnrolledInChallenge: StateFlow<Boolean> = _isUserEnrolledInChallenge

    private var userEnrolledInChallenge = false

    init {
        mAuth = FirebaseAuth.getInstance()
        initViewStateOnLoad()

        fetchKeys()
        checkIfUserIsEnrolledInAChallenge()

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

    private fun initViewStateOnLoad() {
        readProfileDetailsFirebase.getUsername()?.let { userName ->
            viewState.toolbarName = userName
        }
        readProfileDetailsFirebase.getUserProfilePicture()?.let { profilePicture ->
            viewState.toolbarImage = profilePicture
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
                                keysList.add(FirebaseKeys(firebaseUserKey))
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

    private fun checkIfUserIsEnrolledInAChallenge() {
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
            newsFeedNavigationImpl.showProgress(fragmentActivity, appContext)
        } else {
            val title = fragmentActivity.getString(R.string.not_enrolled_in_challenge)
            val message = fragmentActivity.getString(R.string.not_enrolled_in_challenge_message)
            newsFeedNavigationImpl.showAlert(title, message, fragmentActivity)
        }
    }

    fun fetchActiveChallengesPosts(firebaseKeys: List<FirebaseKeys>) {
        val newsFeedList = arrayListOf<NewsFeedResponse>()
        val activeChallengesResponseList = arrayListOf<CurrentActiveChallengesResponse>()
        firebaseKeys.forEachIndexed { indexs, firebaseKeys ->
            ref.child(firebaseKeys.key).addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        ref.child("${firebaseKeys.key}$ACTIVE_CHALLENGES").addValueEventListener(object : ValueEventListener {
                            override fun onDataChange(snapshot: DataSnapshot) {
                                for (activeChallenges in snapshot.children) {
                                    activeChallenges.getValue(CurrentActiveChallengesResponse::class.java).let {
                                        it?.let { activeChallenge ->
                                            activeChallengesResponseList.add(activeChallenge)
                                        }
                                    }
                                }
                                activeChallengesResponseList.forEachIndexed { index, currentActiveChallengesResponse ->
                                    ref.child("${firebaseKeys.key}${ACTIVE_CHALLENGES}$index$ACTIVE_CHALLENGES_POSTS").addValueEventListener(object : ValueEventListener {
                                        override fun onDataChange(snapshot: DataSnapshot) {
                                            for (challengesPosts in snapshot.children) {
                                                challengesPosts.getValue(NewsFeedResponse::class.java).let {
                                                    it?.let { challengePost -> newsFeedList.add(challengePost) }
                                                }
                                            }

                                            println(newsFeedList.size)
                                            _newsFeed.value = newsFeedList
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
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    println("error")
                }
            })
        }
    }

    fun fetchUsers(firebaseKeys: List<FirebaseKeys>) {
        viewModelScope.launch {
            firebaseKeys.forEach { firebaseKey ->
                ref.child(firebaseKey.key).addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if (snapshot.exists()) {
                            val username = snapshot.child(USERNAME).value.toString()
                            val email = snapshot.child(EMAIL).value.toString()
                            val profileImageUrl = snapshot.child(PROFILE_IMAGE).value.toString()
                            val password = snapshot.child(PASSWORD).value.toString()
                            val firstName = snapshot.child(FIRST_NAME).value.toString()
                            val lastName = snapshot.child(LAST_NAME).value.toString()
                            val bio = snapshot.child(BIO).value.toString()
                            val age = snapshot.child(AGE).value.toString()

                            ref.child("${firebaseKey.key}${ACTIVE_CHALLENGES}$STARTER_INDEX").addValueEventListener(object : ValueEventListener {
                                override fun onDataChange(snapshot: DataSnapshot) {
                                    if (snapshot.exists()) {
                                        for (users in snapshot.children) {
                                            val user = User(
                                                id = 1,
                                                username = username,
                                                email = email,
                                                profileImageUrl = profileImageUrl,
                                                password = password,
                                                firstName = firstName,
                                                lastName = lastName,
                                                bio = bio,
                                                age = 11,
                                                currentFriends = listOf( // placeholder since right now, user cannot add a friend
                                                    Friends(
                                                        name = "",
                                                        username = "",
                                                        email = ""
                                                    )
                                                ),
                                                activeChallenges = listOf(
                                                    ActiveChallenge(
                                                        name = snapshot.child(CATEGORY_NAME).value
                                                            .toString(),
                                                        description = snapshot.child(DESCRIPTION).value
                                                            .toString(),
                                                        numberOfDaysOfChallenge = 11,
                                                        challengeExipreTime = snapshot.child(
                                                            TIME_CHANGE_EXPIRE
                                                        ).value.toString(),
                                                        currentDayOfChallange = 3,
                                                        categoryName = snapshot.child(CATEGORY_NAME)
                                                            .value.toString(),
                                                        activeChallengesPosts = emptyList()
                                                    )
                                                )
                                            )

                                            usersList.add(user)
                                        }
                                        _allUsers.value = usersList
                                    }
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
            }
        }
    }

    inner class NewsFeedRedesignViewStateImpl : NewsFeedRedesignViewState {
        override var toolbarName: String = ""
        override var toolbarImage: String = ""
    }
}
