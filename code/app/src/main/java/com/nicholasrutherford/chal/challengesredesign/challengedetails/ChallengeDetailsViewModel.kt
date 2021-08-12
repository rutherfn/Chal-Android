package com.nicholasrutherford.chal.challengesredesign.challengedetails

import android.content.Context
import android.os.CountDownTimer
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nicholasrutherford.chal.ChallengeCalenderDay
import com.nicholasrutherford.chal.challengebanner.ChallengeBannerType
import com.nicholasrutherford.chal.data.firebase.ActiveChallenge
import com.nicholasrutherford.chal.data.realdata.Challenges
import com.nicholasrutherford.chal.data.realdata.LiveChallenges
import com.nicholasrutherford.chal.data.responses.CurrentActiveChallengesResponse
import com.nicholasrutherford.chal.firebase.ACTIVE_CHALLENGES
import com.nicholasrutherford.chal.firebase.USERS
import com.nicholasrutherford.chal.firebase.read.ReadAccountFirebase
import com.nicholasrutherford.chal.firebase.read.accountinfo.ReadFirebaseFieldsImpl
import com.nicholasrutherford.chal.firebase.sharedpref.clear.ClearFirebaseSharedPref
import com.nicholasrutherford.chal.firebase.sharedpref.write.WriteFirebaseSharedPref
import com.nicholasrutherford.chal.firebase.write.accountinfo.WriteAccountInfoImpl
import com.nicholasrutherford.chal.firebase.write.activenewchallenge.WriteNewActiveChallengeImpl
import com.nicholasrutherford.chal.navigationimpl.challengeredesign.ChallengeDetailsNavigationImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

const val STARTER_INDEX = "0"
private const val CHALLENGES_JSON_FILE_NAME = "challenges.json"

const val DATE_FORMAT = "dd/M/yyyy hh:mm:ss"

class ChallengeDetailsViewModel(
    private val appContext: Context,
    private val challenge: Challenges,
    private val fragmentActivity: FragmentActivity
) : ViewModel() {

    val viewState = ChallengeDetailsViewStateImpl()
    private val readProfileDetailsFirebase = ReadAccountFirebase(appContext)
    private val writeNewActiveChallenge = WriteNewActiveChallengeImpl()

    private val uid = FirebaseAuth.getInstance().uid ?: ""

    private var navigation = ChallengeDetailsNavigationImpl()
    val ref = FirebaseDatabase.getInstance().getReference(USERS)

    private var isAbleToEnroll: Boolean = true

    private val _activeChallengesResponse = MutableStateFlow(listOf<CurrentActiveChallengesResponse>())
    val activeChallengesResponse: StateFlow<List<CurrentActiveChallengesResponse>> = _activeChallengesResponse

    private val _allActiveChallengesListSize = MutableStateFlow(0)
    private val allActiveChallengesListSize: StateFlow<Int> = _allActiveChallengesListSize

    private var currentActiveChallengesSize = 0

    private val challengeCalenderDay = ChallengeCalenderDay()
    var liveChallengesFilterList = arrayListOf<LiveChallenges>()

    private val clearFirebaseSharedPref = ClearFirebaseSharedPref(appContext)
    private val writeFirebaseSharedPref = WriteFirebaseSharedPref(appContext)

    private val writeAccountInfoImpl = WriteAccountInfoImpl()

    private val readFirebaseFieldsImpl = ReadFirebaseFieldsImpl()

    init {
        initViewStateOnLoad()

        fetchChallenges()
        fetchActiveChallenges()
        fetchAllChallengesListSize()

        viewModelScope.launch {
            allActiveChallengesListSize.collect {
                currentActiveChallengesSize = it
            }
        }
    }

    fun initViewStateOnLoad() {
        readProfileDetailsFirebase.getUsername()?.let { userName ->
            viewState.toolbarName = userName
        }
        readProfileDetailsFirebase.getUserProfilePicture()?.let { profilePicture ->
            viewState.toolbarImage = profilePicture
        }
    }

    fun dateChallengeExpires(daysAgo: Int): String {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, +daysAgo)

        return SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).format(calendar.time)
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

    fun fetchAllChallengesListSize() = readFirebaseFieldsImpl.getAllChallengesSizeFlow(_allActiveChallengesListSize)

    private fun enrollUserIntoNewChallenge(starterIndex: String) {
        val challengeExpire = challengeCalenderDay.dayInChallenge() + 7

        writeNewActiveChallenge.writeNewActiveChallenge(currentActiveChallengesSize, uid, starterIndex, ActiveChallenge(
            name = challenge.title,
            bio = challenge.desc,
            categoryName = challenge.category,
            numberOfDaysInChallenge = challenge.duration,
            challengeExpire = challengeExpire.toString(),
            currentDay = challengeCalenderDay.dayInChallenge(),
            username = readProfileDetailsFirebase.getUsername() ?: ""
        ))

        updateNewsFeedBanner(
            title = "Success! You've joined the",
            desc = challenge.title,
            isVisible = true,
            isCloseable = true
        )

        navigation.showAlert(
            "You have just joined the ${challenge.duration} Day ${challenge.title}! Get started by posting progress.",
            challenge.title, fragmentActivity
        )
    }

    private fun updateNewsFeedBanner(title: String, desc: String, isVisible: Boolean, isCloseable: Boolean) {
        clearFirebaseSharedPref.clearBannerTypeDetails()

        writeFirebaseSharedPref.writeSharedPrefBannerTypeTitle(title = title)
        writeFirebaseSharedPref.writeSharedPrefBannerTypeDesc(description = desc)
        writeFirebaseSharedPref.writeSharedPrefBannerTypeIsVisible(isVisible = isVisible)
        writeFirebaseSharedPref.writeBannerTypeIsCloseable(isCloseable = isCloseable)

        writeAccountInfoImpl.updateChallengeBannerType(uid = uid, bannerType = ChallengeBannerType.JOINED_CHALLENGE.value)
    }

    private fun fetchChallenges() {
        var liveChallengesList: List<LiveChallenges>
        val listOfLiveChallenges = object : TypeToken<List<LiveChallenges>>() {}.type
        liveChallengesList = Gson().fromJson(readJsonChallenges(), listOfLiveChallenges)

        liveChallengesList.forEach { challengesList ->
            if (challengesList.categoryNumber == challenge.categoryNumber) {
                liveChallengesFilterList.add(challengesList)
            }
        }
    }

    private fun readJsonChallenges(): String? {
        val jsonString: String

        try {
            jsonString = appContext.assets.open(CHALLENGES_JSON_FILE_NAME).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }

        return jsonString
    }

    fun showChallengeDetails(challenge: Challenges) = navigation.showChallengeDetails(fragmentActivity.supportFragmentManager, appContext, challenge)

    inner class ChallengeDetailsViewStateImpl : ChallengeDetailsViewState {
        override var toolbarName: String = ""
        override var toolbarImage: String = ""
    }

    data class JoinChallenge(
        val isAbleToEnroll: Boolean,
        val size: Int
    )
}
