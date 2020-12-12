package com.nicholasrutherford.chal.newsfeed

import android.content.Context
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nicholasrutherford.chal.MainActivity
import com.nicholasrutherford.chal.firebase.read.ReadAccountFirebase
import com.nicholasrutherford.chal.room.entity.firebasekey.FirebaseKeyEntity

class NewsFeedRedesignViewModel(
    private val mainActivity: MainActivity,
    private val appContext: Context,
    private val fragmentManager: FragmentManager,
    private val container: Int,
    private val bottomNavigationView: BottomNavigationView,
    private val firebaseKeys: List<FirebaseKeyEntity>
) : ViewModel() {

    val viewState = NewsFeedRedesignViewStateImpl()
    private val readProfileDetailsFirebase = ReadAccountFirebase(appContext)

    init {
        initViewStateOnLoad()
    }

    fun initViewStateOnLoad() {
        readProfileDetailsFirebase.getUsername()?.let { userName ->
            viewState.toolbarName = userName
        }
        readProfileDetailsFirebase.getUserProfilePicture()?.let { profilePicture ->
            viewState.toolbarImage = profilePicture
        }
    }

    inner class NewsFeedRedesignViewStateImpl: NewsFeedRedesignViewState {
        override var toolbarName: String = ""
        override var toolbarImage: String = ""
    }
}