package com.nicholasrutherford.chal.profile.profiles

import android.content.Context
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.activitys.MainActivity
import com.nicholasrutherford.chal.firebase.read.ReadAccountFirebase
import com.nicholasrutherford.chal.navigationimpl.MyProfileNavigationImpl

class MyProfileViewModel(private val mainActivity: MainActivity, private val appContext: Context,
                         private val fragmentManager: FragmentManager, private val container: Int,
                         private val bottomNavigationView: BottomNavigationView
) : ViewModel() {

    val viewState = MyProfileViewStateImpl()
    val navigation = MyProfileNavigationImpl()

    private val readProfiledDetailsFirebase = ReadAccountFirebase(appContext)

    init {
        setupProfilePage()
    }

    private fun setupProfilePage() {
        navigation.showAcProgress(mainActivity)

        viewState.age = readProfiledDetailsFirebase.getAge()

        viewState.description = readProfiledDetailsFirebase.getBio()
        if (viewState.description?.isEmpty()!!) {
            viewState.description = appContext.getString(R.string.click_here_to_add_a_description)
        }

        viewState.username = readProfiledDetailsFirebase.getUsername()
        viewState.profileImage = readProfiledDetailsFirebase.getUserProfilePicture()

        navigation.hideAcProgress()
    }

    fun onEditMyProfileClicked() = navigation.showEditMyProfile(mainActivity, appContext, true, fragmentManager, container, bottomNavigationView )


    inner class MyProfileViewStateImpl: MyProfileViewState {
        override var age: Int? = 0
        override var description: String? = ""
        override var username: String? = ""
        override var profileImage: String? = ""
    }
}