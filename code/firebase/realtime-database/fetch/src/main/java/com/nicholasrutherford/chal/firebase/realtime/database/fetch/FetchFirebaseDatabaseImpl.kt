package com.nicholasrutherford.chal.firebase.realtime.database.fetch

import android.app.Application
import com.google.firebase.database.*
import com.nicholasrutherford.chal.data.post.PostListResponse
import com.nicholasrutherford.chal.data.post.PostResponse
import kotlinx.coroutines.flow.MutableStateFlow
import com.nicholasrutherford.chal.firebase.auth.ChalFirebaseAuth
import com.nicholasrutherford.chal.helper.constants.*
import con.nicholasrutherford.chal.data.challenges.ActiveChallengesListResponse
import con.nicholasrutherford.chal.data.challenges.ActiveChallengesResponse
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class FetchFirebaseDatabaseImpl @Inject constructor(
    private val firebaseAuth: ChalFirebaseAuth,
    private val application: Application
): FetchFirebaseDatabase {

    override val database = FirebaseDatabase.getInstance()

    override val databaseUserReference = FirebaseDatabase.getInstance().getReference(USERS)
    override val databaseAllActiveChallengesReference = database.getReference(ALL_ACTIVE_CHALLENGES)
    override val databasePostsReference = database.getReference(POSTS)

    private fun String.isNumber(): Boolean { // TODO this belongs in a helper module
        return if (this.isNullOrEmpty()) false else this.all { Character.isDigit(it) }
    }

    override fun fetchAllUsersDatabaseReference(uid: String): DatabaseReference {
        return database.getReference(userDatabaseReference(uid))
    }

    override fun fetchLoggedInUsername(_loggedInUsername: MutableStateFlow<String>) {
        firebaseAuth.uid?.let { firebaseUid ->
            databaseUserReference.child(firebaseUid)
                .addValueEventListener(object : ValueEventListener {

                    override fun onDataChange(snapshot: DataSnapshot) {
                        if (snapshot.exists()) {
                            println("user namne test " + snapshot.child(USERNAME).value.toString())
                            _loggedInUsername.value = snapshot.child(USERNAME).value.toString()
                        } else {
                            println("snapshot does not exist ")
                            _loggedInUsername.value = application.getString(R.string.empty_string)
                        }
                    }

                    override fun onCancelled(error: DatabaseError) = Unit
                })
        }
    }

    override fun fetchLoggedInUserProfilePicture(_userProfilePicture: MutableStateFlow<String>) {
        firebaseAuth.uid?.let { firebaseUid ->
            databaseUserReference.child(firebaseUid)
                .addValueEventListener(object : ValueEventListener {

                    override fun onDataChange(snapshot: DataSnapshot) {
                        if (snapshot.exists()) {
                            _userProfilePicture.value = snapshot.child(PROFILE_IMAGE).value.toString()
                        } else {
                            _userProfilePicture.value = application.getString(R.string.empty_string)
                        }
                    }

                    override fun onCancelled(error: DatabaseError) = Unit
                })
        }
    }

    override fun fetchAllChallenges(_allActiveChallengesList: MutableStateFlow<List<ActiveChallengesListResponse>>) {
        databaseAllActiveChallengesReference.addValueEventListener(object : ValueEventListener {

            override fun onCancelled(error: DatabaseError) = Unit

            override fun onDataChange(snapshot: DataSnapshot) {
                val allChallengesList = arrayListOf<ActiveChallengesListResponse>()

                for (allChallenges in snapshot.children) {
                    allChallenges.getValue(ActiveChallengesResponse::class.java).let { activeChallengesResponse ->
                        activeChallengesResponse?.let { data ->
                            allChallengesList.add(ActiveChallengesListResponse(data))
                        }
                    }
                }

                _allActiveChallengesList.value = allChallengesList
            }
        })
    }

    override fun fetchAllChallengesSize(_allActiveChallengesSize: MutableStateFlow<Int>) {
        databaseAllActiveChallengesReference.addValueEventListener(object : ValueEventListener {

            override fun onCancelled(error: DatabaseError) = Unit

            override fun onDataChange(snapshot: DataSnapshot) {
                val allChallengesList = arrayListOf<ActiveChallengesListResponse>()

                for (allChallenges in snapshot.children) {
                    allChallenges.getValue(ActiveChallengesResponse::class.java).let { activeChallengesResponse ->
                        activeChallengesResponse?.let { data ->
                            allChallengesList.add(ActiveChallengesListResponse(data))
                        }
                    }
                }

                _allActiveChallengesSize.value = allChallengesList.size
            }
        })
    }

    override fun fetchAllUserActiveChallenges(_activeChallengesList: MutableStateFlow<List<ActiveChallengesListResponse>>) {
        firebaseAuth.uid?.let { firebaseUid ->
            val activeChallengesPathString = "$firebaseUid$ACTIVE_CHALLENGES"

            databaseAllActiveChallengesReference.child(activeChallengesPathString).addValueEventListener(object: ValueEventListener {

                override fun onCancelled(error: DatabaseError) = Unit

                override fun onDataChange(snapshot: DataSnapshot) {
                    val allChallengesList = arrayListOf<ActiveChallengesListResponse>()

                    for (challenges in snapshot.children) {
                        challenges.getValue(ActiveChallengesResponse::class.java).let { activeChallengesResponse ->
                            activeChallengesResponse?.let { data ->
                                allChallengesList.add(ActiveChallengesListResponse(data))
                            }
                        }
                    }
                    _activeChallengesList.value = allChallengesList
                }
            })
        }
    }

    override fun fetchIsUserEnrolledInAChallenge(_isUserEnrolledInChallenge: MutableStateFlow<Boolean>) {
        firebaseAuth.uid?.let { firebaseUid ->
            val activeChallengesPathString = "$firebaseUid$ACTIVE_CHALLENGES"

            databaseAllActiveChallengesReference.child(activeChallengesPathString).addValueEventListener(object: ValueEventListener {

                override fun onCancelled(error: DatabaseError) = Unit

                override fun onDataChange(snapshot: DataSnapshot) {
                    _isUserEnrolledInChallenge.value = snapshot.exists()
                }
            })
        }
    }

    override fun fetchAllPosts(_postList: MutableStateFlow<List<PostListResponse>>) {
        databasePostsReference.addValueEventListener(object: ValueEventListener {

            override fun onCancelled(error: DatabaseError) = Unit

            override fun onDataChange(snapshot: DataSnapshot) {
                val postList = arrayListOf<PostListResponse>()

                for (posts in snapshot.children) {
                    posts.getValue(PostResponse::class.java).let { postResponse ->
                        postResponse?.let { data ->
                            postList.add(PostListResponse(data))
                        }
                    }
                }
                _postList.value = postList
            }
        })
    }

    override fun fetchChallengeBannerType(_challengeBannerType: MutableStateFlow<Int>) {
        val unknownChallengeBannerType = 111 // TODO update this in the future
        firebaseAuth.uid?.let { firebaseUid ->
            databaseUserReference.child(firebaseUid).addValueEventListener(object : ValueEventListener {

                override fun onCancelled(error: DatabaseError) = Unit

                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        val bannerTypeSnapshot = snapshot.child(CHALLENGE_BANNER_TYPE).value.toString()
                        if (bannerTypeSnapshot.isNumber()) {
                            _challengeBannerType.value = bannerTypeSnapshot.toInt()
                        } else {
                            _challengeBannerType.value = unknownChallengeBannerType
                        }
                    } else {
                        _challengeBannerType.value = unknownChallengeBannerType
                    }
                }
            })
        }
    }


}