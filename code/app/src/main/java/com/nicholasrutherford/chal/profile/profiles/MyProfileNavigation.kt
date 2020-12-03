package com.nicholasrutherford.chal.profile.profiles

import android.content.Context
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nicholasrutherford.chal.activitys.MainActivity

interface MyProfileNavigation {
    fun showAcProgress(naubActivity: MainActivity)
    fun hideAcProgress()
    fun showEditMyProfile(activity: MainActivity, context: Context, isClicked: Boolean, fragmentManager: FragmentManager, id: Int, bottomNavigationView: BottomNavigationView)
}