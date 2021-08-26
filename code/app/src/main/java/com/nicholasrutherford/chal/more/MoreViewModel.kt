package com.nicholasrutherford.chal.more

import android.app.Application
import android.os.CountDownTimer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.nicholasrutherford.chal.main.MainActivity
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
import javax.inject.Inject

@Suppress("MagicNumber")
const val MILLIS_IN_FUTURE = 3000
const val COUNT_DOWN_INTERVAL = 100

class MoreViewModel @Inject constructor(
    private val application: Application,
    private val navigation: MoreNavigationImpl,
    mainActivity: MainActivity
) : ViewModel() {

    private val ref = FirebaseDatabase.getInstance().getReference(USERS)
    private val uid = FirebaseAuth.getInstance().uid ?: ""

    val viewState = MoreViewStateImpl()

    private val readProfileDetailsFirebase = ReadAccountFirebase(application.applicationContext)

    private val _isUserEnrolledInChallenge = MutableStateFlow(false)
    private val isUserEnrolledInChallenge: StateFlow<Boolean> = _isUserEnrolledInChallenge

    private var userEnrolledInChallenge = false

    init {
        navigation.showAcProgress()

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
        navigation.showAcProgress()
        val timer = object : CountDownTimer(MILLIS_IN_FUTURE.toLong(), COUNT_DOWN_INTERVAL.toLong()) {
            override fun onTick(millisUntilFinished: Long) {}

            override fun onFinish() {
                logUserOut()

                navigation.hideAcProgress()
                navigation.login()
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
            navigation.showProgress()
        } else {
            application.applicationContext?.let { context ->
            viewState.alertTitle = context.getString(R.string.not_enrolled_in_challenge)
            viewState.alertMessage = context.getString(R.string.not_enrolled_in_challenge_message)
            navigation.showAlert(viewState.alertTitle, viewState.alertMessage)
            }
        }
    }

    fun onReportBugClicked() = navigation.reportBug()

    fun onMyProfileClicked() = navigation.showMyProfile()

    inner class MoreViewStateImpl : MoreViewState {
        override var alertTitle: String = ""
        override var alertMessage = ""
        override var profilePictureDirectory = ""
    }
}
