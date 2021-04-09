package com.nicholasrutherford.chal.newsfeed

import android.content.Context
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nicholasrutherford.chal.MainActivity

interface NewsFeedNavigation {
    fun showPeopleList()
    fun showAlert(title: String, message: String)
    fun showProgress()
}
