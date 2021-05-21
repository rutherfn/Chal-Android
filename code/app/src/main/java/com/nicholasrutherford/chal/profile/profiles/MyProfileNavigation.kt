package com.nicholasrutherford.chal.profile.profiles

import android.content.Context
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nicholasrutherford.chal.MainActivity

interface MyProfileNavigation {
    fun showAcProgress()
    fun hideAcProgress()
    fun showEditMyProfile()
}