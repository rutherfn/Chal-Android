package com.nicholasrutherford.chal.main.upload.progress.addedprogress

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import com.nicholasrutherford.chal.main.upload.progress.R
import com.nicholasrutherford.chal.ui.base_vm.BaseViewModel
import dagger.hilt.android.AndroidEntryPoint

class AddedProgressViewModel @ViewModelInject constructor(
    private val application: Application,
    private val navigation: AddedProgressNavigation
) : BaseViewModel() {

    val toolbarTitle = application.getString(R.string.added_progress)

    val viewState = AddedProgressViewStateImpl()

    fun setViewState(challengeTitle: String, challengeDay: Int) {
        viewState.challengeTitle = challengeTitle
        viewState.challengeDay = challengeDay

        setViewStateAsUpdated()
    }

    fun onAddMoreProgressClicked() {
        navigation.onNavigateBack()
    }

    fun onContinueClicked() {
        navigation.onNavigateBack()
    }

    inner class AddedProgressViewStateImpl : AddedProgressViewState {
        override var challengeTitle: String = application.getString(R.string.empty_string)
        override var challengeDay: Int = 0
    }
}