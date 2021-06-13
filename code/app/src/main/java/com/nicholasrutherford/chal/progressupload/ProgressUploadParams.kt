package com.nicholasrutherford.chal.progressupload

import android.net.Uri

data class ProgressUploadParams(
    var isUpdate: Boolean,
    val title: String?,
    val caption: String?,
    var photoUri: Uri?
)