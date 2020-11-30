package com.nicholasrutherford.chal.settings.more

import android.content.Context
import com.nicholasrutherford.chal.activitys.MainActivity

interface MoreNavigation {
    fun login(mainActivity: MainActivity, context: Context)
    fun showAcProgress(naubActivity: MainActivity)
    fun hideAcProgress()
}