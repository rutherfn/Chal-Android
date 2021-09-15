package com.nicholasrutherford.chal.create.account.uploadphoto

import android.app.Application
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.annotation.RequiresApi
import androidx.hilt.lifecycle.ViewModelInject
import com.nicholasrutherford.chal.shared.preference.fetch.FetchSharedPreference
import com.nicholasrutherford.chal.shared.preference.remove.RemoveSharedPreference
import com.nicholasrutherford.chal.ui.base_vm.BaseViewModel

@Suppress("MagicNumber")
const val PROFILE_PICTURE_SHARED_PREF = "profile-picture"

class UploadPhotoViewModel @ViewModelInject constructor(
    private val fetchSharedPreference: FetchSharedPreference,
    private val removeSharedPreference: RemoveSharedPreference,
    private val application: Application
) : BaseViewModel() {

    private var isPhotoReadyToBeUpdated = false
    private val buildSdkVersion = Build.VERSION.SDK_INT

    val viewState = UploadPhotoViewStateImpl()

    fun updateIsPhotoReadyToBeUpdated(isPhotoReadyToBeUpdated: Boolean) {
        this.isPhotoReadyToBeUpdated = isPhotoReadyToBeUpdated
    }

    fun onImageUpdate() {
        if (isPhotoReadyToBeUpdated) {
            val profilePictureDirectory =
                fetchSharedPreference.fetchProfilePictureDirectorySharedPreference(
                    PROFILE_PICTURE_SHARED_PREF)

            if (profilePictureDirectory.isNullOrEmpty()) {
                viewState.imageTakeAPhotoBitmap = null
            } else {
                val profileUri = Uri.parse(profilePictureDirectory)
                viewState.imageTakeAPhotoBitmap = getCapturedImage(profileUri)

                removeSharedPreference.removeProfilePictureDirectorySharedPreference(
                    PROFILE_PICTURE_SHARED_PREF)
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
    
    inner class UploadPhotoViewStateImpl : UploadPhotoViewState {
        override var imageTakeAPhotoBitmap: Bitmap? = null
    }
}