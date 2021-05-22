package com.nicholasrutherford.chal.profile.editprofile

import android.app.Application
import android.os.CountDownTimer
import androidx.lifecycle.ViewModel
import com.nicholasrutherford.chal.MainActivity
import com.nicholasrutherford.chal.firebase.read.ReadAccountFirebase
import com.nicholasrutherford.chal.firebase.write.WriteAccountFirebase
import com.nicholasrutherford.chal.navigationimpl.editmyprofile.EditProfileNavigationImpl
import javax.inject.Inject

class EditProfileViewModel @Inject constructor(application: Application, mainActivity: MainActivity): ViewModel() {

    val viewState = EditProfileViewStateImpl()
    val navigation = EditProfileNavigationImpl(application, mainActivity)

    var oldUserName: String? = ""

    private val readProfileDetailsFirebase = ReadAccountFirebase(application.applicationContext)
    private val writeAccountFirebase = WriteAccountFirebase(application.applicationContext)

    init {
        setupEditProfile()
    }

    private fun setupEditProfile() {
        viewState.editUsername = readProfileDetailsFirebase.getUsername()
        viewState.editFirstName = readProfileDetailsFirebase.getFirstName()
        viewState.editLastName = readProfileDetailsFirebase.getLastName()
        viewState.editBio = readProfileDetailsFirebase.getBio()
    }

    fun onEditProfileClicked(username: String, firstName: String, lastName: String, bio: String) {
        oldUserName = viewState.editUsername

        viewState.editUsername = username
        viewState.editFirstName = firstName
        viewState.editLastName = lastName
        viewState.editBio = bio

        writeAccountFirebase.updateUsername(viewState.editUsername)
        writeAccountFirebase.updateFirstName(viewState.editFirstName)
        writeAccountFirebase.updateLastName(viewState.editLastName)
        writeAccountFirebase.updateBio(viewState.editBio)

        navigation.showAcProgress()
        val timer = object : CountDownTimer(2500, 100) {

            override fun onTick(millisUntilFinished: Long) {}

            override fun onFinish() {
                navigation.hideAcProgress()
                navigation.showMyProfile()
            }
        }
        timer.start()
    }

    fun onBackButtonClicked() = navigation.pop()

    inner class EditProfileViewStateImpl(
        override var editUsername: String? = "",
        override var editFirstName: String? = "",
        override var editLastName: String? = "",
        override var editBio: String? = ""
    ) : EditProfileViewState
}
