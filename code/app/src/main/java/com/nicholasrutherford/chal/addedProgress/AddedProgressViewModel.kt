package com.nicholasrutherford.chal.addedProgress

import android.app.Application
import androidx.lifecycle.ViewModel
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.main.MainActivity
import com.nicholasrutherford.chal.navigationimpl.addedprogress.AddedProgressNavigationImpl
import javax.inject.Inject

class AddedProgressViewModel @Inject constructor(private val application: Application, mainActivity: MainActivity) : ViewModel() {

    val viewState = AddedProgressViewStateImpl()
    val navigation = AddedProgressNavigationImpl(application = application, activity = mainActivity)

    init {
        viewState.toolbarBackButtonVisible = true
        viewState.toolbarTitle = application.applicationContext.getString(R.string.added_progress)
    }

    fun onAddMoreProgressClicked() {
        navigation.pop()
    }

    fun onContinueClicked() {
        navigation.showNewsFeed()
    }

    inner class AddedProgressViewStateImpl: AddedProgressViewState {
        override var toolbarBackButtonVisible: Boolean = false
        override var toolbarTitle: String = ""
    }
}