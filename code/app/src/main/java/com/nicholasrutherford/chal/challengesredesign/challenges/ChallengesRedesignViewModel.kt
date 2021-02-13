package com.nicholasrutherford.chal.challengesredesign.challenges

import android.content.Context
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nicholasrutherford.chal.MainActivity
import com.nicholasrutherford.chal.data.realdata.LiveChallenges
import com.nicholasrutherford.chal.firebase.read.ReadAccountFirebase
import com.nicholasrutherford.chal.navigationimpl.challengeredesign.ChallengeRedesignNavigationImpl
import java.io.IOException

private const val CHALLENGES_JSON_FILE_NAME = "challenges.json"

class ChallengesRedesignViewModel(
    private val mainActivity: MainActivity,
    private val appContext: Context,
    private val fragmentManager: FragmentManager,
    private val container: Int,
    private val bottomNavigationView: BottomNavigationView
) : ViewModel() {

    val viewState = ChallengesRedesignViewStateImpl()
    val challengesRedesignNavigationImpl = ChallengeRedesignNavigationImpl()

    private val readProfileDetailsFirebase = ReadAccountFirebase(appContext)
    var liveChallengesList: List<LiveChallenges> = emptyList()

    init {
        initViewStateOnLoad()
        fetchChallenges()
    }

    fun initViewStateOnLoad() {
        readProfileDetailsFirebase.getUsername()?.let { userName ->
            viewState.toolbarName = userName
        }
        readProfileDetailsFirebase.getUserProfilePicture()?.let { profilePicture ->
            viewState.toolbarImage = profilePicture
        }
    }

    private fun fetchChallenges() {
        val listOfLiveChallenges = object : TypeToken<List<LiveChallenges>>() {}.type
        liveChallengesList = Gson().fromJson(readJsonChallenges(), listOfLiveChallenges)
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

    inner class ChallengesRedesignViewStateImpl :
        ChallengesRedesignViewState {
        override var toolbarName: String = ""
        override var toolbarImage: String = ""
    }
}
