package com.nicholasrutherford.chal.newsfeed

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.nicholasrutherford.chal.MainActivity
import com.nicholasrutherford.chal.challengesredesign.challengedetails.STARTER_INDEX
import com.nicholasrutherford.chal.data.firebase.ActiveChallenge
import com.nicholasrutherford.chal.data.firebase.FirebaseKeys
import com.nicholasrutherford.chal.data.firebase.Friends
import com.nicholasrutherford.chal.data.firebase.User
import com.nicholasrutherford.chal.firebase.ACTIVE_CHALLENGES
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
import com.nicholasrutherford.chal.room.entity.challengesposts.ChallengesPostsEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NewsFeedRedesignViewModel(private val mainActivity: MainActivity, appContext: Context, val activeChallengesPostsList: List<ChallengesPostsEntity>?) : ViewModel() {

    private val keysList: MutableList<FirebaseKeys> = ArrayList()
    private val usersList: MutableList<User> = ArrayList()

    private val ref = FirebaseDatabase.getInstance().getReference(USERS)
    var mAuth: FirebaseAuth? = null

    val viewState = NewsFeedRedesignViewStateImpl()
    private val readProfileDetailsFirebase = ReadAccountFirebase(appContext)

    private val _allFirebaseKeys = MutableStateFlow(listOf<FirebaseKeys>())
    val allFirebaseKeys: StateFlow<List<FirebaseKeys>> = _allFirebaseKeys

    private val _allUsers = MutableStateFlow(listOf<User>())
    val allUsers: StateFlow<List<User>> = _allUsers

    private val _allChallengesPosts = MutableStateFlow(listOf<ChallengesPostsEntity>())
    private val allChallengesPosts: StateFlow<List<ChallengesPostsEntity>> = _allChallengesPosts

    init {
        mAuth = FirebaseAuth.getInstance()
        initViewStateOnLoad()

        fetchKeys()
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

    fun fetchUsers(firebaseKeys: List<FirebaseKeys>) {
        viewModelScope.launch {
            firebaseKeys.forEach { firebaseKey ->
                ref.child(firebaseKey.key).addValueEventListener(object: ValueEventListener {
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
                                                        name = snapshot.child(CATEGORY_NAME)
                                                            .toString(),
                                                        description = snapshot.child(DESCRIPTION)
                                                            .toString(),
                                                        numberOfDaysOfChallenge = 11,
                                                        challengeExipreTime = snapshot.child(
                                                            TIME_CHANGE_EXPIRE
                                                        ).toString(),
                                                        currentDayOfChallange = 3,
                                                        categoryName = snapshot.child(CATEGORY_NAME)
                                                            .toString(),
                                                        activeChallengesPosts = emptyList()
                                                    )
                                                )
                                            )
                                            usersList.add(user)

                                            _allUsers.value = usersList
                                        }
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