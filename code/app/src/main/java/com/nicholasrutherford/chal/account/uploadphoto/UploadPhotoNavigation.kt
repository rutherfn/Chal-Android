package com.nicholasrutherford.chal.account.uploadphoto

import android.content.Context
import com.nicholasrutherford.chal.Navigation

interface UploadPhotoNavigation : Navigation {
    fun createAccountAlert(title: String, message: String, uploadPhotoActivity: UploadPhotoActivity)
    fun showAcProgress(uploadPhotoActivity: UploadPhotoActivity)
    fun hideAcProgress()
    fun mainActivity(context: Context, uploadPhotoActivity: UploadPhotoActivity)
    fun openCamera(uploadPhotoActivity: UploadPhotoActivity)
    fun openGallery(uploadPhotoActivity: UploadPhotoActivity)
}