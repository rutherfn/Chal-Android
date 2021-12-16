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
import com.nicholasrutherford.chal.Network
import com.nicholasrutherford.chal.data.elert.Alert
import com.nicholasrutherford.chal.data.elert.AlertType
import com.nicholasrutherford.chal.data.post.PostListResponse
import com.nicholasrutherford.chal.firebase.realtime.database.create.CreateFirebaseDatabase
import com.nicholasrutherford.chal.firebase.realtime.database.fetch.FetchFirebaseDatabase
import com.nicholasrutherford.chal.helper.constants.*
import com.nicholasrutherford.chal.helper.image.Image
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
    private val image: Image,
    private val network: Network,
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

    private val _loggedInUsername = MutableStateFlow(application.getString(R.string.empty_string))
    private val loggedInUsername: StateFlow<String> = _loggedInUsername

    private val _loggedInUserProfilePicture = MutableStateFlow(application.getString(R.string.empty_string))
    private val loggedInUserProfilePicture: StateFlow<String> = _loggedInUserProfilePicture

    private var currentPostsSize: Int = application.getString(R.string.zero).toInt()
    private var username: String? = null
    private var usernameUrl: String? = null

    private var profileUri: Uri? = null

    private var isSelectedIndex = false

    private var savedUserLastIndexOfProgress = application.getString(R.string.zero).toInt()

    private val uid = FirebaseAuth.getInstance().uid ?: application.getString(R.string.empty_string)
    private val ref = FirebaseDatabase.getInstance().getReference(USERS)

    private var progressImageUrl: String? = application.getString(R.string.empty_string)

    private var isPhotoReadyToBeUpdated = false

    val viewState = UploadProgressViewStateImpl()

    init {
        viewModelScope.launch {
            postList.collect { data ->
                currentPostsSize = data.size
            }
        }

        viewModelScope.launch {
            loggedInUsername.collect { loggedInUsername ->
                username = loggedInUsername
            }
        }

        viewModelScope.launch {
            loggedInUserProfilePicture.collect { loggedInUserProfilePicture ->
                usernameUrl = loggedInUserProfilePicture
            }
        }

        viewState.toolbarTitle = application.getString(R.string.post_your_progress)

        fetchFirebaseDatabase.fetchAllUserActiveChallenges(_allUserActiveChallengesList)
        fetchFirebaseDatabase.fetchAllPosts(_postList)

        fetchFirebaseDatabase.fetchLoggedInUsername(_loggedInUsername)
        fetchFirebaseDatabase.fetchLoggedInUserProfilePicture(_loggedInUserProfilePicture)
    }

    fun onImageUpdate() {
        if (isPhotoReadyToBeUpdated) {
            val profilePictureDirectory =
                fetchSharedPreference.fetchProfilePictureDirectorySharedPreference()

            if (profilePictureDirectory.isNullOrEmpty()) {
                viewState.imageTakeAPhotoBitmap = null
            } else {
                profileUri = Uri.parse(profilePictureDirectory)
                viewState.imageTakeAPhotoBitmap = image.getCapturedImage(profileUri as Uri)

                removeSharedPreference.removeProfilePictureDirectorySharedPreference()
                setViewStateAsUpdated()
            }
        }
    }

    fun onBackClicked() = navigation.onNavigateBack()

    fun updateIsPhotoReadyToBeUpdated(isPhotoReadyToBeUpdated: Boolean) {
        this.isPhotoReadyToBeUpdated = isPhotoReadyToBeUpdated
        setShouldSetAlertAsUpdated(
            title = application.getString(R.string.uploading_image),
            message = application.getString(R.string.how_would_you_like_to_upload_your_image),
            type = AlertType.CAMERA_OR_GALLERY_ALERT,
            shouldCloseAppAfterDone = false
        )
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
        if (!network.isConnected()) {
            setShouldSetAlertAsUpdated(
                title = application.getString(R.string.unable_to_post_progress),
                message = application.getString(R.string.error_no_internet_log_in),
                type = AlertType.REGULAR_OK_ALERT,
                shouldCloseAppAfterDone = false
            )
            setShouldShowDismissProgressAsUpdated()
            setShouldShowAlertAsUpdated()
        } else {
            var selectedIndex = 0

            listOfChallenges.forEachIndexed { index, challenge ->
                if (title == challenge) {
                    selectedIndex = index
                }
            }

            setShouldShowProgressAsUpdated()

            when (caption) {
                application.getString(R.string.empty_string) -> {
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
        // todo: Steps to refactor said function

        // todo: Step 1: take all of these firebase impl and abstract them over to impl methods
        // todo: Step 2: from there, each call can set a flow. the flow gets observed, and if its sucessful it goes to the next step
        // todo: Step 3: check to see if we need both currentChallengeDay and currentChallengeExpireDate.

        // todo: Steps for flows calls

        // todo: Step 1 / call 1: Make a call to get the selected index active challenges posts.
        // todo: continued: if the snapshot exists loop over children and increment activeChallengesPostIndex.
        // todo: continued: if its empty, then just reset it back to 0
        // todo: continued: if for any reason, this call hits the onCanclled method do the following
        // todo: continued: build out a alert response, and then set a flow of said alert response.
        // todo: continued: that alert response, gets observed by the view model which sets said alert data
        // todo: outcome from this calls: this call should set a flow<Int> of activeChallengesPostsIndex
        // todo: once that call is observed and it is set, we can then make the next call(:

        // todo:  to be continued
        // todo: Step 2 / call 2: Make a call on that selected index, of the current day of said challenge

        val zero = application.getString(R.string.zero)

        var activeChallengesPostsIndex = zero.toInt()
        var currentChallengeDay = zero
        var currentChallengeExpireDay = zero

        ref.child("$uid$ACTIVE_CHALLENGES$selectedIndex$ACTIVE_CHALLENGES_POSTS").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (activeChallengesPosts in snapshot.children) {
                        activeChallengesPostsIndex++
                    }
                } else {
                    activeChallengesPostsIndex = zero.toInt()
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

        // todo: figure out why we need this future ticket
        val isChallengeCompleted = newCurrentDay == currentChallengeExpireDay.toInt()

        if (newCurrentDay != 7) {

            writeActivePost.writePost(
                uid, selectedIndex, savedUserLastIndexOfProgress, currentPostsSize, ActivePost(
                    title = title,
                    description = body,
                    category = 0, // todo: set this to actual data future ticket
                    image = progressImageUrl ?: application.getString(R.string.empty_string),
                    currentDay = newCurrentDay.toString(),
                    username = username ?: application.getString(R.string.empty_string),
                    usernameUrl = usernameUrl ?: application.getString(R.string.empty_string),
                    dateChallengeExpired = currentChallengeExpireDay
                )
            )

            writeNewActiveChallengeImpl.writeCurrentDay(
                uid,
                selectedIndex.toString(),
                newCurrentDay
            )

            writeNewsFeedBanner(
                title = application.getString(R.string.a_new_post_has_been_updated_fpr_the),
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
        if (fetchSharedPreference.fetchChallengeModeSharedPreference()) { // if we enable challenge mode from debug, show this copy
            setShouldSetAlertAsUpdated(
                title = application.getString(R.string.progress_has_been_updated),
                message = application.getString(
                    R.string.congrats_you_have_posted_progress_on_the_x_press_ok_to_see_progress_on_the_news_feed, challengeTitle
                ),
                type = AlertType.REGULAR_OK_ALERT,
                shouldCloseAppAfterDone = false
            )
            setShouldShowDismissProgressAsUpdated()
            setShouldShowAlertAsUpdated()
        } else { // if we are not in challenge mode, then should possibly show new content in else statement
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