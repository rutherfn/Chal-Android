package com.nicholasrutherford.chal.navigation.debug

import androidx.fragment.app.FragmentManager
import com.nicholasrutherford.chal.fragments.debug.ChangeFontsAndColorsFragment

class DebugNavigationImpl : DebugNavigation {
    override fun showChangeFontsAndColorsFragment(isClicked: Boolean, fragmentManager: FragmentManager, id: Int, changeFontsAndColorsFragment: ChangeFontsAndColorsFragment) {
        if(isClicked) {
            fragmentManager.beginTransaction()
                .replace(
                    id,
                    changeFontsAndColorsFragment,
                    changeFontsAndColorsFragment::javaClass.name
                )
                .commit()
        }
    }

}