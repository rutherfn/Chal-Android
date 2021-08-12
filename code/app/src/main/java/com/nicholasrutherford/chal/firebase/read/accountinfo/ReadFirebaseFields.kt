package com.nicholasrutherford.chal.firebase.read.accountinfo

import com.nicholasrutherford.chal.data.responses.activechallenges.ActiveChallengesListResponse
import com.nicholasrutherford.chal.data.responses.post.PostListResponse
import com.nicholasrutherford.chal.firebase.ChalFirebase
import kotlinx.coroutines.flow.MutableStateFlow

interface ReadFirebaseFields : ChalFirebase {
    fun getAccountInfo()
    fun getAllChallengesSizeFlow(_activeChallengesSize: MutableStateFlow<Int>)
    fun getAllActiveChallengesFlow(_activeChallengesList: MutableStateFlow<List<ActiveChallengesListResponse>>)
    fun getAllPosts(_postList: MutableStateFlow<List<PostListResponse>>)
}