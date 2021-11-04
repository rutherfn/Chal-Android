package com.nicholasrutherford.chal.main.upload.progress

import android.app.Application
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.annotation.RequiresApi
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.nicholasrutherford.chal.data.elert.Alert
import com.nicholasrutherford.chal.data.elert.AlertType
import com.nicholasrutherford.chal.data.post.PostListResponse
import com.nicholasrutherford.chal.firebase.realtime.database.create.CreateFirebaseDatabase
import com.nicholasrutherford.chal.firebase.realtime.database.fetch.FetchFirebaseDatabase
import com.nicholasrutherford.chal.helper.constants.*
import com.nicholasrutherford.chal.shared.preference.create.CreateSharedPreference
import com.nicholasrutherford.chal.shared.preference.fetch.FetchSharedPreference
import com.nicholasrutherford.chal.shared.preference.remove.RemoveSharedPreference
import com.nicholasrutherford.chal.ui.base_vm.BaseViewModel
import con.nicholasrutherford.chal.data.challenges.ActiveChallengesListResponse
import con.nicholasrutherford.chal.data.challenges.banner.ChallengeBannerType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*

class UploadProgressViewModel @ViewModelInject constructor(
    private val application: Application,
    private val navigation: UploadProgressNavigation,
    private val createFirebaseDatabase: CreateFirebaseDatabase,
    private val createSharedPreference: CreateSharedPreference,
    private val fetchFirebaseDatabase: FetchFirebaseDatabase,
    private val removeSharedPreference: RemoveSharedPreference,
    private val fetchSharedPreference: FetchSharedPreference
) : BaseViewModel() {

    private val _allUserActiveChallengesList = MutableStateFlow(listOf<ActiveChallengesListResponse>())
    val allUserActiveChallengesList: StateFlow<List<ActiveChallengesListResponse>> = _allUserActiveChallengesList

    private val _postList = MutableStateFlow(listOf<PostListResponse>())
    private val postList: StateFlow<List<PostListResponse>> = _postList

    private val _usernameAndUrl = MutableStateFlow(listOf<String>())
    private val usernameAndUrl: StateFlow<List<String>> = _usernameAndUrl

    private var currentPostsSize: Int = 0
    private var username: String? = null
    private var usernameUrl: String? = null

    private var profileUri: Uri? = null

    private var isSelectedIndex = false

    private var savedUserLastIndexOfProgress = 0

    private val uid = FirebaseAuth.getInstance().uid ?: ""
    private val ref = FirebaseDatabase.getInstance().getReference(USERS)

    private val buildSdkVersion = Build.VERSION.SDK_INT

    private var progressImageUrl: String? = ""

    private var isPhotoReadyToBeUpdated = false

    val viewState = UploadProgressViewStateImpl()

    init {
        viewModelScope.launch {
            postList.collect { data ->
                currentPostsSize = data.size
            }
        }

        viewModelScope.launch {
            usernameAndUrl.collect { data ->
                if (data.size == 2) {
                    username = data[0]
                    usernameUrl = data[1]
                }
            }
        }

        viewState.toolbarTitle = application.getString(R.string.post_your_progress)

        fetchFirebaseDatabase.fetchAllUserActiveChallenges(_allUserActiveChallengesList)
        fetchFirebaseDatabase.fetchAllPosts(_postList)
        fetchFirebaseDatabase.fetchUserNameAndUrl(_usernameAndUrl)
    }

    fun onImageUpdate() {
        if (isPhotoReadyToBeUpdated) {
            val profilePictureDirectory =
                fetchSharedPreference.fetchProfilePictureDirectorySharedPreference()

            if (profilePictureDirectory.isNullOrEmpty()) {
                viewState.imageTakeAPhotoBitmap = null
            } else {
                profileUri = Uri.parse(profilePictureDirectory)
                viewState.imageTakeAPhotoBitmap = getCapturedImage(profileUri as Uri)

                removeSharedPreference.removeProfilePictureDirectorySharedPreference()
                setViewStateAsUpdated()
            }
        }
    }

    private fun getCapturedImage(selectedPhotoUri: Uri): Bitmap? {
        return when {
            buildSdkVersion < 28 -> MediaStore.Images.Media.getBitmap(
                application.contentResolver,
                selectedPhotoUri
            )
            buildSdkVersion > 28 -> {
                bitMapByImageDecoderSource(selectedPhotoUri)
            }
            else -> {
                return null
            }
        }
    }

    fun onBackClicked() = navigation.onNavigateBack()

    @RequiresApi(Build.VERSION_CODES.P)
    private fun bitMapByImageDecoderSource(selectedPhotoUri: Uri): Bitmap {
        val source = ImageDecoder.createSource(application.contentResolver, selectedPhotoUri)
        return ImageDecoder.decodeBitmap(source)
    }

    fun updateIsPhotoReadyToBeUpdated(isPhotoReadyToBeUpdated: Boolean) {
        // TODO prompt to prompt me if i want to take a picture or choose one from my gallery
        this.isPhotoReadyToBeUpdated = isPhotoReadyToBeUpdated
    }

    fun onDiscardPostClicked() {
        setShouldSetAlertAsUpdated(
            title = application.getString(R.string.are_you_sure_you_you_want_to_discard_the_post),
            message = application.getString(R.string.discard_post_details),
            type = AlertType.YES_NO_ALERT_WITH_ACTION,
            shouldCloseAppAfterDone = false
        )
        setShouldShowAlertAsUpdated()
    }

    fun onPostProgressClicked(title: String, caption: String, listOfChallenges: List<String>) {
        // todo do a check here to see if we have internet avivable
        var selectedIndex = 0

        listOfChallenges.forEachIndexed { index, challenge ->
            if (title == challenge) {
                selectedIndex = index
            }
        }

        setShouldShowProgressAsUpdated()

        when (caption) {
            "" -> {
                setShouldSetAlertAsUpdated(
                    title = application.getString(R.string.missing_fields),
                    message = application.getString(R.string.looks_like_were_missing_caption),
                    type = AlertType.REGULAR_OK_ALERT,
                    shouldCloseAppAfterDone = false
                )
                setShouldShowDismissProgressAsUpdated()
                setShouldShowAlertAsUpdated()
            }
            else -> {
                profileUri?.let { photoUri ->
                    uploadProgressPhoto(title, caption, selectedIndex, photoUri)
                } ?: run {
                    setShouldSetAlertAsUpdated(
                        title = application.getString(R.string.missing_fields),
                        message = application.getString(R.string.looks_like_were_missing_image),
                        type = AlertType.REGULAR_OK_ALERT,
                        shouldCloseAppAfterDone = false
                    )
                    setShouldShowDismissProgressAsUpdated()
                    setShouldShowAlertAsUpdated()
                }
            }
        }
    }

    private fun uploadProgressPhoto(title: String, caption: String, selectedIndex: Int, photoUri: Uri) {
        val fileName = UUID.randomUUID().toString()
        val ref = FirebaseStorage.getInstance().getReference(bindUserImageFile(fileName))

        ref.putFile(photoUri)
            .addOnSuccessListener {
                ref.downloadUrl.addOnSuccessListener { progressImage ->
                    progressImageUrl = progressImage.toString()
                    updateFirebaseUser(title, caption, selectedIndex)
                }
            }
            .addOnFailureListener {
                setShouldShowDismissProgressAsUpdated()

                setShouldSetAlertAsUpdated(
                    title = application.getString(R.string.can_not_upload_image_to_server),
                    message = application.getString(R.string.issue_uploading_image_to_server),
                    type = AlertType.REGULAR_OK_ALERT,
                    shouldCloseAppAfterDone = false
                )
                setShouldShowDismissProgressAsUpdated()
                setShouldShowAlertAsUpdated()
            }
    }

    private fun updateFirebaseUser(title: String, caption: String, selectedIndex: Int) {
        var activeChallengesPostsIndex = 0
        var currentChallengeDay = "0"
        var currentChallengeExpireDay = "0"

        ref.child("$uid$ACTIVE_CHALLENGES$selectedIndex$ACTIVE_CHALLENGES_POSTS").addValueEventListener(object :
            ValueEventListener {
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
                        setShouldShowDismissProgressAsUpdated()

                        setShouldSetAlertAsUpdated(
                            title = application.getString(R.string.error_updating_data_title),
                            message = application.getString(R.string.error_updating_data_desc),
                            type = AlertType.REGULAR_OK_ALERT,
                            shouldCloseAppAfterDone = false
                        )
                        setShouldShowDismissProgressAsUpdated()
                        setShouldShowAlertAsUpdated()
                    }
                })

                ref.child("$uid$ACTIVE_CHALLENGES$selectedIndex/$DATE_CHALLENGE_EXPIRED").addValueEventListener(object : ValueEventListener {

                    override fun onDataChange(snapshot: DataSnapshot) {
                        if (snapshot.exists()) {
                            currentChallengeExpireDay = snapshot.value.toString()
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        setShouldShowDismissProgressAsUpdated()

                        setShouldSetAlertAsUpdated(
                            title = application.getString(R.string.error_updating_data_title),
                            message = application.getString(R.string.error_updating_data_desc),
                            type = AlertType.REGULAR_OK_ALERT,
                            shouldCloseAppAfterDone = false
                        )
                        setShouldShowDismissProgressAsUpdated()
                        setShouldShowAlertAsUpdated()
                    }
                })

                ref.child("$uid$ACTIVE_CHALLENGES$selectedIndex$ACTIVE_CHALLENGES_POSTS$activeChallengesPostsIndex/$TITLE").addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if (!snapshot.exists() && !isSelectedIndex) {
                            savedUserLastIndexOfProgress = activeChallengesPostsIndex
                            isSelectedIndex = true

                            if (fetchSharedPreference.fetchChallengeModeSharedPreference()) {
                                writeNewPost(
                                    title,
                                    caption,
                                    selectedIndex,
                                    currentChallengeDay,
                                    currentChallengeExpireDay
                                )
                            }
                        }
                    }
                    override fun onCancelled(error: DatabaseError) {
                        setShouldShowDismissProgressAsUpdated()

                        setShouldSetAlertAsUpdated(
                            title = application.getString(R.string.error_updating_data_title),
                            message = application.getString(R.string.error_updating_data_desc),
                            type = AlertType.REGULAR_OK_ALERT,
                            shouldCloseAppAfterDone = false
                        )
                        setShouldShowDismissProgressAsUpdated()
                        setShouldShowAlertAsUpdated()
                    }
                })
            }

            override fun onCancelled(error: DatabaseError) {
                setShouldShowDismissProgressAsUpdated()

                setShouldSetAlertAsUpdated(
                    title = application.getString(R.string.error_updating_data_title),
                    message = application.getString(R.string.error_updating_data_desc),
                    type = AlertType.REGULAR_OK_ALERT,
                    shouldCloseAppAfterDone = false
                )
                setShouldShowDismissProgressAsUpdated()
                setShouldShowAlertAsUpdated()
            }
        })

        isSelectedIndex = false
    }

    internal fun writeNewPost(title: String, body: String, selectedIndex: Int, currentChallengeDay: String, currentChallengeExpireDay: String) {
        val writeActivePost = WriteActivePostImpl()
        val writeNewActiveChallengeImpl = WriteNewActiveChallengeImpl()

        val newCurrentDay = currentChallengeDay.toInt() + 1

        val isChallengeCompleted = newCurrentDay == currentChallengeExpireDay.toInt()

        if (newCurrentDay != 7) {

            writeActivePost.writePost(
                uid, selectedIndex, savedUserLastIndexOfProgress, currentPostsSize, ActivePost(
                    title = title,
                    description = body,
                    category = 0,
                    image = progressImageUrl ?: "",
                    currentDay = newCurrentDay.toString(),
                    username = username ?: "",
                    usernameUrl = usernameUrl ?: "",
                    dateChallengeExpired = currentChallengeExpireDay
                )
            )

            writeNewActiveChallengeImpl.writeCurrentDay(
                uid,
                selectedIndex.toString(),
                newCurrentDay
            )

            writeNewsFeedBanner(
                title = "A new post has been updated for the",
                desc = title,
                isVisible = true,
                isCloseable = true
            )

            setShouldShowDismissProgressAsUpdated()

            showAddedProgressAlert(title, newCurrentDay)
            navigation.onNavigateBack()
        } else {
            // we need to take this challenge, remove it and then add the challenge in the user post list
        }
    }

    fun writeNewsFeedBanner(title: String, desc: String, isVisible: Boolean, isCloseable: Boolean) {
        removeSharedPreference.removeChallengeBannerPreferences()

        createSharedPreference.createChallengeBannerTypeTitleSharedPreference(title)
        createSharedPreference.createChallengeBannerTypeDescSharedPreference(desc)
        createSharedPreference.createChallengeBannerTypeIsVisible(isVisible)
        createSharedPreference.createChallengeBannerTypeIsCloseable(isCloseable)

        createSharedPreference.createChallengeBannerTypeSharedPreference(ChallengeBannerType.JUST_POSTED.value)
    }

    private fun showAddedProgressAlert(challengeTitle: String, newCurrentDay: Int) {
        if (fetchSharedPreference.fetchChallengeModeSharedPreference()) {
            setShouldSetAlertAsUpdated(
                title = "Progress has been updated",
                message = "Congrats! you have posted progress on the " +
                        "$challengeTitle. Press OK to see progress on the news feed",
                type = AlertType.REGULAR_OK_ALERT,
                shouldCloseAppAfterDone = false
            )
            setShouldShowDismissProgressAsUpdated()
            setShouldShowAlertAsUpdated()
        } else {
            // copy here for actual challenge update
        }

        clearViewState()
    }

    private fun clearViewState() {
        viewState.title = application.getString(R.string.empty_string)
        viewState.body = application.getString(R.string.empty_string)
        viewState.category = application.getString(R.string.empty_string)
        viewState.image = application.getString(R.string.empty_string)
        viewState.imageTakeAPhotoBitmap = null
        viewState.isClearingUI = true

        setViewStateAsUpdated()
    }

    inner class UploadProgressViewStateImpl : UploadProgressViewState {
        override var toolbarTitle: String = application.getString(R.string.empty_string)
        override var title: String = application.getString(R.string.empty_string)
        override var body: String = application.getString(R.string.empty_string)
        override var category: String = application.getString(R.string.empty_string)
        override var image: String = application.getString(R.string.empty_string)
        override var imageTakeAPhotoBitmap: Bitmap? = null
        override var isClearingUI: Boolean = false
    }
}