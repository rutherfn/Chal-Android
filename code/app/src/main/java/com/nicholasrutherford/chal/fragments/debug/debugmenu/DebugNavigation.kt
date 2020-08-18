package com.nicholasrutherford.chal.fragments.debug.debugmenu

import androidx.fragment.app.FragmentManager
import com.nicholasrutherford.chal.fragments.debug.changecfontscolors.ChangeFontsAndColorsFragment
import com.nicholasrutherford.chal.navigation.Navigation

interface DebugNavigation : Navigation {
    fun showChangeFontsAndColorsFragment(isClicked: Boolean, fragmentManager: FragmentManager, id: Int, changeFontsAndColorsFragment: ChangeFontsAndColorsFragment)
}