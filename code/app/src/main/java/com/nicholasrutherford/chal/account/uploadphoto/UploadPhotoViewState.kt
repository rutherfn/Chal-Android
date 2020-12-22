package com.nicholasrutherford.chal.account.uploadphoto

import com.nicholasrutherford.chal.ViewState

interface UploadPhotoViewState : ViewState {
    var username: String?
    var email: String?
    var password: String?
}