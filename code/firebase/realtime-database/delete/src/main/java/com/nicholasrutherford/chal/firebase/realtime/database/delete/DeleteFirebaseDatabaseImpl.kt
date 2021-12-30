package com.nicholasrutherford.chal.firebase.realtime.database.delete

import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class DeleteFirebaseDatabaseImpl @Inject constructor(

) : DeleteFirebaseDatabase {

    override fun deleteAChallengeInAllActive(index: String, status: MutableStateFlow<Boolean>) {

    }
}