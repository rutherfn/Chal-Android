package com.nicholasrutherford.chal.splash

import android.content.Context
import androidx.lifecycle.ViewModel
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.navigationimpl.splash.SplashNavigationImpl
import android.os.Handler
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.nicholasrutherford.chal.ChalRoom
import com.nicholasrutherford.chal.challengesredesign.challengedetails.STARTER_INDEX
import com.nicholasrutherford.chal.firebase.*
import com.nicholasrutherford.chal.room.entity.activechallenges.ActiveChallengesEntity
import com.nicholasrutherford.chal.room.entity.firebasekey.FirebaseKeyEntity
import com.nicholasrutherford.chal.room.entity.user.UserEntity
import com.nicholasrutherford.chal.room.helpers.FirebaseKeysViewModelHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SplashViewModel(context: Context, private val activity: SplashActivity)  : ViewModel() {

    private val navigation = SplashNavigationImpl()
    var viewState = SplashViewModelImpl()

    private val _allUsers = MutableStateFlow(listOf<UserEntity>())
    private val allUsers: StateFlow<List<UserEntity>> = _allUsers

    private val ref = FirebaseDatabase.getInstance().getReference(USERS)

    init {
        val firebaseKeysHelper = FirebaseKeysViewModelHelper(viewModelScope = viewModelScope)
        val chalRoom = ChalRoom(activity.application)
        firebaseKeysHelper.fetchLatestKeys(chalRoom)

        viewModelScope.launch { chalRoom.userRepository.deleteAllUsers() }

        viewModelScope.launch {
            firebaseKeysHelper.allKeys.collect { keys ->
                fetchLatestUsers(keys)
            }
        }

        checkIfUserIsSignedIn(firebaseKeysHelper)
    }

    private fun checkIfUserIsSignedIn(firebaseKeysViewModelHelper: FirebaseKeysViewModelHelper) {
        val handler = Handler()
        handler.postDelayed({
            val user = firebaseKeysViewModelHelper.mAuth?.currentUser ?: null

            if (user == null) {
                navigation.login(activity)
            } else {
                navigation.home(activity)
            }
        }, 5000)
    }

    private fun fetchLatestUsers(allFirebaseKeys: List<FirebaseKeyEntity>) {
            allFirebaseKeys.forEach { firebaseKey ->
                ref.child(firebaseKey.key).addValueEventListener(object: ValueEventListener{
                    override fun onCancelled(error: DatabaseError) {
                        println("error")
                    }

                    override fun onDataChange(userSnapshot: DataSnapshot) {
                        if (userSnapshot.exists()) {

                            ref.child("${firebaseKey.key}/${ACTIVE_CHALLENGES}$STARTER_INDEX").addValueEventListener(object: ValueEventListener{
                                override fun onCancelled(error: DatabaseError) {
                                    println("error")
                                }

                                override fun onDataChange(activeChallengeSnapshot: DataSnapshot) {
                                    if (activeChallengeSnapshot.exists()) {

                                        viewModelScope.launch {
                                            val chalRoom = ChalRoom(activity.application)
                                            chalRoom.userRepository.addAUser(
                                                UserEntity(
                                                    id = userSnapshot.child(ID).value.toString()
                                                        .toInt(),
                                                    username = userSnapshot.child(USERNAME).value.toString(),
                                                    email = userSnapshot.child(EMAIL).value.toString(),
                                                    profileImageUrl = userSnapshot.child(
                                                        PROFILE_IMAGE
                                                    ).value.toString(),
                                                    password = userSnapshot.child(PASSWORD).value.toString(),
                                                    firstName = userSnapshot.child(FIRST_NAME).value.toString(),
                                                    lastName = userSnapshot.child(LAST_NAME).value.toString(),
                                                    bio = userSnapshot.child(BIO).value.toString(),
                                                    age = userSnapshot.child(AGE).value.toString()
                                                        .toInt(),
                                                    currentFriends = null,
                                                    activeChallengeEntities = listOf(
                                                        ActiveChallengesEntity(
                                                            id = 0,
                                                            nameOfChallenge = activeChallengeSnapshot.child(
                                                                CATEGORY_NAME
                                                            ).toString(),
                                                            description = activeChallengeSnapshot.child(
                                                                DESCRIPTION
                                                            ).toString(),
                                                            numberOfDaysOfChallenge = 0,
                                                            challengeExpireTime = "dad",
                                                            currentDayOfChallenge = 0,
                                                            categoryName = activeChallengeSnapshot.child(
                                                                CATEGORY_NAME
                                                            ).toString(),
                                                            activeChallengesPosts = emptyList()

                                                        )
                                                    )
                                                )
                                            )
                                        }
                                    }
                                }
                            })
                        }

                    }

                })
            }

    }

    inner class SplashViewModelImpl : SplashViewState {
        override var splashImageRes: Int = R.mipmap.chalappicon
    }

}