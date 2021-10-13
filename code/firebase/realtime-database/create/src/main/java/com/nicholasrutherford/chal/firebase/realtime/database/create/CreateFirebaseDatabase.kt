package com.nicholasrutherford.chal.firebase.realtime.database.create

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import con.nicholasrutherford.chal.data.challenges.ActiveChallenge

interface CreateFirebaseDatabase {
    val uid: String?
    val databaseReferenceUsers: DatabaseReference
    val databaseReferenceActiveChallenges: DatabaseReference

    // call this function to add a active challenge via firebase
    fun createCurrentDayOfChallenge(allActiveChallengeIndex: Int, userChallengeIndex: String, currentDay: Int)
    fun createNewActiveChallenge(allActiveChallengeIndex: Int, userChallengeIndex: String, activeChallenge: ActiveChallenge)
    fun createChallengeBannerType(bannerType: Int)
}