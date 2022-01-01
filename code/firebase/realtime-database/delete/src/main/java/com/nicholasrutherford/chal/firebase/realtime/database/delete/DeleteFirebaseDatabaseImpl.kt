package com.nicholasrutherford.chal.firebase.realtime.database.delete

import com.google.firebase.database.FirebaseDatabase
import com.nicholasrutherford.chal.helper.constants.ALL_ACTIVE_CHALLENGES
import com.nicholasrutherford.chal.helper.constants.USERS
import con.nicholasrutherford.chal.data.challenges.FirebaseStatus
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class DeleteFirebaseDatabaseImpl @Inject constructor() : DeleteFirebaseDatabase {

    override val database = FirebaseDatabase.getInstance()

    override fun deleteAChallengeInAllActive(index: String, _status: MutableStateFlow<FirebaseStatus>) {
        database.getReference("$USERS$ALL_ACTIVE_CHALLENGES$index").removeValue().addOnSuccessListener {
            _status.value = FirebaseStatus.SUCCESSFUL
        }.addOnFailureListener {
            _status.value = FirebaseStatus.FAILURE
        }.addOnCanceledListener {
            _status.value = FirebaseStatus.ON_CANCELED
        }
    }

    override fun deleteBulkChallengesInAllActive(
        listIndexes: List<String>,
        _status: MutableStateFlow<FirebaseStatus>
    ) {
        var status = FirebaseStatus.NONE
        listIndexes.forEach { index ->
            database.getReference("$USERS$ALL_ACTIVE_CHALLENGES$index").removeValue().addOnSuccessListener {
                status = FirebaseStatus.SUCCESSFUL
            }.addOnFailureListener {
                status = FirebaseStatus.FAILURE
            }.addOnCanceledListener {
                status = FirebaseStatus.ON_CANCELED
            }
        }

        _status.value = status
    }
}