package com.nicholasrutherford.chal.navigation.debug

import androidx.fragment.app.FragmentManager
import com.nicholasrutherford.chal.fragments.debug.ChangeFontsAndColorsFragment
import com.nicholasrutherford.chal.navigation.Navigation

interface DebugNavigation : Navigation {
    fun showChangeFontsAndColorsFragment(isClicked: Boolean, fragmentManager: FragmentManager, id: Int, changeFontsAndColorsFragment: ChangeFontsAndColorsFragment)
}