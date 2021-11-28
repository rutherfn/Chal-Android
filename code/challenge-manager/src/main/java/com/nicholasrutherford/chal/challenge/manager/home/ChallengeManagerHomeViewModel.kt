package com.nicholasrutherford.chal.challenge.manager.home

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import com.nicholasrutherford.chal.challenge.manager.R
import com.nicholasrutherford.chal.main.navigation.Navigator
import com.nicholasrutherford.chal.ui.base_vm.BaseViewModel

class ChallengeManagerHomeViewModel @ViewModelInject constructor(
    private val application: Application,
    private val navigator: Navigator
) : BaseViewModel() {

    inner class ChallengeManagerViewStateImpl: ChallengeManagerHomeViewState {
        override var toolbarText: String = application.getString(R.string.back)
    }
}