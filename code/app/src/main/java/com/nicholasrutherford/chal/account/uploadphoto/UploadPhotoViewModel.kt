package com.nicholasrutherford.chal.account.uploadphoto

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.data.realdata.Account
import com.nicholasrutherford.chal.data.realdata.ProfileInfo
import com.nicholasrutherford.chal.firebase.bindUserId
import com.nicholasrutherford.chal.firebase.bindUserImageFile
import com.nicholasrutherford.chal.firebase.read.ReadAccountFirebase
import com.nicholasrutherford.chal.navigationimpl.uploadphoto.UploadPhotoNavigationImpl
import kotlinx.coroutines.launch
import java.util.*

class UploadPhotoViewModel(private val uploadPhotoActivity: UploadPhotoActivity, private val appContext: Context) : ViewModel() {

    val viewState = UploadPhotoViewStateImpl()
    private val navigation = UploadPhotoNavigationImpl()

    private var profileImageUrl: String? = ""

    fun retrieveAccountInfoFromPreviousActivity(userEmail: String?, userPassword: String?, userUsername: String?) {
        viewState.email = userEmail
        viewState.password = userPassword
        viewState.username = userUsername
    }

    fun openCamera() = navigation.openCamera(uploadPhotoActivity)

    fun openGallery() = navigation.openGallery(uploadPhotoActivity)

    fun sendEmailVerification() {
        val user = FirebaseAuth.getInstance().currentUser
        user?.sendEmailVerification()
    }

    fun initStarterSavedPrefs() {
        val readAccountFirebase = ReadAccountFirebase(appContext)

        readAccountFirebase.getAge()
        readAccountFirebase.getBio()
        readAccountFirebase.getEmail()
        readAccountFirebase.getFirstName()
        readAccountFirebase.getId()
        readAccountFirebase.getLastName()
        readAccountFirebase.getPassword()
        readAccountFirebase.getUserProfilePicture()
        readAccountFirebase.getUsername()
    }

    fun createUser(photoUri: Uri?) {
        navigation.showAcProgress(uploadPhotoActivity)

        viewState.email?.let { usersEmail ->
            viewState.password?.let { usersPassword ->
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(usersEmail, usersPassword)
                    .addOnCompleteListener(uploadPhotoActivity) { task ->
                    if (task.isSuccessful) {
                        uploadUserPhoto(photoUri)
                    }
                    }.addOnFailureListener {
                        navigation.hideAcProgress()
                        navigation.createAccountAlert("Issue creating your account. Please try again", uploadPhotoActivity.getString(
                            R.string.error_cant_create_account), uploadPhotoActivity)
                    }
            }
        }
    }

    fun uploadUserPhoto(photoUri: Uri?) {
        photoUri?.let { selectedPhotoUri ->
            val fileName = UUID.randomUUID().toString()
            val ref = FirebaseStorage.getInstance().getReference(bindUserImageFile(fileName))

            ref.putFile(selectedPhotoUri)
                .addOnSuccessListener {
                    ref.downloadUrl.addOnSuccessListener { profileImage ->
                        profileImageUrl = profileImage.toString()
                        createDbUser()
                    }
                }
                .addOnFailureListener {
                    navigation.hideAcProgress()
                    navigation.createAccountAlert("Issue creating your account. Please try again", uploadPhotoActivity.getString(
                        R.string.error_cant_create_account), uploadPhotoActivity)
                }
        }
    }

    fun createDbUser() {
        val uid = FirebaseAuth.getInstance().uid ?: ""
        val ref = FirebaseDatabase.getInstance().getReference(bindUserId(uid))

        val newUser = Account(
            id = 0,
            profileInfo = profileImageUrl?.let { viewState.username?.let { it1 -> ProfileInfo(username = it1, profileImage = it) } },
            username = viewState.username,
            email = viewState.email,
            profileImage = profileImageUrl,
            password = viewState.password,
            firstName = "",
            lastName = "",
            bio = "",
            age = 0,
            friends = null,
            activeChallenges = null
        )

        ref.setValue(newUser)
            .addOnSuccessListener {
                sendEmailVerification()
                initStarterSavedPrefs()

                navigation.hideAcProgress()
                navigation.mainActivity(appContext, uploadPhotoActivity)
            }
            .addOnFailureListener {
                navigation.hideAcProgress()
                navigation.createAccountAlert("Issue creating your account. Please try again", uploadPhotoActivity.getString(
                    R.string.error_cant_create_account), uploadPhotoActivity)
            }
    }

    class UploadPhotoViewStateImpl : UploadPhotoViewState {
        override var email: String? = ""
        override var password: String? = ""
        override var username: String? = ""
    }
}
