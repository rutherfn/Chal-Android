package com.nicholasrutherford.chal.settings.more

import android.content.Context
import android.os.CountDownTimer
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.nicholasrutherford.chal.activitys.MainActivity
import com.nicholasrutherford.chal.data.realdata.ActiveChallengesPosts
import com.nicholasrutherford.chal.firebase.USERS
import com.nicholasrutherford.chal.firebase.logUserOut
import com.nicholasrutherford.chal.firebase.read.ReadProfileDetailsFirebase
import com.nicholasrutherford.chal.navigationimpl.MoreNavigationImpl

const val MILLIS_IN_FUTURE = 3000
const val COUNT_DOWN_INTERVAL = 100

class MoreViewModel(private val mainActivity: MainActivity, private val appContext: Context) : ViewModel() {

    val viewState = MoreViewStateImpl()
    val navigation = MoreNavigationImpl()

    val uid = FirebaseAuth.getInstance().uid  ?: ""
    val ref = FirebaseDatabase.getInstance().getReference(USERS)

    private val readProfileDetailsFirebase = ReadProfileDetailsFirebase(appContext)

    init {
        readProfileDetailsFirebase.getUserProfilePicture()?.let { userProfilePicture ->
            viewState.profilePictureDirectory = userProfilePicture
        }
        test()
    }

    private fun test() {
        ref.child("$uid/activeChallenges").addValueEventListener(object: ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                println("error")
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val activeChallengesPostList: ArrayList<ActiveChallengesPosts> = ArrayList()
                if (snapshot.exists()) {
                    for (test in snapshot.children) {
                        val test2 = test.getValue(ActiveChallengesPosts::class.java)
                        activeChallengesPostList.add(test2!!)
                    }
                    println(activeChallengesPostList.get(0).currentDay)

                } else {
                    println("does not exist")
                }
            }

        })
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

    inner class MoreViewStateImpl: MoreViewState{
        override var profilePictureDirectory = ""
    }

}