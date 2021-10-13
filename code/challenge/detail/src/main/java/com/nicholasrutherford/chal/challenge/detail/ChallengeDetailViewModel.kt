package com.nicholasrutherford.chal.challenge.detail

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nicholasrutherford.chal.firebase.realtime.database.create.CreateFirebaseDatabase
import com.nicholasrutherford.chal.firebase.realtime.database.fetch.FetchFirebaseDatabase
import com.nicholasrutherford.chal.helper.constants.JSON_CHALLENGES_NAME
import com.nicholasrutherford.chal.helper.constants.daysInChallengeActiveChallengePath
import com.nicholasrutherford.chal.main.navigation.Navigator
import com.nicholasrutherford.chal.shared.preference.create.CreateSharedPreference
import com.nicholasrutherford.chal.shared.preference.remove.RemoveSharedPreference
import com.nicholasrutherford.chal.ui.base_vm.BaseViewModel
import con.nicholasrutherford.chal.data.challenges.*
import con.nicholasrutherford.chal.data.challenges.banner.ChallengeBannerType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

// not used
const val DATE_FORMAT = "dd/M/yyyy hh:mm:ss"

class ChallengeDetailViewModel @ViewModelInject constructor(
    private val application: Application,
    private val createSharedPreference: CreateSharedPreference,
    private val removeSharedPreference: RemoveSharedPreference,
    private val createFirebaseDatabase: CreateFirebaseDatabase,
    private val fetchFirebaseDatabase: FetchFirebaseDatabase,
    private val navigator: Navigator
) : BaseViewModel() {

    private var isAbleToEnroll: Boolean = true
    private var username: String = application.getString(R.string.empty_string)

    var joinableFilterByChallenges = arrayListOf<JoinableChallenges>()

    private val _loggedInUsername = MutableStateFlow(application.getString(R.string.empty_string))
    private val loggedInUsername: StateFlow<String> = _loggedInUsername

    private val _allUserActiveChallengesList = MutableStateFlow(listOf<ActiveChallengesListResponse>())
    val allUserActiveChallengesList: StateFlow<List<ActiveChallengesListResponse>> = _allUserActiveChallengesList

    private val _alljoinableFilteredChallengesList = MutableStateFlow(listOf<JoinableChallenges>())
    val allJoinableFilteredChallengesList: StateFlow<List<JoinableChallenges>> = _alljoinableFilteredChallengesList

    private var allUserChallengesList: List<ActiveChallengesListResponse> = emptyList()

    var alertTitle = application.getString(R.string.empty_string)
    var alertMessage = application.getString(R.string.empty_string)

    init {
        viewModelScope.launch {
            loggedInUsername.collect { name ->
                username = name
            }
        }

        viewModelScope.launch {
        allUserActiveChallengesList.collect { userActiveChallengesList ->
            allUserChallengesList = userActiveChallengesList
            }
        }

        fetchFirebaseDatabase.fetchLoggedInUsername(_loggedInUsername)

        fetchAllUserActiveChallenges()
    }

    var selectedAvailableChallenge: AvailableChallenges? = null

    val viewState = ChallengeDetailViewStateImpl()

    private fun fetchAllUserActiveChallenges() = fetchFirebaseDatabase.fetchAllUserActiveChallenges(_allUserActiveChallengesList)

    fun setSelectedAvailableChallenge(
        challengeTitle: String,
        challengeCategory: String,
        challengeUrl: String,
        challengeDesc: String,
        challengeTime: String,
        challengeDuration: Int,
        challengeCategoryNumber: Int
    ) {
        selectedAvailableChallenge = AvailableChallenges(
            title = challengeTitle,
            category = challengeCategory,
            url = challengeUrl,
            desc = challengeDesc,
            time = challengeTime,
            duration = challengeDuration,
            categoryNumber = challengeCategoryNumber
        )

        selectedAvailableChallenge?.let { availableChallenge -> setUnitalViewState(availableChallenge) }

        fetchAllJoinableChallenges()
    }

    private fun setUnitalViewState(availableChallenge: AvailableChallenges) {
        viewState.title = availableChallenge.title
        viewState.description = availableChallenge.desc
        viewState.category = availableChallenge.category
        viewState.categoryIcon = categoryChallengeIcon(availableChallenge.categoryNumber)
        viewState.challengeUrlImage = availableChallenge.url

        setViewStateAsUpdated()
    }

    private fun categoryChallengeIcon(categoryNumber: Int): Int {
        return when (categoryNumber) {
            0 -> {
                R.drawable.ic_health_wellness
            }
            1 -> {
                R.drawable.ic_intellectual
            }
            else -> {
                R.drawable.ic_lifestyle
            }
        }
    }

    private fun readJsonChallenges(): String {
        var jsonString: String = application.getString(R.string.empty_string)

        try {
            jsonString = application.applicationContext.assets.open(JSON_CHALLENGES_NAME)
                .bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
        }

        return jsonString
    }

    private fun fetchAllJoinableChallenges() {

        var listOfChallenges:  List<JoinableChallenges>
        val listOfJoinableChallenges =  object : TypeToken<List<JoinableChallenges>>() {}.type

        listOfChallenges = Gson().fromJson(readJsonChallenges(), listOfJoinableChallenges)
        listOfChallenges.forEach { challenge ->
            if (challenge.categoryNumber == selectedAvailableChallenge!!.categoryNumber) {
                joinableFilterByChallenges.add(challenge)
            }
        }

        _alljoinableFilteredChallengesList.value = joinableFilterByChallenges
    }

    private fun dateChallengeExpires(daysAgo: Int): String {
        // TODO not used, can be used to record actual date of a challenge down the line
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, +daysAgo)

        return SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).format(calendar.time)
    }

    fun onJoinChallengeClicked() {
        selectedAvailableChallenge?.title?.let { avaChallenge ->
            // navigation.showProgress()
            allUserChallengesList.forEach { userChallengeResponse ->
                userChallengeResponse.activeChallenges?.let { activeChallenge ->
                    if (activeChallenge.name.contains(avaChallenge)) {
                        isAbleToEnroll = false
                    }
                }
            }
        }
        attemptToEnrollUserIntoActiveChallenge(
            JoinChallenge(isAbleToEnroll,
                allUserChallengesList.size
            )
        )
    }

    private fun attemptToEnrollUserIntoActiveChallenge(joinChallenge: JoinChallenge) {
        if (joinChallenge.isAbleToEnroll) {
            val starterIndex = joinChallenge.size.toString()
            val challengeExpire = this.dayInChallenge() + 8

            createFirebaseDatabase.createNewActiveChallenge(
                allUserChallengesList.size,
                starterIndex,
                ActiveChallenge(
                    name = selectedAvailableChallenge?.title ?: application.getString(R.string.empty_string),
                    bio = selectedAvailableChallenge?.desc ?: application.getString(R.string.empty_string),
                    categoryName = selectedAvailableChallenge?.category ?: application.getString(R.string.empty_string),
                    numberOfDaysInChallenge = selectedAvailableChallenge?.duration ?: 0,
                    challengeExpire =challengeExpire.toString(),
                    currentDay = this.dayInChallenge(),
                    username = username
                ))

            removeSharedPreference.removeChallengeBannerPreferences()

            createSharedPreference.createChallengeBannerTypeTitleSharedPreference("Success! You joined the")
            createSharedPreference.createChallengeBannerTypeDescSharedPreference(
                selectedAvailableChallenge?.title ?: application.getString(R.string.empty_string
                ))
            createSharedPreference.createChallengeBannerTypeIsVisible(true)
            createSharedPreference.createChallengeBannerTypeIsCloseable(true)

            createFirebaseDatabase.createChallengeBannerType(ChallengeBannerType.JOINED_CHALLENGE.value)

            alertTitle = selectedAvailableChallenge?.title ?: application.getString(R.string.empty_string)
            alertMessage = "You have just joined the ${selectedAvailableChallenge!!.duration} " +
                    "Day ${selectedAvailableChallenge!!.title}! Get started by posting progress."

            setShouldShowAlertAsUpdated()
        } else {
            alertTitle = application.getString(R.string.error_can_not_join_challenge)
            alertMessage = "It looks like your enrolled already in the ${selectedAvailableChallenge!!.duration}" +
                    " Day ${selectedAvailableChallenge!!.title}! Start posting progress on your challenge now! "

            setShouldShowAlertAsUpdated()
        }
    }

    fun onBackClicked() = navigator.navigateBack()

    inner class ChallengeDetailViewStateImpl: ChallengeDetailViewState {
        override var title: String = application.getString(R.string.empty_string)
        override var description = application.getString(R.string.empty_string)
        override var category = application.getString(R.string.empty_string)
        override var categoryIcon: Int? = null
        override var challengeUrlImage: String = application.getString(R.string.empty_string)
    }
}