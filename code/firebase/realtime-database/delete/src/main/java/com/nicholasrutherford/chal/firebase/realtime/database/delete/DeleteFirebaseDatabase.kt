package com.nicholasrutherford.chal.firebase.realtime.database.delete

import com.google.firebase.database.FirebaseDatabase
import con.nicholasrutherford.chal.data.challenges.FirebaseStatus
import kotlinx.coroutines.flow.MutableStateFlow

interface DeleteFirebaseDatabase {

    val database: FirebaseDatabase

    // this simply removes a challenge by
    // the all-active challenge node in firebase
    // and it gets removed, simply by its index
    fun deleteAChallengeInAllActive(index: String, _status: MutableStateFlow<FirebaseStatus>)

    // this simply removes a challenge by
    // the all active challenge node in firebase
    // and it gets removed, by the list of indexes.
    // it will loop throught he size of the index
    // and remove it one index at a time
    fun deleteBulkChallengesInAllActive(listIndexes: List<String>, _status: MutableStateFlow<FirebaseStatus>)
}