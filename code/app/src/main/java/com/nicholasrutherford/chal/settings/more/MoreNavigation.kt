package com.nicholasrutherford.chal.settings.more

import android.content.Context
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nicholasrutherford.chal.activitys.MainActivity

interface MoreNavigation {
    fun login(mainActivity: MainActivity, context: Context)
    fun showAcProgress(naubActivity: MainActivity)
    fun showMyProfile(activity: MainActivity, context: Context, isClicked: Boolean, fragmentManager: FragmentManager, id: Int, bottomNavigationView: BottomNavigationView)
    fun hideAcProgress()
}