package com.nicholasrutherford.chal.navigationimpl.challengeredesign

import android.app.AlertDialog
import android.graphics.Color
import androidx.fragment.app.FragmentActivity
import cc.cloudist.acplibrary.ACProgressConstant
import cc.cloudist.acplibrary.ACProgressFlower
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.challengesredesign.challengedetails.ChallengeDetailsNavigation

class ChallengeDetailsNavigationImpl : ChallengeDetailsNavigation {
    private var flowerLoadingDialog: ACProgressFlower? = null

    override fun showAlert(message: String, title: String, fragmentActivity: FragmentActivity) {
        val alertDialogBuffer = AlertDialog.Builder(fragmentActivity)

        alertDialogBuffer.setMessage(message)
            .setCancelable(false)
            .setPositiveButton(fragmentActivity.getString(R.string.ok)) { dialog, _ ->
                dialog.cancel()
            }

        val alert = alertDialogBuffer.create()

        alert.setTitle(title)
        alert.show()
    }

    override fun showAcProgress(fragmentActivity: FragmentActivity) {
        flowerLoadingDialog = ACProgressFlower.Builder(fragmentActivity)
            .direction(ACProgressConstant.DIRECT_CLOCKWISE)
            .themeColor(Color.WHITE)
            .fadeColor(Color.DKGRAY).build()

        flowerLoadingDialog?.let { acProgressFlower ->
            acProgressFlower.show()
        }
    }

    override fun hideAcProgress() {
        flowerLoadingDialog?.let { acProgressFlower ->
            acProgressFlower.dismiss()
        }
    }
}
