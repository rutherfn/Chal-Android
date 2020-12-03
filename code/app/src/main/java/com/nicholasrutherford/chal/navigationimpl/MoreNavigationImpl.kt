package com.nicholasrutherford.chal.navigationimpl

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.fragment.app.FragmentManager
import cc.cloudist.acplibrary.ACProgressConstant
import cc.cloudist.acplibrary.ACProgressFlower
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nicholasrutherford.chal.account.login.LoginActivity
import com.nicholasrutherford.chal.activitys.MainActivity
import com.nicholasrutherford.chal.fragments.profileFragment
import com.nicholasrutherford.chal.helpers.visibleOrGone
import com.nicholasrutherford.chal.settings.more.MoreNavigation

class MoreNavigationImpl : MoreNavigation {

    private var flowerLoadingDialog: ACProgressFlower? = null

    override fun login(mainActivity: MainActivity, context: Context) {
        val intent = Intent(context, LoginActivity::class.java)
        mainActivity.startActivity(intent)
    }

    override fun showAcProgress(mainActivity: MainActivity) {
        flowerLoadingDialog = ACProgressFlower.Builder(mainActivity)
            .direction(ACProgressConstant.DIRECT_CLOCKWISE)
            .themeColor(Color.WHITE)
            .fadeColor(Color.DKGRAY).build()

        flowerLoadingDialog?.let {acProgressFlower ->
            acProgressFlower.show()
        }
    }

    override fun showMyProfile(activity: MainActivity, context: Context, isClicked: Boolean, fragmentManager: FragmentManager, id: Int, bottomNavigationView: BottomNavigationView) {
        if (isClicked) {
            bottomNavigationView.visibleOrGone = true
            fragmentManager.beginTransaction()
                .replace(id, profileFragment(activity, context),
                profileFragment(activity, context)::javaClass.name)
                .commit()
        }
    }

    override fun hideAcProgress() {
        flowerLoadingDialog?.let {acProgressFlower ->
            acProgressFlower.dismiss()
        }
    }

}