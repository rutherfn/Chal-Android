package com.nicholasrutherford.chal.profile.editprofile

import android.content.Context
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nicholasrutherford.chal.MainActivity

interface EditMyProfileNavigation {
    fun showMyProfile(activity: MainActivity, context: Context, isClicked: Boolean, fragmentManager: FragmentManager, id: Int, bottomNavigationView: BottomNavigationView)
    fun showAcProgress(mainActivity: MainActivity)
    fun hideAcProgress()
}