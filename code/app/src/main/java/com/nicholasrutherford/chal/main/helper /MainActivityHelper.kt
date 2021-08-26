package com.nicholasrutherford.chal.main.helper

import com.nicholasrutherford.chal.Screens

interface MainActivityHelper {
    fun updateCurrentScreen(currentScreen: Screens)
    fun updateBottomNavigationVisibility(isVisible: Boolean)
}