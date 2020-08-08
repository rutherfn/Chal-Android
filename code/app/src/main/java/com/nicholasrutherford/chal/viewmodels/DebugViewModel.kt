package com.nicholasrutherford.chal.viewmodels

import androidx.lifecycle.ViewModel
import com.nicholasrutherford.chal.data.debug.Debug
import com.nicholasrutherford.chal.viewstate.DebugViewState

class DebugViewModel(private val listOfOptions: Array<String>) : ViewModel() {

    // declarations
    val viewState = DebugViewStateImpl()

    init {
        debugOptions()
    }

    private fun debugOptions(): MutableList<Debug>? {
        for (i in listOfOptions) {
            val debugOptions = Debug(i)
            viewState.debugOptionsList.add(debugOptions)
        }
        return viewState.debugOptionsList
    }

    inner class DebugViewStateImpl() : DebugViewState {
        override val debugModeVisible = false
        override val debugOptionsList = ArrayList<Debug>()
    }

}