package com.nicholasrutherford.chal.newsfeed

import android.content.Context
import androidx.lifecycle.ViewModel
import com.nicholasrutherford.chal.MainActivity
import com.nicholasrutherford.chal.firebase.read.ReadAccountFirebase
import com.nicholasrutherford.chal.room.entity.challengesposts.ChallengesPostsEntity

class NewsFeedRedesignViewModel(private val mainActivity: MainActivity,
                                private val appContext: Context,
                                val activeChallengesPostsList: List<ChallengesPostsEntity>?) : ViewModel() {

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