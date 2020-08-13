package com.nicholasrutherford.chal.viewstate

import com.nicholasrutherford.chal.data.responses.UserProfilePreview

interface OtherUserProfileViewState : ViewState {
    var backClicked: Boolean
    var userProfilePreview: ArrayList<UserProfilePreview>
}