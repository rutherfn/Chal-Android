package com.nicholasrutherford.chal.profile.profiles

import android.app.Application
import androidx.lifecycle.ViewModel
import com.nicholasrutherford.chal.MainActivity
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.firebase.read.ReadAccountFirebase
import com.nicholasrutherford.chal.navigationimpl.myprofile.MyProfileNavigationImpl
import javax.inject.Inject

class MyProfileViewModel @Inject constructor(private val application: Application, mainActivity: MainActivity) : ViewModel() {

    val viewState = MyProfileViewStateImpl()
    val navigation =
        MyProfileNavigationImpl(application, mainActivity)

    private val readProfiledDetailsFirebase = ReadAccountFirebase(application.applicationContext)

    init {
        setupProfilePage()
        onMyChallengesTabClicked()
    }

    fun onMyChallengesTabClicked() {
        viewState.myChallengesTabActive = true
        viewState.myFriendsTabActive = false
    }

    fun onMyFriendsTabClicked() {
        viewState.myFriendsTabActive = true
        viewState.myChallengesTabActive = false
    }

    private fun setupProfilePage() {
        navigation.showAcProgress()

        viewState.age = readProfiledDetailsFirebase.getAge()

        if (readProfiledDetailsFirebase.getBio()?.isEmpty() == true) {
            viewState.description = application.applicationContext.getString(R.string.click_here_to_add_a_description)
        } else {
            viewState.description = readProfiledDetailsFirebase.getBio()
        }

        viewState.username = readProfiledDetailsFirebase.getUsername()
        viewState.profileImage = readProfiledDetailsFirebase.getUserProfilePicture()
    }

    fun onEditMyProfileClicked() = navigation.showEditMyProfile()

    inner class MyProfileViewStateImpl : MyProfileViewState {
        override var age: Int? = 0
        override var description: String? = ""
        override var username: String? = ""
        override var profileImage: String? = ""
        override var myChallengesTabActive = false
        override var myFriendsTabActive = false
    }
}
