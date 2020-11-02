package com.nicholasrutherford.chal.screens.home

import android.content.Context
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.helpers.Helper
import com.nicholasrutherford.chal.navigation.home.HomeNavigationImpl
import com.nicholasrutherford.chal.room.StarterTableEntries
import com.nicholasrutherford.chal.room.helpers.ConfigurationHelper
import kotlinx.coroutines.launch

class HomeViewModel(private val fm: FragmentManager, private val containerId: Int, context: Context, private val btNavigationView: BottomNavigationView) : ViewModel() {

    val viewState = HomeViewStateImpl()
    var helper = Helper()
    val navigation = HomeNavigationImpl()
    private val configHelper = ConfigurationHelper(context)

    init {
        setupConfigHome()
    }

    private fun setupConfigHome() {
        viewModelScope.launch {
            viewState.configurationEntity = configHelper.currentConfigurationRecords()[0]
        }
    }

    fun placeHolderProfileImage(): Int {
        return R.drawable.willplaceholder
    }

    fun showProfileFragment() {
        navigation.showProfileFragment(fm, containerId, btNavigationView)
    }

    fun showChallengePostFragment() {
        navigation.showChallengePostFragment(fm, containerId, btNavigationView)
    }

    class HomeViewStateImpl() : HomeViewState {
        override var configurationEntity = StarterTableEntries().configurationStarterEntry
        override val isWallVisible = true
    }
}