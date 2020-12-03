package com.nicholasrutherford.chal.settings.more

import android.content.Context
import android.os.CountDownTimer
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nicholasrutherford.chal.activitys.MainActivity
import com.nicholasrutherford.chal.firebase.logUserOut
import com.nicholasrutherford.chal.firebase.read.ReadAccountFirebase
import com.nicholasrutherford.chal.navigationimpl.MoreNavigationImpl

const val MILLIS_IN_FUTURE = 3000
const val COUNT_DOWN_INTERVAL = 100

class MoreViewModel(private val mainActivity: MainActivity, private val appContext: Context,
                    private val fragmentManager: FragmentManager, private val container: Int,
                    private val bottomNavigationView: BottomNavigationView) : ViewModel() {

    val viewState = MoreViewStateImpl()
    val navigation = MoreNavigationImpl()

    private val readProfileDetailsFirebase = ReadAccountFirebase(appContext)

    init {
        navigation.showAcProgress(mainActivity)
        readProfileDetailsFirebase.getUserProfilePicture()?.let { userProfilePicture ->
            viewState.profilePictureDirectory = userProfilePicture
        }
        navigation.hideAcProgress()
    }

    fun onSignOutAccountClicked() {
        navigation.showAcProgress(mainActivity)
        val timer = object : CountDownTimer(MILLIS_IN_FUTURE.toLong(), COUNT_DOWN_INTERVAL.toLong()) {
            override fun onTick(millisUntilFinished: Long) {}

            override fun onFinish() {
                logUserOut()

                navigation.hideAcProgress()
                navigation.login(mainActivity, appContext)
            }

        }
        timer.start()
    }

    fun onMyProfileClicked() {
        navigation.showMyProfile(mainActivity, appContext, true, fragmentManager, container, bottomNavigationView )
    }

    inner class MoreViewStateImpl: MoreViewState {
        override var profilePictureDirectory = ""
    }

}