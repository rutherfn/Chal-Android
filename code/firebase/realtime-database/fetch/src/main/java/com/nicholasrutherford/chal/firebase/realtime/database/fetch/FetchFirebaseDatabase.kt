package com.nicholasrutherford.chal.firebase.realtime.database.fetch

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.nicholasrutherford.chal.data.post.PostListResponse
import con.nicholasrutherford.chal.data.challenges.ActiveChallengesListResponse
import kotlinx.coroutines.flow.MutableStateFlow

interface FetchFirebaseDatabase {
    val database: FirebaseDatabase
    val databaseUserReference: DatabaseReference
    val databaseAllActiveChallengesReference: DatabaseReference
    val databasePostsReference: DatabaseReference


    fun fetchAllUsersDatabaseReference(uid: String): DatabaseReference

    fun fetchLoggedInUsername(_loggedInUsername: MutableStateFlow<String>)

    fun fetchAllChallenges(_allActiveChallengesList: MutableStateFlow<List<ActiveChallengesListResponse>>)
    fun fetchAllChallengesSize(_allActiveChallengesSize: MutableStateFlow<Int>)
    fun fetchAllUserActiveChallenges(_activeChallengesList: MutableStateFlow<List<ActiveChallengesListResponse>>)
    fun fetchIsUserEnrolledInAChallenge(_isUserEnrolledInChallenge: MutableStateFlow<Boolean>)

    fun fetchAllPosts(_postList: MutableStateFlow<List<PostListResponse>>)

    fun fetchChallengeBannerType(_challengeBannerType: MutableStateFlow<Int>)
}