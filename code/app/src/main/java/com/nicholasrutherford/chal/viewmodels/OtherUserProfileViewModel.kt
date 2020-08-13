package com.nicholasrutherford.chal.viewmodels

import androidx.lifecycle.ViewModel
import com.nicholasrutherford.chal.data.responses.UserProfilePreview
import com.nicholasrutherford.chal.viewstate.OtherUserProfileViewState

class OtherUserProfileViewModel() : ViewModel() {

 //   private val otherUserProfileNavigationImpl = OtherUserProfileNavigationImpl()

    var viewState = OtherUserProfileViewStateImpl()

    fun sampleDataForOtherUserProfilePreview(): ArrayList<UserProfilePreview> {
        val userProfileSampleArray = ArrayList<UserProfilePreview>()
        val sampleData = UserProfilePreview("https://pbs.twimg.com/profile_images/971963353106010113/HSOt7Yvd_400x400.jpg", "nick.rutherfortd","Nick", "Rutherford", "20,", "Milwaukee.", "WI", "Happy to be here to work on my fitness! One step at a time!")
        userProfileSampleArray.add(sampleData)
        return userProfileSampleArray
    }

    fun onBackClicked() {
        viewState.backClicked = true
 //       otherUserProfileNavigationImpl.showSearchPeopleFragment(viewState.backClicked, fragmentManager, container, searchPeopleFragment)
    }

    inner class OtherUserProfileViewStateImpl: OtherUserProfileViewState {
        override var backClicked = false
        override var userProfilePreview: ArrayList<UserProfilePreview> = sampleDataForOtherUserProfilePreview()
    }
}