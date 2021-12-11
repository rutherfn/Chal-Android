package com.nicholasrutherford.chal.helper.image

import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.provider.MediaStore

interface Image {
    fun getCapturedImage(selectedPhotoUri: Uri): Bitmap?
}