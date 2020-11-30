package com.nicholasrutherford.chal.navigationimpl

import android.content.Context
import android.content.Intent
import android.graphics.Color
import cc.cloudist.acplibrary.ACProgressConstant
import cc.cloudist.acplibrary.ACProgressFlower
import com.nicholasrutherford.chal.account.login.LoginActivity
import com.nicholasrutherford.chal.activitys.MainActivity
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

    override fun hideAcProgress() {
        flowerLoadingDialog?.let {acProgressFlower ->
            acProgressFlower.dismiss()
        }
    }

}