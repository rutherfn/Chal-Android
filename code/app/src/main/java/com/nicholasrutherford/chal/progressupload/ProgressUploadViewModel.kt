package com.nicholasrutherford.chal.progressupload

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.nicholasrutherford.chal.ChalRoom
import com.nicholasrutherford.chal.challengesredesign.challengedetails.STARTER_INDEX
import com.nicholasrutherford.chal.data.realdata.ActiveChallengesPosts
import com.nicholasrutherford.chal.firebase.ACTIVE_CHALLENGES
import com.nicholasrutherford.chal.firebase.ACTIVE_CHALLENGES_POSTS
import com.nicholasrutherford.chal.firebase.CATEGORY_NAME
import com.nicholasrutherford.chal.firebase.TITLE_ACTIVE_CHALLENGES_POST
import com.nicholasrutherford.chal.firebase.USERS
import com.nicholasrutherford.chal.firebase.bindUserImageFile
import com.nicholasrutherford.chal.firebase.read.ReadAccountFirebase
import com.nicholasrutherford.chal.firebase.write.activechallengepost.WriteActiveChallengesPostsFirebase
import com.nicholasrutherford.chal.navigationimpl.progressupload.ProgressUploadNavigationImpl
import com.nicholasrutherford.chal.room.entity.challengesposts.ChallengesPostsEntity
import kotlinx.coroutines.launch
import java.util.UUID

class ProgressUploadViewModel(private val progressUploadActivity: ProgressUploadActivity, private val appContext: Context, private val container: Int) : ViewModel() {

    var userPostTitle = ""
    var userPostBody = ""
    var userPostCategory = ""

    private val readProfileDetailsFirebase = ReadAccountFirebase(appContext)

    val viewState = ProgressUploadViewStateImpl()
    val navigation = ProgressUploadNavigationImpl()

    private var progressImageUrl: String? = ""

    private val writeActiveChallengesPostFirebase = WriteActiveChallengesPostsFirebase()
    private val uid = FirebaseAuth.getInstance().uid ?: ""
    private val ref = FirebaseDatabase.getInstance().getReference(USERS)

    private var savedUserLastIndexOfProgress = 0
    private var isSelectedIndex = false

    var currentUsername = ""

    init {
        readProfileDetailsFirebase.getUsername()?.let { userName ->
            currentUsername = userName
        }
    }

    fun onPhotoClicked() {
        navigation.openGallery(progressUploadActivity)
    }

    fun onDiscardPostClicked() {
        navigation.showAlert("Are you sure you want to discard this post?", "Discard Post", progressUploadActivity, appContext, true, container)
    }

    fun onPostProgressClicked(title: String, body: String, category: String, photoUri: Uri?) {
        navigation.showAcProgress(progressUploadActivity)

        if (title == "" || body == "" || category == "" || photoUri == null) {
            navigation.hideAcProgress()
            navigation.progressUploadAlert("Looks like were missing data. Please enter info for all fields, in order to continue", "Missing Fields", progressUploadActivity)
        } else {
            userPostTitle = title
            userPostBody = body
            userPostCategory = category

            uploadProgressPhoto(photoUri)
        }
    }

    fun uploadProgressPhoto(photoUri: Uri?) {
        photoUri?.let { progressPhotoUri ->
            val fileName = UUID.randomUUID().toString()
            val ref = FirebaseStorage.getInstance().getReference(bindUserImageFile(fileName))

            ref.putFile(progressPhotoUri)
                .addOnSuccessListener {
                    ref.downloadUrl.addOnSuccessListener { progressImage ->
                        progressImageUrl = progressImage.toString()
                        updateFirebaseUser()
                    }
                }
                .addOnFailureListener {
                    navigation.hideAcProgress()
                    navigation.progressUploadAlert("Issue uploading image to server. Please Try Again", "Can not upload image to server", progressUploadActivity)
                }
        }
    }

    fun updateFirebaseUser() {
        listOf(0, 1, 2, 3, 4, 5, 6, 7).forEach { index ->

            ref.child("$uid$ACTIVE_CHALLENGES$STARTER_INDEX$ACTIVE_CHALLENGES_POSTS$index/$TITLE_ACTIVE_CHALLENGES_POST").addValueEventListener(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    println("error")
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    if (!snapshot.exists() && !isSelectedIndex) {
                        savedUserLastIndexOfProgress = index
                        isSelectedIndex = true

                        writeUpdatedPostToFirebase()

                        navigation.hideAcProgress()
                    }
                }
            })
        }
        isSelectedIndex = false
    }

    internal fun writeUpdatedPostToFirebase() {
        writeActiveChallengesPostFirebase.writeTitle(savedUserLastIndexOfProgress, userPostTitle)
        writeActiveChallengesPostFirebase.writeDescription(savedUserLastIndexOfProgress, userPostBody)
        writeActiveChallengesPostFirebase.writeCategory(savedUserLastIndexOfProgress, 0)

        progressImageUrl?.let { imageUrl ->
            writeActiveChallengesPostFirebase.writeImage(savedUserLastIndexOfProgress, imageUrl)
        }

        writeActiveChallengesPostFirebase.writeCurrentDay(savedUserLastIndexOfProgress, "0")
    }

    inner class ProgressUploadViewStateImpl : ProgressViewState {
        override var title = ""
        override var body = ""
        override var category = ""
        override var image = ""
    }
}
