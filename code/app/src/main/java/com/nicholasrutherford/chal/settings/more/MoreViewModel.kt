package com.nicholasrutherford.chal.settings.more

import android.content.Context
import androidx.lifecycle.ViewModel
import com.nicholasrutherford.chal.firebase.read.ReadProfileDetailsFirebase

class MoreViewModel(appContext: Context) : ViewModel() {

    val viewState = MoreViewStateImpl()

    private val readProfileDetailsFirebase = ReadProfileDetailsFirebase(appContext)

    init {
        readProfileDetailsFirebase.getUserProfilePicture()?.let { userProfilePicture ->
            viewState.profilePictureDirectory = userProfilePicture
        }
    }

    inner class MoreViewStateImpl: MoreViewState{
        override var profilePictureDirectory = ""
    }

}