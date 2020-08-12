package com.nicholasrutherford.chal.navigation.debugpassword

import android.content.Context
import android.content.Intent
import androidx.fragment.app.FragmentActivity
import com.nicholasrutherford.chal.activitys.debug.DebugActivity

class DebugPasswordDialogNavigationImpl : DebugPasswordDialogNavigation {
    override fun startDebugActivity(context: Context, fragmentActivity: FragmentActivity, activity: DebugActivity) {
        context.let {
            val intent = Intent(it.applicationContext, activity::class.java)
            context.startActivity(intent)
            fragmentActivity.finish()
        }
    }

}