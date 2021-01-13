package com.nicholasrutherford.chal.newsfeed

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicholasrutherford.chal.ChalRoom
import com.nicholasrutherford.chal.MainActivity
import com.nicholasrutherford.chal.firebase.read.ReadAccountFirebase
import com.nicholasrutherford.chal.room.entity.challengesposts.ChallengesPostsEntity
import com.nicholasrutherford.chal.room.entity.user.UserEntity
import kotlinx.coroutines.launch

class NewsFeedRedesignViewModel(private val mainActivity: MainActivity, appContext: Context, val activeChallengesPostsList: List<ChallengesPostsEntity>?) : ViewModel() {

    val viewState = NewsFeedRedesignViewStateImpl()
    private val readProfileDetailsFirebase = ReadAccountFirebase(appContext)

    init {
        initViewStateOnLoad()
    }

    private fun initViewStateOnLoad() {
        readProfileDetailsFirebase.getUsername()?.let { userName ->
            viewState.toolbarName = userName
        }
        readProfileDetailsFirebase.getUserProfilePicture()?.let { profilePicture ->
            viewState.toolbarImage = profilePicture
        }
    }

    private fun test() {
        val chalRoom = ChalRoom(mainActivity.application)
        viewModelScope.launch {
            val user = chalRoom.userRepository.getUser(viewState.toolbarName)

            println(user.username)
        }
    }

    inner class NewsFeedRedesignViewStateImpl: NewsFeedRedesignViewState {
        override var toolbarName: String = ""
        override var toolbarImage: String = ""
    }
}