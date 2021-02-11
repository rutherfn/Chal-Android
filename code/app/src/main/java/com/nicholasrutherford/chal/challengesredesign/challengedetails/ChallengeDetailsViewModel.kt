package com.nicholasrutherford.chal.challengesredesign.challengedetails

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.nicholasrutherford.chal.MainActivity
import com.nicholasrutherford.chal.firebase.ACTIVE_CHALLENGES
import com.nicholasrutherford.chal.firebase.NUMBER_OF_DAYS_OF_CHALLENGE
import com.nicholasrutherford.chal.firebase.USERS
import com.nicholasrutherford.chal.firebase.read.ReadAccountFirebase
import com.nicholasrutherford.chal.firebase.write.activechallenge.WriteActiveChallengeFirebase
import com.nicholasrutherford.chal.navigationimpl.challengeredesign.ChallengeDetailsNavigationImpl
import java.text.SimpleDateFormat
import java.util.*

const val STARTER_INDEX = "0"

class ChallengeDetailsViewModel(
    private val mainActivity: MainActivity,
    private val appContext: Context,
    private val fragmentManager: FragmentManager,
    private val container: Int,
    private val bottomNavigationView: BottomNavigationView
) : ViewModel() {

    val viewState = ChallengeDetailsViewStateImpl()
    private val readProfileDetailsFirebase = ReadAccountFirebase(appContext)
    private val writeActiveChallengesFirebase = WriteActiveChallengeFirebase()

    val uid = FirebaseAuth.getInstance().uid ?: ""

    private var navigation = ChallengeDetailsNavigationImpl()
    val ref = FirebaseDatabase.getInstance().getReference(USERS)

    init {
        initViewStateOnLoad()
    }

    fun initViewStateOnLoad() {
        readProfileDetailsFirebase.getUsername()?.let { userName ->
            viewState.toolbarName = userName
        }
        readProfileDetailsFirebase.getUserProfilePicture()?.let { profilePicture ->
            viewState.toolbarImage = profilePicture
        }
    }

    fun getDaysAgo(daysAgo: Int): Date {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, +daysAgo)

        return calendar.time
    }

    fun onJoinChallengeClicked() {
        ref.child("$uid$ACTIVE_CHALLENGES$STARTER_INDEX/$NUMBER_OF_DAYS_OF_CHALLENGE").addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                println("error")
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val snapshot = snapshot.value.toString()

                    if (snapshot.toInt() == 0) {
                        enrollUserIntoChallenge()
                    } else {
                        Toast.makeText(
                            appContext, "Your already in this challenge!",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                } else {
                    enrollUserIntoChallenge()
                }
            }
        })
    }

    fun enrollUserIntoChallenge() {
        val simpleDateFormat = SimpleDateFormat("EEE", Locale.ENGLISH)
        val date = Date()

        writeActiveChallengesFirebase.writeCategoryName(STARTER_INDEX, "Health & Fitness")
        writeActiveChallengesFirebase.writeBio(STARTER_INDEX, "lorem ipsum dolor sit amet, consetetur spadiscing elitr, sed diam noumy elfrmod tempert invidunt vu volumpant. At vero eas et accusam eltistro duo doloroes et eu rebum")
        writeActiveChallengesFirebase.writeName(STARTER_INDEX, "7 Days Of Meditation")
        writeActiveChallengesFirebase.writeNumberOfDaysInChallenge(STARTER_INDEX, 7)
        writeActiveChallengesFirebase.writeTimeChallengeExpire(STARTER_INDEX, getDaysAgo(7))
        writeActiveChallengesFirebase.writeUserCurrentDay(STARTER_INDEX, 0)

        navigation.hideAcProgress()

        Toast.makeText(
            appContext, "Enrolled in Challenge!",
            Toast.LENGTH_LONG
        ).show()
    }

    inner class ChallengeDetailsViewStateImpl : ChallengeDetailsViewState {
        override var toolbarName: String = ""
        override var toolbarImage: String = ""
    }
}
