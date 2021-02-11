package com.nicholasrutherford.chal.more

import android.content.Context
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nicholasrutherford.chal.MainActivity

interface MoreNavigation {
    fun login(mainActivity: MainActivity, context: Context)
    fun reportBug(mainActivity: MainActivity, context: Context, bottomNavigationView: BottomNavigationView)
    fun showAlert(title: String, message: String, mainActivity: MainActivity)
    fun showAcProgress(naubActivity: MainActivity)
    fun showProgress(mainActivity: MainActivity, context: Context)
    fun showMyProfile(activity: MainActivity, context: Context, isClicked: Boolean, fragmentManager: FragmentManager, id: Int, bottomNavigationView: BottomNavigationView)
    fun hideAcProgress()
}