package com.nicholasrutherford.chal.challengesredesign.challengedetails

import android.content.Context
import android.os.CountDownTimer
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.nicholasrutherford.chal.data.realdata.Challenges
import com.nicholasrutherford.chal.data.responses.CurrentActiveChallengesResponse
import com.nicholasrutherford.chal.firebase.ACTIVE_CHALLENGES
import com.nicholasrutherford.chal.firebase.USERS
import com.nicholasrutherford.chal.firebase.read.ReadAccountFirebase
import com.nicholasrutherford.chal.firebase.write.activechallenge.WriteActiveChallengeFirebase
import com.nicholasrutherford.chal.navigationimpl.challengeredesign.ChallengeDetailsNavigationImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.Calendar
import java.util.Date

const val STARTER_INDEX = "0"

class ChallengeDetailsViewModel(
    appContext: Context,
    private val challenge: Challenges,
    private val fragmentActivity: FragmentActivity
) : ViewModel() {

    val viewState = ChallengeDetailsViewStateImpl()
    private val readProfileDetailsFirebase = ReadAccountFirebase(appContext)
    private val writeActiveChallengesFirebase = WriteActiveChallengeFirebase()

    private val uid = FirebaseAuth.getInstance().uid ?: ""

    private var navigation = ChallengeDetailsNavigationImpl()
    val ref = FirebaseDatabase.getInstance().getReference(USERS)

    private var isAbleToEnroll: Boolean = true

    private val _activeChallengesResponse = MutableStateFlow(listOf<CurrentActiveChallengesResponse>())
    val activeChallengesResponse: StateFlow<List<CurrentActiveChallengesResponse>> = _activeChallengesResponse

    init {
        initViewStateOnLoad()
        fetchActiveChallenges()
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

    fun attemptToEnrollUserIntoChallenge(joinChallenge: JoinChallenge) {
        if (joinChallenge.isAbleToEnroll) {
            enrollUserIntoNewChallenge(joinChallenge.size.toString())
        } else {
            navigation.showAlert(
                "It looks like your enrolled already in the ${challenge.duration} Day ${challenge.title}! Start posting progress on your challenge now! ",
                challenge.title, fragmentActivity
            )
        }
    }

    fun onJoinChallengeClicked(currentActiveChallengesResponseList: List<CurrentActiveChallengesResponse>?) {
        navigation.showAcProgress(fragmentActivity)
        val timer = object : CountDownTimer(2000, 100) {

            override fun onTick(millisUntilFinished: Long) {}

            override fun onFinish() {
                navigation.hideAcProgress()

                currentActiveChallengesResponseList?.let { test ->
                    test.forEach { activeChallenge ->
                        if (activeChallenge.name.contains(challenge.title)) {
                            isAbleToEnroll = false
                        }
                    }
                }
                attemptToEnrollUserIntoChallenge(JoinChallenge(isAbleToEnroll, currentActiveChallengesResponseList?.size ?: 0))
            }
        }
        timer.start()
    }

    fun fetchActiveChallenges() {
        ref.child("$uid$ACTIVE_CHALLENGES")
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {}

                override fun onDataChange(snapshot: DataSnapshot) {
                    val activeChallengesResponseList = arrayListOf<CurrentActiveChallengesResponse>()
                    for (activeChallenges in snapshot.children) {
                        activeChallenges.getValue(CurrentActiveChallengesResponse::class.java).let {
                            it?.let { activeChallenge ->
                                activeChallengesResponseList.add(activeChallenge)
                            }
                        }
                    }
                    _activeChallengesResponse.value = activeChallengesResponseList
                }
            })
    }

    fun enrollUserIntoNewChallenge(starterIndex: String) {
        writeActiveChallengesFirebase.writeCategoryName(starterIndex, challenge.category)
        writeActiveChallengesFirebase.writeBio(starterIndex, challenge.desc)
        writeActiveChallengesFirebase.writeName(starterIndex, challenge.title)
        writeActiveChallengesFirebase.writeNumberOfDaysInChallenge(starterIndex, challenge.duration)
        writeActiveChallengesFirebase.writeTimeChallengeExpire(starterIndex, getDaysAgo(challenge.duration))
        writeActiveChallengesFirebase.writeUserCurrentDay(starterIndex, dayJoiningChallenge())

        navigation.showAlert(
            "You have just joined the ${challenge.duration} Day ${challenge.title}! Get started by posting progress.",
            challenge.title, fragmentActivity
        )
    }

    private fun dayJoiningChallenge(): Int {
        val calendar = Calendar.getInstance()
        val day = calendar[Calendar.DAY_OF_WEEK]

        return when (day) {
            Calendar.MONDAY -> { 0 }
            Calendar.TUESDAY -> { 1 }
            Calendar.WEDNESDAY -> { 2 }
            Calendar.THURSDAY -> { 3 }
            Calendar.FRIDAY -> { 4 }
            Calendar.SATURDAY -> { 5 }
            else -> { 6 }
        }
    }

    inner class ChallengeDetailsViewStateImpl : ChallengeDetailsViewState {
        override var toolbarName: String = ""
        override var toolbarImage: String = ""
    }

    data class JoinChallenge(
        val isAbleToEnroll: Boolean,
        val size: Int
    )
}
