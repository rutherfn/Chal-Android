package com.nicholasrutherford.chal.peoplelist

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.nicholasrutherford.chal.data.firebase.FirebaseKeys
import com.nicholasrutherford.chal.data.responses.PeopleListResponse
import com.nicholasrutherford.chal.firebase.PROFILE_INFO
import com.nicholasrutherford.chal.firebase.USERS
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class PeopleListViewModel @Inject constructor(private val application: Application)
    : ViewModel() {

    private val uid = FirebaseAuth.getInstance().uid ?: ""
    private val ref = FirebaseDatabase.getInstance().getReference(USERS)

    private val keysList: MutableList<FirebaseKeys> = ArrayList()

    private val _allFirebaseKeys = MutableStateFlow(listOf<FirebaseKeys>())
    val allFirebaseKeys: StateFlow<List<FirebaseKeys>> = _allFirebaseKeys

    private val _allPeopleList = MutableStateFlow(mutableListOf<PeopleListResponse>())
    val peopleList: StateFlow<MutableList<PeopleListResponse>> = _allPeopleList

    val viewState = PeopleListViewStateImpl()

    init {
        fetchKeys()

        viewModelScope.launch {
            allFirebaseKeys.collect { firebaseKeys ->
                fetchAllUsers(firebaseKeys)
            }
        }
    }

    private fun fetchKeys() {
        viewModelScope.launch {
            ref.addValueEventListener(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    println("error")
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        for (keys in snapshot.children) {
                            keys?.key?.let { firebaseUserKey ->
                                 if (firebaseUserKey != uid) { // fetch all firebase keys that are not the current user
                                keysList.add(FirebaseKeys(firebaseUserKey))
                                  }
                            }
                        }
                        _allFirebaseKeys.value = keysList
                    } else {
                        println("we have no users in our account error")
                    }
                }
            })
        }
    }

    private fun fetchAllUsers(firebaseKeys: List<FirebaseKeys>) {
        val profileListResponse = arrayListOf<PeopleListResponse>()

        firebaseKeys.forEachIndexed { index, firebase ->
            ref.child("${firebase.key}$PROFILE_INFO")
                .addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {

                        snapshot.getValue(PeopleListResponse::class.java).let { peopleList ->
                            peopleList?.let { people ->
                                if (firebaseKeys.size != index - 1) {
                                    profileListResponse.add(people)
                                }
                            }
                        }

                        _allPeopleList.value = profileListResponse
                    }

                    override fun onCancelled(error: DatabaseError) {
                        println("error")
                    }
                })
        }
    }

    inner class PeopleListViewStateImpl : PeopleListViewState {
        override var profileImageUrl: String = ""
        override var profileUsername: String = ""
    }
}
