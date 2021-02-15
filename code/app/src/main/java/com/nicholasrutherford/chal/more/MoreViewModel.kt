package com.nicholasrutherford.chal.more

import android.content.Context
import android.os.CountDownTimer
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.nicholasrutherford.chal.MainActivity
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.firebase.ACTIVE_CHALLENGES
import com.nicholasrutherford.chal.firebase.USERS
import com.nicholasrutherford.chal.firebase.logUserOut
import com.nicholasrutherford.chal.firebase.read.ReadAccountFirebase
import com.nicholasrutherford.chal.navigationimpl.more.MoreNavigationImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

const val MILLIS_IN_FUTURE = 3000
const val COUNT_DOWN_INTERVAL = 100

class MoreViewModel(
    private val mainActivity: MainActivity,
    private val appContext: Context,
    private val fragmentManager: FragmentManager,
    private val container: Int,
    private val bottomNavigationView: BottomNavigationView
) : ViewModel() {

    private val ref = FirebaseDatabase.getInstance().getReference(USERS)
    private val uid = FirebaseAuth.getInstance().uid ?: ""

    val viewState = MoreViewStateImpl()
    val navigation = MoreNavigationImpl()

    private val readProfileDetailsFirebase = ReadAccountFirebase(appContext)

    private val _isUserEnrolledInChallenge = MutableStateFlow(false)
    private val isUserEnrolledInChallenge: StateFlow<Boolean> = _isUserEnrolledInChallenge

    private var userEnrolledInChallenge = false

    init {
        navigation.showAcProgress(mainActivity)

        checkIfUserIsEnrolledInAChallenge()

        readProfileDetailsFirebase.getUserProfilePicture()?.let { userProfilePicture ->
            viewState.profilePictureDirectory = userProfilePicture
        }
        navigation.hideAcProgress()

        viewModelScope.launch {
            isUserEnrolledInChallenge.collect { isEnrolled ->
                userEnrolledInChallenge = isEnrolled
            }
        }
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

    private fun checkIfUserIsEnrolledInAChallenge() {
        ref.child("$uid$ACTIVE_CHALLENGES")
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {}

                override fun onDataChange(snapshot: DataSnapshot) {
                    _isUserEnrolledInChallenge.value = snapshot.exists()
                }
            })
    }

    fun onUploadProgressClicked() {
        if (userEnrolledInChallenge) {
            navigation.showProgress(mainActivity, appContext)
        } else {
            val title = mainActivity.getString(R.string.not_enrolled_in_challenge)
            val message = mainActivity.getString(R.string.not_enrolled_in_challenge_message)
            navigation.showAlert(title, message, mainActivity)
        }
    }

    fun onReportBugClicked() = navigation.reportBug(mainActivity, appContext, bottomNavigationView)

    fun onMyProfileClicked() = navigation.showMyProfile(mainActivity, appContext, true, fragmentManager, container, bottomNavigationView)

    inner class MoreViewStateImpl : MoreViewState {
        override var profilePictureDirectory = ""
    }
}
