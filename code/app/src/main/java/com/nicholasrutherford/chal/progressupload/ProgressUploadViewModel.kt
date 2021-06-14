package com.nicholasrutherford.chal.progressupload

import android.app.Application
import android.net.Uri
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.nicholasrutherford.chal.main.MainActivity
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.data.responses.ActiveChallengeResponse
import com.nicholasrutherford.chal.data.responses.CurrentActiveChallengesResponse
import com.nicholasrutherford.chal.firebase.ACTIVE_CHALLENGES
import com.nicholasrutherford.chal.firebase.ACTIVE_CHALLENGES_POSTS
import com.nicholasrutherford.chal.firebase.PROFILE_IMAGE
import com.nicholasrutherford.chal.firebase.TITLE_ACTIVE_CHALLENGES_POST
import com.nicholasrutherford.chal.firebase.USERNAME
import com.nicholasrutherford.chal.firebase.USERS
import com.nicholasrutherford.chal.firebase.bindUserImageFile
import com.nicholasrutherford.chal.firebase.write.activechallengepost.WriteActiveChallengesPostsFirebase
import com.nicholasrutherford.chal.helpers.sharedpreference.updatesharedpreference.UpdateSharedPreferenceImpl
import com.nicholasrutherford.chal.navigationimpl.progressupload.ProgressUploadNavigationImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.UUID
import javax.inject.Inject

class ProgressUploadViewModel @Inject constructor(private val application: Application, mainActivity: MainActivity) : ViewModel() {

    var userPostTitle = ""
    var userPostBody = ""

    val viewState = ProgressUploadViewStateImpl()
    val navigation = ProgressUploadNavigationImpl(application, mainActivity)

    private val updateSharedPreference = UpdateSharedPreferenceImpl(application)

    private var progressImageUrl: String? = ""

    private val writeActiveChallengesPostFirebase = WriteActiveChallengesPostsFirebase()
    private val uid = FirebaseAuth.getInstance().uid ?: ""
    private val ref = FirebaseDatabase.getInstance().getReference(USERS)

    private var savedUserLastIndexOfProgress = 0
    private var isSelectedIndex = false

    private val _activeChallengeList = MutableStateFlow(listOf<ActiveChallengeResponse>())
    val activeChallengeList: StateFlow<List<ActiveChallengeResponse>> = _activeChallengeList

    private var selectedPhotoUri: Uri? = null

    init {
        viewState.toolbarTitle = application.getString(R.string.post_your_progress)
        fetchActiveChallenges()
    }

    fun onPhotoClicked(title: String, caption: String) {
        updateSharedPreference.updateProgressTitle(title)
        updateSharedPreference.updateProgressCaption(caption)
        navigation.openGallery()
    }

    fun onDiscardPostClicked() {
        val title = application.applicationContext.getString(R.string.discard_post)
        val desc = application.applicationContext.getString(R.string.are_you_sure_you_you_want_to_discard_the_post)

        navigation.showAlert(title, desc)
    }

    fun onPostProgressClicked(title: String, body: String, listOfChallenges: List<String>) { // check if the image is empty or not\
        var selectedIndex = 0

        listOfChallenges.forEachIndexed { index, challenges ->
            if (title == challenges) {
                selectedIndex = index
            }
        }
        navigation.showAcProgress()

        if (title == "" || body == "") {
            navigation.hideAcProgress()

            val alertTitle = application.applicationContext.getString(R.string.missing_fields)
            val alertDesc = application.applicationContext.getString(R.string.looks_like_were_missing_data)

            navigation.progressUploadAlert(alertDesc, alertTitle)
        } else {
            userPostTitle = title
            userPostBody = body

            uploadProgressPhoto(selectedIndex, selectedPhotoUri)
        }
    }

    fun uploadProgressPhoto(selectedIndex: Int, photoUri: Uri?) {
        photoUri?.let { progressPhotoUri ->
            val fileName = UUID.randomUUID().toString()
            val ref = FirebaseStorage.getInstance().getReference(bindUserImageFile(fileName))

            ref.putFile(progressPhotoUri)
                .addOnSuccessListener {
                    ref.downloadUrl.addOnSuccessListener { progressImage ->
                        progressImageUrl = progressImage.toString()
                        updateFirebaseUser(selectedIndex)
                    }
                }
                .addOnFailureListener {
                    navigation.hideAcProgress()

                    val title = application.applicationContext.getString(R.string.can_not_upload_image_to_server)
                    val message = application.applicationContext.getString(R.string.issue_uploading_image_to_server)

                    navigation.progressUploadAlert(message, title)
                }
        }
    }

    fun updateFirebaseUser(selectedIndex: Int) {
        var activeChallengesPostsIndex = 0
        ref.child("$uid$ACTIVE_CHALLENGES$selectedIndex$ACTIVE_CHALLENGES_POSTS").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (activeChallengesPosts in snapshot.children) {
                        activeChallengesPostsIndex++
                    }
                } else {
                    activeChallengesPostsIndex = 0
                }
                ref.child("$uid$ACTIVE_CHALLENGES$selectedIndex$ACTIVE_CHALLENGES_POSTS$activeChallengesPostsIndex/$TITLE_ACTIVE_CHALLENGES_POST").addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if (!snapshot.exists() && !isSelectedIndex) {
                            savedUserLastIndexOfProgress = activeChallengesPostsIndex
                            isSelectedIndex = true

                            writeUpdatedPostToFirebase(selectedIndex)

                            fetchUsernameAndUrl(savedUserLastIndexOfProgress, selectedIndex)

                            navigation.hideAcProgress()
                            navigation.finish()
                        }
                    }
                    override fun onCancelled(error: DatabaseError) {
                        println("error")
                    }
                })
            }

            override fun onCancelled(error: DatabaseError) {
                println("error")
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

    private fun fetchUsernameAndUrl(index: Int, selectedIndex: Int) {
        ref.child(uid).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val username = snapshot.child(USERNAME).value.toString()
                    val profileImageUrl = snapshot.child(PROFILE_IMAGE).value.toString()

                    writeUpdatedUsernameAndUrlToFirebase(username, index, profileImageUrl, selectedIndex)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                println("error")
            }
        })
    }

    fun onBackClicked() = println("test") //navigation.finish(progressUploadActivity)

    internal fun writeUpdatedPostToFirebase(selectedIndex: Int) {
        writeActiveChallengesPostFirebase.writeTitle(selectedIndex, savedUserLastIndexOfProgress, userPostTitle)
        writeActiveChallengesPostFirebase.writeDescription(selectedIndex, savedUserLastIndexOfProgress, userPostBody)
        writeActiveChallengesPostFirebase.writeCategory(selectedIndex, savedUserLastIndexOfProgress, 0)

        progressImageUrl?.let { imageUrl ->
            writeActiveChallengesPostFirebase.writeImage(selectedIndex, savedUserLastIndexOfProgress, imageUrl)
        }

        writeActiveChallengesPostFirebase.writeCurrentDay(selectedIndex, savedUserLastIndexOfProgress, "0")
    }

    internal fun writeUpdatedUsernameAndUrlToFirebase(username: String, index: Int, url: String, selectedIndex: Int) {
        writeActiveChallengesPostFirebase.writeUsername(selectedIndex, index, username)
        writeActiveChallengesPostFirebase.writeUsernameUrl(selectedIndex, index, url)
    }

    inner class ProgressUploadViewStateImpl : ProgressViewState {
        override var toolbarTitle = ""
        override var title = ""
        override var body = ""
        override var category = ""
        override var image = ""
    }
}
