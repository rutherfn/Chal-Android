package com.nicholasrutherford.chal.profile.editprofile

import android.content.Context
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nicholasrutherford.chal.MainActivity

interface EditProfileNavigation {
    fun showMyProfile()
    fun showAcProgress()
    fun hideAcProgress()
}