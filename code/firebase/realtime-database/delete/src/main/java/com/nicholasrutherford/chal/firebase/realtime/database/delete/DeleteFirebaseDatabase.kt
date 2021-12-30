package com.nicholasrutherford.chal.firebase.realtime.database.delete

import kotlinx.coroutines.flow.MutableStateFlow

interface DeleteFirebaseDatabase {

    // this simply removes a challenge by
    // the all-active challenge node in firebase
    // and it gets removed, simply by its index
    fun deleteAChallengeInAllActive(index: String, status: MutableStateFlow<Boolean>)
}