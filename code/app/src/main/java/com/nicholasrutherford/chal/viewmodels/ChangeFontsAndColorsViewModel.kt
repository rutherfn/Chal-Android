package com.nicholasrutherford.chal.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.room.StarterTableEntries
import com.nicholasrutherford.chal.viewstate.ChangeFontsAndColorsViewState

class ChangeFontsAndColorsViewModel(private val context: Context) : ViewModel() {

    val viewState = ChangeFontsAndColorsViewStateImpl()

    // declarations
    init {

    }



    inner class ChangeFontsAndColorsViewStateImpl() : ChangeFontsAndColorsViewState {
        override val currentConfigurations =  StarterTableEntries().configurationStarterEntry
        override val newConfigurations =  StarterTableEntries().configurationStarterEntry
        override val fontsLocations: Array<String> = context.resources.getStringArray(R.array.listOfFontsLocations)
        override val listOfFonts: Array<String> = context.resources.getStringArray(R.array.listOfFonts)
        override val newSelectedPrimaryColor = ""
        override val newSelectedSecondaryColor = ""
    }
}