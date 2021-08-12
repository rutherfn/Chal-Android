package com.nicholasrutherford.chal.profile.editprofile

import android.app.Application
import android.os.CountDownTimer
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.nicholasrutherford.chal.data.firebase.AccountInfo
import com.nicholasrutherford.chal.main.MainActivity
import com.nicholasrutherford.chal.firebase.read.ReadAccountFirebase
import com.nicholasrutherford.chal.firebase.write.accountinfo.WriteAccountInfoImpl
import com.nicholasrutherford.chal.navigationimpl.editmyprofile.EditProfileNavigationImpl
import javax.inject.Inject

class EditProfileViewModel @Inject constructor(application: Application, mainActivity: MainActivity): ViewModel() {

    val viewState = EditProfileViewStateImpl()
    val navigation = EditProfileNavigationImpl(application, mainActivity)

    private var oldUserName: String? = ""

    private val readProfileDetailsFirebase = ReadAccountFirebase(application.applicationContext)
    private val writeAccountInfo = WriteAccountInfoImpl()

    val uid = FirebaseAuth.getInstance().uid ?: ""

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

        writeAccountInfo.updateAccountInfo(uid = uid, accountInfo = AccountInfo(
            username = username,
            firstName = firstName,
            lastName = lastName,
            bio = bio,
            age = 0
        ))

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
