package com.nicholasrutherford.chal.main.helper

import com.nicholasrutherford.chal.Screens
import com.nicholasrutherford.chal.helpers.visibleOrGone
import com.nicholasrutherford.chal.main.MainActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivityHelperImpl @Inject constructor(private val mainActivity: MainActivity) : MainActivityHelper {

    override fun updateCurrentScreen(currentScreen: Screens) {
      //  mainActivity.viewModel.updateCurrentScreen(currentScreen)
    }

    override fun updateBottomNavigationVisibility(isVisible: Boolean) {
        mainActivity.bvNavigation.visibleOrGone = isVisible
    }
}