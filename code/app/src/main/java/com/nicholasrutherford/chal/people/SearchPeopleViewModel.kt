package com.nicholasrutherford.chal.people

import android.app.Application
import androidx.lifecycle.ViewModel
import com.nicholasrutherford.chal.firebase.read.ReadAccountFirebase
import javax.inject.Inject

class SearchPeopleViewModel @Inject constructor(
    application: Application
    ) : ViewModel() {

    val viewState = SearchPeopleViewStateImpl()

    private val readProfileDetailsFirebase = ReadAccountFirebase((application.applicationContext))

    init {
        initViewStateOnLoad()
    }

    private fun initViewStateOnLoad() {
        readProfileDetailsFirebase.getUsername()?.let { username ->
            viewState.toolbarName = username
        }
        readProfileDetailsFirebase.getUserProfilePicture()?.let { urlProfilePicture ->
            viewState.toolbarUrl = urlProfilePicture
        }
    }

    inner class SearchPeopleViewStateImpl: SearchPeopleViewState {
        override var toolbarName: String = ""
        override var toolbarUrl: String = ""
        override var profileName: String = ""
        override var profileUrl: String = ""
    }
}