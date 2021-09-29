package com.nicholasrutherford.chal.create.account.uploadphoto

import android.graphics.Bitmap

interface UploadPhotoViewState {
    var toolbarText: String
    var imageTakeAPhotoBitmap: Bitmap?
}