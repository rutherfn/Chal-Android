package com.nicholasrutherford.chal.challengesredesign.challenges

import android.content.Context
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nicholasrutherford.chal.activitys.MainActivity
import com.nicholasrutherford.chal.firebase.read.ReadAccountFirebase

class ChallengesRedesignViewModel(private val mainActivity: MainActivity, private val appContext: Context,
                                  private val fragmentManager: FragmentManager, private val container: Int,
                                  private val bottomNavigationView: BottomNavigationView
) : ViewModel() {

    val viewState = ChallengesRedesignViewStateImpl()
    private val readProfileDetailsFirebase = ReadAccountFirebase(appContext)

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

    inner class ChallengesRedesignViewStateImpl:
        ChallengesRedesignViewState {
        override var toolbarName: String = ""
        override var toolbarImage: String = ""
    }
}