package com.nicholasrutherford.chal.newsfeed

import android.content.Context
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.nicholasrutherford.chal.activitys.MainActivity
import com.nicholasrutherford.chal.data.realdata.Account
import com.nicholasrutherford.chal.data.realdata.ActiveChallenges
import com.nicholasrutherford.chal.firebase.USERS
import com.nicholasrutherford.chal.firebase.read.ReadAccountFirebase

class NewsFeedRedesignViewModel (private val mainActivity: MainActivity, private val appContext: Context,
                                 private val fragmentManager: FragmentManager, private val container: Int,
                                 private val bottomNavigationView: BottomNavigationView
) : ViewModel() {

    val uid = FirebaseAuth.getInstance().uid ?: ""
    val ref = FirebaseDatabase.getInstance().getReference(USERS)

    val viewState = NewsFeedRedesignViewStateImpl()
    private val readProfileDetailsFirebase = ReadAccountFirebase(appContext)

    init {
        initViewStateOnLoad()
        fetchKeys()
    }

    fun initViewStateOnLoad() {
        readProfileDetailsFirebase.getUsername()?.let { userName ->
            viewState.toolbarName = userName
        }
        readProfileDetailsFirebase.getUserProfilePicture()?.let { profilePicture ->
            viewState.toolbarImage = profilePicture
        }
    }

    fun fetchKeys() {
        ref.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                println("error")
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (activeChallenge in snapshot.children) {
                        println(activeChallenge.key)
                    }
                } else {
                    println("does not exist")
                }
            }

        })
    }

    inner class NewsFeedRedesignViewStateImpl: NewsFeedRedesignViewState {
        override var toolbarName: String = ""
        override var toolbarImage: String = ""
    }
}