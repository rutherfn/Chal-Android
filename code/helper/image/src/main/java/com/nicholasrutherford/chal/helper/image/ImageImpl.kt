package com.nicholasrutherford.chal.helper.image

import android.app.Application
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.annotation.RequiresApi
import javax.inject.Inject

@Suppress("MagicNumber")
const val MIND_SDK_VERSION = 28

class ImageImpl @Inject constructor(private val application: Application) : Image {

    private val buildSdkVersion = Build.VERSION.SDK_INT

    @RequiresApi(Build.VERSION_CODES.P)
    // todo: refactored function down the line
    override fun getCapturedImage(selectedPhotoUri: Uri): Bitmap? {
        return when {
            buildSdkVersion < MIND_SDK_VERSION-> MediaStore.Images.Media.getBitmap(
                application.contentResolver,
                selectedPhotoUri
            )
            buildSdkVersion > MIND_SDK_VERSION -> {
                bitMapByImageDecoderSource(selectedPhotoUri)
            }
            else -> {
                return null
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.P)
    // todo: refactored function down the line
    private fun bitMapByImageDecoderSource(selectedPhotoUri: Uri): Bitmap {
        val source = ImageDecoder.createSource(application.contentResolver, selectedPhotoUri)
        return ImageDecoder.decodeBitmap(source)
    }
}