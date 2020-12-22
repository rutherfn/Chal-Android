package com.nicholasrutherford.chal.navigationimpl.challengeredesign

import android.graphics.Color
import cc.cloudist.acplibrary.ACProgressConstant
import cc.cloudist.acplibrary.ACProgressFlower
import com.nicholasrutherford.chal.MainActivity
import com.nicholasrutherford.chal.challengesredesign.challengedetails.ChallengeDetailsNavigation

class ChallengeDetailsNavigationImpl : ChallengeDetailsNavigation {
    private var flowerLoadingDialog: ACProgressFlower? = null

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