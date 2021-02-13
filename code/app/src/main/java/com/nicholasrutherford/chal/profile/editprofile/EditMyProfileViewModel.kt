package com.nicholasrutherford.chal.profile.editprofile

import android.content.Context
import android.os.CountDownTimer
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nicholasrutherford.chal.ChalRoom
import com.nicholasrutherford.chal.MainActivity
import com.nicholasrutherford.chal.firebase.read.ReadAccountFirebase
import com.nicholasrutherford.chal.firebase.write.WriteAccountFirebase
import com.nicholasrutherford.chal.navigationimpl.editmyprofile.EditMyProfileNavigationImpl
import com.nicholasrutherford.chal.room.entity.user.UserEntity
import kotlinx.coroutines.launch

class EditMyProfileViewModel(
    private val mainActivity: MainActivity,
    private val appContext: Context,
    private val fragmentManager: FragmentManager,
    private val container: Int,
    private val bottomNavigationView: BottomNavigationView
) : ViewModel() {

    val viewState = EditMyProfileViewStateImpl()
    val navigation = EditMyProfileNavigationImpl()

    var oldUserName: String? = ""

    private val readProfileDetailsFirebase = ReadAccountFirebase(appContext)
    private val writeAccountFirebase = WriteAccountFirebase(appContext)

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

        editProfileDb()

        navigation.showAcProgress(mainActivity)
        val timer = object : CountDownTimer(2500, 100) {

            override fun onTick(millisUntilFinished: Long) {}

            override fun onFinish() {
                navigation.hideAcProgress()
                navigation.showMyProfile(mainActivity, appContext, true, fragmentManager, container, bottomNavigationView)

                Toast.makeText(
                    appContext, "Edited your account info",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        timer.start()
    }

    fun editProfileDb() {
        val chalRoom = ChalRoom(mainActivity.application)
        viewModelScope.launch {
            oldUserName?.let { username ->
                val user = chalRoom.userRepository.getUser(username)

                chalRoom.userRepository.updateUser(
                    UserEntity(
                        id = user.id,
                        username = viewState.editUsername ?: "",
                        email = user.email,
                        profileImageUrl = user.profileImageUrl,
                        password = user.password,
                        firstName = viewState.editFirstName ?: "",
                        lastName = viewState.editLastName ?: "",
                        bio = viewState.editBio ?: "",
                        age = user.age,
                        currentFriends = user.currentFriends,
                        activeChallengeEntities = user.activeChallengeEntities
                    )
                )
            }
        }
    }

    inner class EditMyProfileViewStateImpl(
        override var editUsername: String? = "",
        override var editFirstName: String? = "",
        override var editLastName: String? = "",
        override var editBio: String? = ""
    ) : EditMyProfileViewState
}
