package com.nicholasrutherford.chal.progressupload

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.data.responses.CurrentActiveChallengesResponse
import com.nicholasrutherford.chal.firebase.ACTIVE_CHALLENGES
import com.nicholasrutherford.chal.firebase.ACTIVE_CHALLENGES_POSTS
import com.nicholasrutherford.chal.firebase.TITLE_ACTIVE_CHALLENGES_POST
import com.nicholasrutherford.chal.firebase.USERS
import com.nicholasrutherford.chal.firebase.bindUserImageFile
import com.nicholasrutherford.chal.firebase.read.ReadAccountFirebase
import com.nicholasrutherford.chal.firebase.write.activechallengepost.WriteActiveChallengesPostsFirebase
import com.nicholasrutherford.chal.navigationimpl.progressupload.ProgressUploadNavigationImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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

    private val _activeChallengeAndCategoryResponse = MutableStateFlow(listOf<ActiveChallengeAndCategoryResponse>())
    val activeChallengeAndCategoryResponse: StateFlow<List<ActiveChallengeAndCategoryResponse>> = _activeChallengeAndCategoryResponse

    init {
        readProfileDetailsFirebase.getUsername()?.let { userName ->
            currentUsername = userName
        }

        fetchActiveChallenges()
    }

    fun onPhotoClicked() {
        navigation.openGallery(progressUploadActivity)
    }

    fun onDiscardPostClicked() {
        navigation.showAlert("Are you sure you want to discard this post?", "Discard Post", progressUploadActivity, appContext, true, container)
    }

    fun onPostProgressClicked(title: String, body: String, category: String, selectedIndex: Int, photoUri: Uri?) {
        navigation.showAcProgress(progressUploadActivity)

        if (title == "" || body == "" || category == "" || photoUri == null) {
            navigation.hideAcProgress()
            navigation.progressUploadAlert("Looks like were missing data. Please enter info for all fields, in order to continue", "Missing Fields", progressUploadActivity)
        } else {
            userPostTitle = title
            userPostBody = body
            userPostCategory = category

            uploadProgressPhoto(selectedIndex, photoUri)
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
                    navigation.progressUploadAlert("Issue uploading image to server. Please Try Again", "Can not upload image to server", progressUploadActivity)
                }
        }
    }

    fun updateFirebaseUser(selectedIndex: Int) {
        listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20).forEach { index ->

            ref.child("$uid$ACTIVE_CHALLENGES$selectedIndex$ACTIVE_CHALLENGES_POSTS$index/$TITLE_ACTIVE_CHALLENGES_POST").addValueEventListener(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    println("error")
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    if (!snapshot.exists() && !isSelectedIndex) {
                        savedUserLastIndexOfProgress = index
                        isSelectedIndex = true

                        writeUpdatedPostToFirebase(selectedIndex)

                        navigation.hideAcProgress()
                        navigation.finish(progressUploadActivity) // place holder for right now
                    }
                }
            })
        }
        isSelectedIndex = false
    }

    private fun fetchActiveChallenges() {
        ref.child("$uid$ACTIVE_CHALLENGES")
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {}

                override fun onDataChange(snapshot: DataSnapshot) {
                    val activeChallengeAndCategoryResponseList = arrayListOf<ActiveChallengeAndCategoryResponse>()
                    for (activeChallenges in snapshot.children) {
                        activeChallenges.getValue(CurrentActiveChallengesResponse::class.java).let {
                            it?.let { activeChallenge ->
                                activeChallengeAndCategoryResponseList.add(ActiveChallengeAndCategoryResponse(activeChallenge.name, activeChallenge.categoryName))
                            }
                        }
                    }
                    _activeChallengeAndCategoryResponse.value = activeChallengeAndCategoryResponseList
                }
            })
    }

    fun onBackClicked() = navigation.finish(progressUploadActivity)

    fun onCancelAndDiscardPostClicked() {
        val title = progressUploadActivity.getString(R.string.cancel_and_discard_post_title)
        val message = progressUploadActivity.getString(R.string.cancel_and_discard_post_message)
        navigation.showCancelAndDiscardAlert(message, title, progressUploadActivity)
    }

    internal fun writeUpdatedPostToFirebase(selectedIndex: Int) {
        writeActiveChallengesPostFirebase.writeTitle(selectedIndex, savedUserLastIndexOfProgress, userPostTitle)
        writeActiveChallengesPostFirebase.writeDescription(selectedIndex, savedUserLastIndexOfProgress, userPostBody)
        writeActiveChallengesPostFirebase.writeCategory(selectedIndex, savedUserLastIndexOfProgress, 0)

        progressImageUrl?.let { imageUrl ->
            writeActiveChallengesPostFirebase.writeImage(selectedIndex, savedUserLastIndexOfProgress, imageUrl)
        }

        writeActiveChallengesPostFirebase.writeCurrentDay(selectedIndex, savedUserLastIndexOfProgress, "0")
    }

    data class ActiveChallengeAndCategoryResponse(
        var challenge: String,
        var category: String
    )

    inner class ProgressUploadViewStateImpl : ProgressViewState {
        override var title = ""
        override var body = ""
        override var category = ""
        override var image = ""
    }
}
