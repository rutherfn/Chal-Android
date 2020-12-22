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
import com.nicholasrutherford.chal.firebase.USERNAME
import com.nicholasrutherford.chal.firebase.USERS
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

                    override fun onDataChange(snapshot: DataSnapshot) {
                        if (snapshot.exists()) {
                            val ageSnapshot = snapshot.child(USERNAME).value.toString()
                        }
                    }

                })
            }

    }

    inner class SplashViewModelImpl : SplashViewState {
        override var splashImageRes: Int = R.mipmap.chalappicon
    }

}