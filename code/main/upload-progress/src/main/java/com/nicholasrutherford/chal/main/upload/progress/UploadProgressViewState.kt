package com.nicholasrutherford.chal.main.upload.progress

import android.graphics.Bitmap

interface UploadProgressViewState {
    var toolbarTitle: String
    var title: String
    var body: String
    var category: String
    var image: String
    var imageTakeAPhotoBitmap: Bitmap?
}