package com.nicholasrutherford.chal.room.helpers

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.nicholasrutherford.chal.ChalRoom
import com.nicholasrutherford.chal.firebase.USERS
import com.nicholasrutherford.chal.room.entity.firebasekey.FirebaseKeyEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FirebaseKeysViewModelHelper(val viewModelScope: CoroutineScope) {

    private val ref = FirebaseDatabase.getInstance().getReference(USERS)

    var mAuth: FirebaseAuth? = null

    val _allKeys = MutableStateFlow((listOf<FirebaseKeyEntity>()))
    val allKeys: StateFlow<List<FirebaseKeyEntity>> = _allKeys

    init {
        mAuth = FirebaseAuth.getInstance()
    }

    fun fetchLatestKeys(chalRoom: ChalRoom) {
        viewModelScope.launch { chalRoom.firebaseKeyDao.deleteAllFirebaseKeys() }

        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                println("error")
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (activeChallenge in snapshot.children) {
                        viewModelScope.launch {
                            addFirebaseKey(
                                FirebaseKeyEntity(
                                    0,
                                    activeChallenge.key ?: ""
                                ), chalRoom
                            )
                        }
                    }
                    viewModelScope.launch {
                        _allKeys.value = chalRoom.firebaseKeyRepository.readAllKeysRegular()
                    }
                } else {
                    println("we have no users in our account error")
                }

            }

        })

    }

    fun addFirebaseKey(firebaseKeyEntity: FirebaseKeyEntity, chalRoom: ChalRoom) {
        viewModelScope.launch() {
            chalRoom.firebaseKeyRepository.addFirebaseKey(firebaseKeyEntity)
        }
    }

    fun deleteAllFirebaseKeys(chalRoom: ChalRoom) {
        viewModelScope.launch {
            chalRoom.firebaseKeyRepository.deleteAllFirebaseKeys()
        }
    }

}