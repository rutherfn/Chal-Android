package com.nicholasrutherford.chal.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicholasrutherford.chal.data.debug.Debug
import com.nicholasrutherford.chal.helpers.Helper
import com.nicholasrutherford.chal.room.StarterTableEntries
import com.nicholasrutherford.chal.room.entity.ConfigurationEntity
import com.nicholasrutherford.chal.room.helpers.ConfigurationHelper
import com.nicholasrutherford.chal.viewstate.HomeViewState
import kotlinx.coroutines.launch

class HomeViewModel(context: Context) : ViewModel() {

    // declarations
    val debugOptionsList: Debug? = null
    val viewState = HomeViewStateImpl()
    var helper = Helper()
    val configHelper = ConfigurationHelper(context)

    init {
        setupConfigHome()
    }

    private fun setupConfigHome() {
        viewModelScope.launch {
            viewState.configurationEntity = configHelper.currentConfigurationRecords()[0]
        }
    }

    inner class HomeViewStateImpl() : HomeViewState {
        override var configurationEntity = StarterTableEntries().configurationStarterEntry
        override val isWallVisible = true
    }
}