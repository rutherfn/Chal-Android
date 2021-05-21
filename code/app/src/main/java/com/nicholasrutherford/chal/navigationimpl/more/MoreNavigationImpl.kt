package com.nicholasrutherford.chal.navigationimpl.more

import android.app.AlertDialog
import android.app.Application
import android.content.Intent
import android.graphics.Color
import cc.cloudist.acplibrary.ACProgressConstant
import cc.cloudist.acplibrary.ACProgressFlower
import com.nicholasrutherford.chal.MainActivity
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.account.login.LoginActivity
import com.nicholasrutherford.chal.bugreport.BugReportFragment
import com.nicholasrutherford.chal.helpers.visibleOrGone
import com.nicholasrutherford.chal.more.MoreNavigation
import com.nicholasrutherford.chal.navigationimpl.challengeredesign.container
import com.nicholasrutherford.chal.profile.profiles.MyProfileFragment
import com.nicholasrutherford.chal.progressupload.ProgressUploadActivity
import javax.inject.Inject

class MoreNavigationImpl @Inject constructor(private val application: Application, private val mainActivity: MainActivity) : MoreNavigation {

    private var flowerLoadingDialog: ACProgressFlower? = null

    override fun login() {
        application.applicationContext?.let { context ->
            val intent = Intent(context, LoginActivity::class.java)
            mainActivity.startActivity(intent)
            mainActivity.finish()
        }
    }

    override fun reportBug() {
        mainActivity.binding?.bvNavigation?.visibleOrGone = true

        mainActivity.supportFragmentManager.beginTransaction()
            .replace(
                container,
                BugReportFragment(application),
                BugReportFragment(application)::javaClass.name
            )
            .addToBackStack("")
            .commit()
    }

    override fun showAlert(title: String, message: String) {
        val alertDialogBuilder = AlertDialog.Builder(mainActivity)

        alertDialogBuilder.setMessage(message)
            .setCancelable(false)
            .setPositiveButton(mainActivity.getString(R.string.ok)) { dialog, _ ->
                dialog.cancel()
            }

        val alert = alertDialogBuilder.create()

        alert.setTitle(title)
        alert.show()
    }

    override fun showAcProgress() {
        flowerLoadingDialog = ACProgressFlower.Builder(mainActivity)
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(Color.WHITE)
                .fadeColor(Color.DKGRAY).build()

            flowerLoadingDialog?.let { acProgressFlower ->
                acProgressFlower.show()
            }
    }

    override fun showProgress() {
        val intent = Intent(application.applicationContext, ProgressUploadActivity::class.java)
        mainActivity.startActivity(intent)
    }

    override fun showMyProfile() {
        mainActivity.binding?.bvNavigation?.visibleOrGone = true

        mainActivity.supportFragmentManager.beginTransaction()
            .replace(
                container,
                MyProfileFragment(application),
                MyProfileFragment(application)::javaClass.name
            )
            .addToBackStack("")
            .commit()
    }

    override fun hideAcProgress() {
        flowerLoadingDialog?.let { acProgressFlower ->
            acProgressFlower.dismiss()
        }
    }
}
