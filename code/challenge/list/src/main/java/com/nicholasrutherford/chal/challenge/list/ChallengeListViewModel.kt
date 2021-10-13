package com.nicholasrutherford.chal.challenge.list

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nicholasrutherford.chal.firebase.realtime.database.fetch.FetchFirebaseDatabase
import com.nicholasrutherford.chal.helper.constants.CHALLENGE_HEADER_PLACEHOLDER_URL
import com.nicholasrutherford.chal.helper.constants.JSON_CHALLENGES_NAME
import com.nicholasrutherford.chal.ui.base_vm.BaseViewModel
import con.nicholasrutherford.chal.data.challenges.ActiveChallenge
import con.nicholasrutherford.chal.data.challenges.ActiveChallengesListResponse
import con.nicholasrutherford.chal.data.challenges.AvailableChallenges
import con.nicholasrutherford.chal.data.challenges.JoinableChallenges
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.io.IOException

class ChallengeListViewModel @ViewModelInject constructor(
    private val fetchFirebaseDatabase: FetchFirebaseDatabase,
    private val navigation: ChallengeListNavigation,
    private val application: Application
) : BaseViewModel() {

    private val _loggedInUsername = MutableStateFlow(application.getString(R.string.empty_string))
    private val loggedInUsername: StateFlow<String> = _loggedInUsername

    private val _loggedInProfilePicture = MutableStateFlow(application.getString(R.string.empty_string))
    private val loggedInProfilePicture: StateFlow<String> = _loggedInProfilePicture

    private val _alljoinableChallengesList = MutableStateFlow(listOf<JoinableChallenges>())
    val allJoinableChallengesList: StateFlow<List<JoinableChallenges>> = _alljoinableChallengesList

    var alertTitle = application.getString(R.string.empty_string)
    var alertDescription = application.getString(R.string.empty_string)

    val viewState = ChallengeListViewStateImpl()

    init {
        viewModelScope.launch {
            loggedInUsername.collect { toolbarName ->
                setToolbarNameFromResults(toolbarName)
            }
        }

        viewModelScope.launch {
            loggedInProfilePicture.collect { toolbarImage ->
                setToolbarImageFromResults(toolbarImage)
            }
        }

        setChallengeHeaderUrl()

        fetchFirebaseDatabase.fetchLoggedInUsername(_loggedInUsername)
        fetchFirebaseDatabase.fetchLoggedInUserProfilePicture(_loggedInProfilePicture)

        fetchAllChallenges()
    }

    private fun setChallengeHeaderUrl() {
        viewState.challengeHeaderImageUrl = CHALLENGE_HEADER_PLACEHOLDER_URL
    }

    private fun setToolbarNameFromResults(toolBarName: String) {
        viewState.toolbarName = toolBarName
        setViewStateAsUpdated()
    }

    private fun setToolbarImageFromResults(toolbarImage: String) {
        viewState.toolbarImage = toolbarImage
        setViewStateAsUpdated()
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

    private fun fetchAllChallenges() {
        val listOfJoinableChallenges =  object : TypeToken<List<JoinableChallenges>>() {}.type
        _alljoinableChallengesList.value = Gson().fromJson(readJsonChallenges(), listOfJoinableChallenges)
    }

    fun onUploadChallengeClicked() {
        alertTitle = application.getString(R.string.join_a_challenge)
        alertDescription = application.getString(R.string.join_a_challenge_message)

        setShouldShowAlertAsUpdated()
    }

    fun onShowChallengeDetailItemClicked(selectedAvailableChallenge: AvailableChallenges) {
        navigation.showChallengeDetail(selectedAvailableChallenge)
    }

    inner class ChallengeListViewStateImpl : ChallengeListViewState {
        override var challengeHeaderImageUrl: String = application.getString(R.string.empty_string)
        override var toolbarName: String = application.getString(R.string.empty_string)
        override var toolbarImage: String = application.getString(R.string.empty_string)
    }
}