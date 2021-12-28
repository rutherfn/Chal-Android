package com.nicholasrutherford.chal.firebase.realtime.database.fetch

import android.app.Application
import com.google.firebase.database.*
import com.nicholasrutherford.chal.data.account.info.ProfileInfo
import com.nicholasrutherford.chal.data.post.PostListResponse
import com.nicholasrutherford.chal.data.post.PostResponse
import kotlinx.coroutines.flow.MutableStateFlow
import com.nicholasrutherford.chal.firebase.auth.ChalFirebaseAuth
import com.nicholasrutherford.chal.helper.constants.*
import con.nicholasrutherford.chal.data.challenges.ActiveChallengesListResponse
import con.nicholasrutherford.chal.data.challenges.ActiveChallengesResponse
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

    override fun fetchProfileInfo(_profileInfo: MutableStateFlow<ProfileInfo>, isUser: Boolean) {
        if (isUser) {
            firebaseAuth.uid?.let { firebaseUid ->
                databaseUserReference.child(firebaseUid).addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if (snapshot.exists()) {
                            val age = snapshot.child(AGE).value.toString()
                            val description = snapshot.child(BIO).value.toString()
                            val username = snapshot.child(USERNAME).value.toString()
                            val profileImage = snapshot.child(PROFILE_IMAGE).value.toString()

                            _profileInfo.value = ProfileInfo(
                                age = age.toInt(),
                                description = description,
                                username = username,
                                profileImage = profileImage
                            )
                        } else {
                            _profileInfo.value = ProfileInfo(
                                age = application.getString(R.string.zero).toInt(),
                                description = application.getString(R.string.empty_string),
                                username = application.getString(R.string.empty_string),
                                profileImage = application.getString(R.string.empty_string)
                            )
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        _profileInfo.value = ProfileInfo(
                            age = application.getString(R.string.zero).toInt(),
                            description = application.getString(R.string.empty_string),
                            username = application.getString(R.string.empty_string),
                            profileImage = application.getString(R.string.empty_string)
                        )
                    }

                })
            }
        }
    }

    override fun fetchEditProfileInfo(_editProfileInfo: MutableStateFlow<List<String>>) {
        firebaseAuth.uid?.let { firebaseUid ->
            databaseUserReference.child(firebaseUid).addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val editProfileList = arrayListOf<String>()

                    editProfileList.add(snapshot.child(USERNAME).value.toString())
                    editProfileList.add(snapshot.child(FIRST_NAME).value.toString())
                    editProfileList.add(snapshot.child(LAST_NAME).value.toString())
                    editProfileList.add(snapshot.child(BIO).value.toString())

                    _editProfileInfo.value = editProfileList
                }

                override fun onCancelled(error: DatabaseError) {
                    _editProfileInfo.value = emptyList()
                }
            })
        }
    }

    override fun fetchLoggedInUsername(_loggedInUsername: MutableStateFlow<String>) {
        firebaseAuth.uid?.let { firebaseUid ->
            databaseUserReference.child(firebaseUid)
                .addValueEventListener(object : ValueEventListener {

                    override fun onDataChange(snapshot: DataSnapshot) {
                        if (snapshot.exists()) {
                            _loggedInUsername.value = snapshot.child(USERNAME).value.toString()
                        } else {
                            _loggedInUsername.value = application.getString(R.string.empty_string)
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        _loggedInUsername.value = application.getString(R.string.empty_string)
                    }
                })
        }
    }

    override fun fetchLoggedInUserProfilePicture(_loggedInUserProfilePicture: MutableStateFlow<String>) {
        firebaseAuth.uid?.let { firebaseUid ->
            databaseUserReference.child(firebaseUid)
                .addValueEventListener(object : ValueEventListener {

                    override fun onDataChange(snapshot: DataSnapshot) {
                        if (snapshot.exists()) {
                            _loggedInUserProfilePicture.value = snapshot.child(PROFILE_IMAGE).value.toString()
                        } else {
                            _loggedInUserProfilePicture.value = application.getString(R.string.empty_string)
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        _loggedInUserProfilePicture.value = application.getString(R.string.empty_string)
                    }
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

            databaseUserReference.child(activeChallengesPathString).addValueEventListener(object: ValueEventListener {

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

            databaseUserReference.child(activeChallengesPathString).addValueEventListener(object: ValueEventListener {

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

    override fun fetchAllPostsBasedOnTitleAndUsername(challengeTitle: String, username: String, _postList: MutableStateFlow<List<PostListResponse>>) {
        databasePostsReference.addValueEventListener(object: ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                val postList = arrayListOf<PostListResponse>()

                for (posts in snapshot.children) {
                    posts.getValue(PostResponse::class.java).let { postResponse ->
                        postResponse?.let { data ->
                            if (data.username == username && data.title == challengeTitle) {
                                postList.add(PostListResponse(data))
                            }
                        }
                    }
                }
                _postList.value = postList
            }

            override fun onCancelled(error: DatabaseError) = Unit
        }
        )
    }

    override fun fetchAllUsersPostsOfChallenge(
        _postList: MutableStateFlow<List<PostListResponse>>,
        index: String
    ) {
        firebaseAuth.uid?.let { firebaseUid ->
            val activeChallengesPathString = "$firebaseUid$ACTIVE_CHALLENGES$index$ACTIVE_CHALLENGES_POSTS"

            databaseUserReference.child(activeChallengesPathString).addValueEventListener(object: ValueEventListener {

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