package com.nicholasrutherford.chal.create.account.uploadphoto

import android.app.Application
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.annotation.RequiresApi
import androidx.hilt.lifecycle.ViewModelInject
import com.nicholasrutherford.chal.Network
import com.nicholasrutherford.chal.create.account.R
import com.nicholasrutherford.chal.data.account.info.AccountInfo
import com.nicholasrutherford.chal.data.account.info.ProfileInfo
import com.nicholasrutherford.chal.firebase.auth.ChalFirebaseAuth
import com.nicholasrutherford.chal.firebase.database.ChalFirebaseDatabase
import com.nicholasrutherford.chal.firebase.storage.ChalFirebaseStorage
import com.nicholasrutherford.chal.helper.constants.PROFILE_PICTURE_DIRECTORY_PREFERENCE
import com.nicholasrutherford.chal.shared.preference.fetch.FetchSharedPreference
import com.nicholasrutherford.chal.shared.preference.remove.RemoveSharedPreference
import com.nicholasrutherford.chal.ui.base_vm.BaseViewModel
import java.util.*

class UploadPhotoViewModel @ViewModelInject constructor(
    private val fetchSharedPreference: FetchSharedPreference,
    private val network: Network,
    private val removeSharedPreference: RemoveSharedPreference,
    private val navigation: UploadPhotoNavigation,
    private val firebaseAuth: ChalFirebaseAuth,
    private val firebaseStorage: ChalFirebaseStorage,
    private val firebaseDatabase: ChalFirebaseDatabase,
    private val application: Application
) : BaseViewModel() {

    private var isPhotoReadyToBeUpdated = false
    private val buildSdkVersion = Build.VERSION.SDK_INT

    private var email: String? = null
    private var password: String? = null
    private var username: String? = null

    var alertTitle = application.getString(R.string.empty_string)
    var alertMessage = application.getString(R.string.empty_string)

    private var profileUri: Uri? = null

    val viewState = UploadPhotoViewStateImpl()

    fun setParams(email: String?, password: String?, username: String?) {
        this.email = email
        this.password = password
        this.username = username
    }

    fun updateIsPhotoReadyToBeUpdated(isPhotoReadyToBeUpdated: Boolean) {
        this.isPhotoReadyToBeUpdated = isPhotoReadyToBeUpdated
    }

    fun onImageUpdate() {
        if (isPhotoReadyToBeUpdated) {
            val profilePictureDirectory =
                fetchSharedPreference.fetchProfilePictureDirectorySharedPreference(
                    PROFILE_PICTURE_DIRECTORY_PREFERENCE)

            if (profilePictureDirectory.isNullOrEmpty()) {
                viewState.imageTakeAPhotoBitmap = null
            } else {
                profileUri = Uri.parse(profilePictureDirectory)
                viewState.imageTakeAPhotoBitmap = getCapturedImage(profileUri as Uri)

                removeSharedPreference.removeProfilePictureDirectorySharedPreference(
                    PROFILE_PICTURE_DIRECTORY_PREFERENCE)
                setViewStateAsUpdated()
            }
            updateIsPhotoReadyToBeUpdated(false)
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

    @RequiresApi(Build.VERSION_CODES.P)
    private fun bitMapByImageDecoderSource(selectedPhotoUri: Uri): Bitmap {
        val source = ImageDecoder.createSource(application.contentResolver, selectedPhotoUri)
        return ImageDecoder.decodeBitmap(source)
    }

    private fun showErrorState(desc: String) {
        alertTitle = application.getString(R.string.error_cant_create_account)
        alertMessage = desc
        setShouldShowDismissProgressAsUpdated()
        setShouldShowAlertAsUpdated()
    }

    private fun showStockErrorState() {
        showErrorState(
            application.getString(R.string.issue_creating_your_account)
        )
    }

    fun onContinueClicked() {
        setShouldShowProgressAsUpdated()

        if (network.isConnected() && profileUri != null) {

            email?.let { validEmail ->
                password?.let { validPassword ->
                    firebaseAuth.auth.createUserWithEmailAndPassword(validEmail, validPassword)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                storeProfilePictureToFirebaseStorage()
                            }
                        }.addOnCompleteListener {
                            showStockErrorState()
                        }
                }
            }
        } else if (!network.isConnected()) {
            showErrorState(
                application.getString(R.string.error_no_internet_log_in)
            )
        } else {
            showErrorState(
                application.getString(R.string.error_no_image)
            )
        }
    }

    private fun storeProfilePictureToFirebaseStorage() {
        profileUri?.let { uri ->
            val fileName = UUID.randomUUID().toString()

            firebaseStorage.profilePictureImageReference(fileName)
                .putFile(uri)
                .addOnSuccessListener {
                    firebaseStorage.profilePictureImageReference(fileName)
                        .downloadUrl.addOnSuccessListener { profileImage ->
                            createDbUser(profileImage.toString())
                        }
                }
                .addOnFailureListener {
                    showStockErrorState()
                }
        }
    }

    private fun createDbUser(profileImageUrl: String) {
        val emptyString = application.getString(R.string.empty_string)
        val newUser = AccountInfo(
            id = 0, // TODO update later
            profileInfo = username?.let { name -> ProfileInfo(username = name, profileImage = profileImageUrl) },
            username = username,
            email = email,
            profileImage = profileImageUrl,
            firstName = emptyString,
            lastName = emptyString,
            bio = emptyString,
            age = 0, // TODO update later
            challengeBannerType = 0, // TODO update later
            friends = null,
            activeChallenges = null
        )

        firebaseDatabase.userDatabaseReference(firebaseAuth.uid ?: emptyString)
            .setValue(newUser)
            .addOnCompleteListener {
                firebaseAuth.sendEmailVerification()
                setShouldShowDismissProgressAsUpdated()
                navigation.showNewsFeed()
            }
            .addOnFailureListener {
                showStockErrorState()
            }
    }
    
    inner class UploadPhotoViewStateImpl : UploadPhotoViewState {
        override var imageTakeAPhotoBitmap: Bitmap? = null
    }
}