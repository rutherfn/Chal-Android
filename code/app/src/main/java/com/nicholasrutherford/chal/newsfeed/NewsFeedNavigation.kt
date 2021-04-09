package com.nicholasrutherford.chal.newsfeed

import android.content.Context
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nicholasrutherford.chal.MainActivity

interface NewsFeedNavigation {
    fun showPeopleList(mainActivity: FragmentActivity, fragmentManager: FragmentManager, context: Context)
    fun showAlert(title: String, message: String, fragmentActivity: FragmentActivity)
    fun showProgress(fragmentActivity: FragmentActivity, context: Context)
}
