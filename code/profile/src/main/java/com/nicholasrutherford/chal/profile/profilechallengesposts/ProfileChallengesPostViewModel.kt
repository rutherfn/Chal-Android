package com.nicholasrutherford.chal.profile.profilechallengesposts

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import com.nicholasrutherford.chal.data.post.PostListResponse
import com.nicholasrutherford.chal.firebase.realtime.database.fetch.FetchFirebaseDatabase
import com.nicholasrutherford.chal.main.navigation.Navigator
import com.nicholasrutherford.chal.ui.base_vm.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ProfileChallengesPostViewModel @ViewModelInject constructor(
    private val application: Application,
    private val navigator: Navigator,
    private val fetchFirebaseDatabase: FetchFirebaseDatabase
) : BaseViewModel() {

    var index = ""

    private val _postList = MutableStateFlow(listOf<PostListResponse>())
    val postList: StateFlow<List<PostListResponse>> = _postList

    fun onNavigateBack() = navigator.navigateBack()

    fun fetchAllUserPosts() = fetchFirebaseDatabase.fetchAllUsersPostsOfChallenge(_postList, index)

}