package com.nicholasrutherford.chal.navigation.debugpassword

import android.content.Context
import androidx.fragment.app.FragmentActivity
import com.nicholasrutherford.chal.activitys.debug.DebugActivity
import com.nicholasrutherford.chal.navigation.Navigation

interface DebugPasswordDialogNavigation : Navigation {
    fun startDebugActivity(context: Context, fragmentActivity: FragmentActivity, activity: DebugActivity)
}