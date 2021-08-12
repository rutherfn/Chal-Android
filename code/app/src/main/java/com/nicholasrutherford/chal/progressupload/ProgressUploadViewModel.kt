package com.nicholasrutherford.chal.progressupload

import android.app.Application
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.nicholasrutherford.chal.ChallengeCalenderDay
import com.nicholasrutherford.chal.InternetConnectivity
import com.nicholasrutherford.chal.main.MainActivity
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.data.firebase.ActivePost
import com.nicholasrutherford.chal.data.responses.ActiveChallengeResponse
import com.nicholasrutherford.chal.data.responses.CurrentActiveChallengesResponse
import com.nicholasrutherford.chal.data.responses.post.PostListResponse
import com.nicholasrutherford.chal.firebase.ACTIVE_CHALLENGES
import com.nicholasrutherford.chal.firebase.ACTIVE_CHALLENGES_POSTS
import com.nicholasrutherford.chal.firebase.CURRENT_DAY
import com.nicholasrutherford.chal.firebase.PROFILE_IMAGE
import com.nicholasrutherford.chal.firebase.TITLE
import com.nicholasrutherford.chal.firebase.USERNAME
import com.nicholasrutherford.chal.firebase.USERS
import com.nicholasrutherford.chal.firebase.bindUserImageFile
import com.nicholasrutherford.chal.firebase.read.accountinfo.ReadFirebaseFieldsImpl
import com.nicholasrutherford.chal.firebase.write.activenewchallenge.WriteNewActiveChallengeImpl
import com.nicholasrutherford.chal.firebase.write.activepost.WriteActivePostImpl
import com.nicholasrutherford.chal.helpers.sharedpreference.updatesharedpreference.UpdateSharedPreferenceImpl
import com.nicholasrutherford.chal.navigationimpl.progressupload.ProgressUploadNavigationImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

class ProgressUploadViewModel @Inject constructor(private val application: Application, mainActivity: MainActivity) : ViewModel() {

    val viewState = ProgressUploadViewStateImpl()
    val navigation = ProgressUploadNavigationImpl(application, mainActivity)

    private val updateSharedPreference = UpdateSharedPreferenceImpl(application)

    private var progressImageUrl: String? = ""

    private val writeActivePost = WriteActivePostImpl()

    private val uid = FirebaseAuth.getInstance().uid ?: ""
    private val ref = FirebaseDatabase.getInstance().getReference(USERS)

    private var savedUserLastIndexOfProgress = 0
    private var isSelectedIndex = false

    private val _activeChallengeList = MutableStateFlow(listOf<ActiveChallengeResponse>())
    val activeChallengeList: StateFlow<List<ActiveChallengeResponse>> = _activeChallengeList

    private val _userInfoList = MutableStateFlow(listOf<String>())
    private val userInfoList: StateFlow<List<String>> = _userInfoList

    private var username: String? = null
    private var usernameUrl: String? = null

    private val _postList = MutableStateFlow(listOf<PostListResponse>())
    private val postList: StateFlow<List<PostListResponse>> = _postList

    private var currentPostsSize: Int = 0

    private val readFirebaseFields = ReadFirebaseFieldsImpl()

    private val internetConnectivity = InternetConnectivity()

    private val challengeCurrentDay = ChallengeCalenderDay()

    init {
        viewState.toolbarTitle = application.getString(R.string.post_your_progress)
        fetchActiveChallenges()
        fetchAllPosts()
        fetchUsernameAndUrl()

        viewModelScope.launch {
            postList.collect { data ->
                currentPostsSize = data.size
            }
        }

        viewModelScope.launch {
            userInfoList.collect { data ->
                if (data.size == 2) {
                    username = data[0]
                    usernameUrl = data[1]
                }
            }
        }
    }

    fun onPhotoClicked(title: String, caption: String) {
        updateSharedPreference.updateProgressTitle(title)
        updateSharedPreference.updateProgressCaption(caption)
        navigation.openGallery()
    }

    fun onDiscardPostClicked() {
        val title = application.applicationContext.getString(R.string.discard_post_details)
        val desc = application.applicationContext.getString(R.string.are_you_sure_you_you_want_to_discard_the_post)

        navigation.showAlert(title, desc)
    }

    fun onPostProgressClicked(title: String, body: String, listOfChallenges: List<String>, selectedPhotoUri: Uri?) {
        if (internetConnectivity.isInternetAvailable(application)) {
            var selectedIndex = 0

            listOfChallenges.forEachIndexed { index, challenge ->
                if (title == challenge) {
                    selectedIndex = index
                }
            }
            navigation.showAcProgress()

            when (body) {
                "" -> {
                    showErrorAlert(
                        title = application.applicationContext.getString(R.string.missing_fields),
                        message = application.applicationContext.getString(R.string.looks_like_were_missing_caption)
                    )
                }
                else -> {
                    selectedPhotoUri?.let { photoUri ->
                        uploadProgressPhoto(title, body, selectedIndex, photoUri)
                    } ?: run {
                        showErrorAlert(
                            title = application.applicationContext.getString(R.string.missing_fields),
                            message = application.applicationContext.getString(R.string.looks_like_were_missing_image)
                        )
                    }
                }
            }
        } else {
            showErrorAlert(
                title = application.applicationContext.getString(R.string.internet_not_available),
                message = application.applicationContext.getString(R.string.looks_like_the_system_cant_detect_internet)
            )
            // no internet we are on airplane mode
        }
    }

    private fun showErrorAlert(title: String, message: String) {
        navigation.hideAcProgress()
        navigation.progressUploadAlert(message, title)
    }

    private fun uploadProgressPhoto(title: String, body: String, selectedIndex: Int, photoUri: Uri) {
            val fileName = UUID.randomUUID().toString()
            val ref = FirebaseStorage.getInstance().getReference(bindUserImageFile(fileName))

            ref.putFile(photoUri)
                .addOnSuccessListener {
                    ref.downloadUrl.addOnSuccessListener { progressImage ->
                        progressImageUrl = progressImage.toString()
                        updateFirebaseUser(title, body, selectedIndex)
                    }
                }
                .addOnFailureListener {
                    navigation.hideAcProgress()

                    val titleAlert= application.applicationContext.getString(R.string.can_not_upload_image_to_server)
                    val messageAlert = application.applicationContext.getString(R.string.issue_uploading_image_to_server)

                    navigation.progressUploadAlert(messageAlert, titleAlert)
                }
    }

    private fun updateFirebaseUser(title: String, body: String, selectedIndex: Int) {
        var activeChallengesPostsIndex = 0
        var currentChallengeDay = "0"
        ref.child("$uid$ACTIVE_CHALLENGES$selectedIndex$ACTIVE_CHALLENGES_POSTS").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (activeChallengesPosts in snapshot.children) {
                        activeChallengesPostsIndex++
                    }
                } else {
                    activeChallengesPostsIndex = 0
                }

                ref.child("$uid$ACTIVE_CHALLENGES$selectedIndex/$CURRENT_DAY").addValueEventListener(object : ValueEventListener {

                    override fun onDataChange(snapshot: DataSnapshot) {
                        if (snapshot.exists()) {
                            currentChallengeDay = snapshot.value.toString()
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        showErrorAlert(
                            title = application.applicationContext.getString(R.string.error_updating_data_title),
                            message = application.applicationContext.getString(R.string.error_updating_data_desc)
                        )
                    }
                })

                ref.child("$uid$ACTIVE_CHALLENGES$selectedIndex$ACTIVE_CHALLENGES_POSTS$activeChallengesPostsIndex/$TITLE").addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if (!snapshot.exists() && !isSelectedIndex) {
                            println(currentChallengeDay)
                            savedUserLastIndexOfProgress = activeChallengesPostsIndex
                            isSelectedIndex = true

                            writeNewPost(title, body, selectedIndex, currentChallengeDay)
                        }
                    }
                    override fun onCancelled(error: DatabaseError) {
                        showErrorAlert(
                            title = application.applicationContext.getString(R.string.error_updating_data_title),
                            message = application.applicationContext.getString(R.string.error_updating_data_desc)
                        )
                    }
                })
            }

            override fun onCancelled(error: DatabaseError) {
                showErrorAlert(
                    title = application.applicationContext.getString(R.string.error_updating_data_title),
                    message = application.applicationContext.getString(R.string.error_updating_data_desc)
                )
            }
        })

        isSelectedIndex = false
    }

    private fun fetchActiveChallenges() {
        ref.child("$uid$ACTIVE_CHALLENGES")
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {}

                override fun onDataChange(snapshot: DataSnapshot) {
                    val activeChallengeList = arrayListOf<ActiveChallengeResponse>()
                    for (activeChallenges in snapshot.children) {
                        activeChallenges.getValue(CurrentActiveChallengesResponse::class.java).let {
                            it?.let { activeChallenge ->
                                activeChallengeList.add(ActiveChallengeResponse(activeChallenge.name))
                            }
                        }
                    }
                    _activeChallengeList.value = activeChallengeList

                }
            })
    }

    private fun fetchAllPosts() = readFirebaseFields.getAllPosts(_postList)

    private fun fetchUsernameAndUrl() {
        ref.child(uid).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val userInfoList = arrayListOf<String>()
                    val username = snapshot.child(USERNAME).value.toString()
                    val profileImageUrl = snapshot.child(PROFILE_IMAGE).value.toString()

                    userInfoList.add(username)
                    userInfoList.add(profileImageUrl)

                    _userInfoList.value = userInfoList
                }
            }

            override fun onCancelled(error: DatabaseError) {
                showErrorAlert(
                    title = application.applicationContext.getString(R.string.error_updating_data_title),
                    message = application.applicationContext.getString(R.string.error_updating_data_desc)
                )
            }
        })
    }

    fun onBackClicked() = navigation.pop()

    internal fun writeNewPost(title: String, body: String, selectedIndex: Int, currentChallengeDay: String) {
        val writeNewActiveChallengeImpl = WriteNewActiveChallengeImpl()

        val newCurrentDay = currentChallengeDay.toInt() + 1

        writeActivePost.writePost(uid, selectedIndex, savedUserLastIndexOfProgress, currentPostsSize, ActivePost(
            title = title,
            description = body,
            category = 0,
            image = progressImageUrl ?: "",
            currentDay = newCurrentDay.toString(),
            username = username ?: "",
            usernameUrl = usernameUrl ?: ""
        ))

        writeNewActiveChallengeImpl.writeCurrentDay(
            uid,
            selectedIndex.toString(),
            newCurrentDay
        )

        navigateToAddedProgress()
    }

    fun navigateToAddedProgress() {
        navigation.hideAcProgress()
        navigation.showAddedProgress()
    }


    inner class ProgressUploadViewStateImpl : ProgressViewState {
        override var toolbarTitle = ""
        override var title = ""
        override var body = ""
        override var category = ""
        override var image = ""
    }
}
