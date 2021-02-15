package com.nicholasrutherford.chal.newsfeed

import android.content.Context
import androidx.fragment.app.FragmentActivity

interface NewsFeedNavigation {
    fun showAlert(title: String, message: String, fragmentActivity: FragmentActivity)
    fun showProgress(fragmentActivity: FragmentActivity, context: Context)
}
