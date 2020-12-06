package com.nicholasrutherford.chal.viewstate

import com.nicholasrutherford.chal.ViewState
import com.nicholasrutherford.chal.room.entity.ConfigurationEntity

interface ChangeFontsAndColorsViewState : ViewState {
    val currentConfigurations : ConfigurationEntity
    val newConfigurations: ConfigurationEntity
    val fontsLocations: Array<out String>
    val listOfFonts: Array<out String>
    val newSelectedPrimaryColor: String
    val newSelectedSecondaryColor: String
}