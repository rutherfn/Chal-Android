package com.nicholasrutherford.chal.navigationimpl.more

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.fragment.app.FragmentManager
import cc.cloudist.acplibrary.ACProgressConstant
import cc.cloudist.acplibrary.ACProgressFlower
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nicholasrutherford.chal.MainActivity
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.account.login.LoginActivity
import com.nicholasrutherford.chal.bugReportFragment
import com.nicholasrutherford.chal.helpers.visibleOrGone
import com.nicholasrutherford.chal.more.MoreNavigation
import com.nicholasrutherford.chal.profileFragment
import com.nicholasrutherford.chal.progressupload.ProgressUploadActivity

class MoreNavigationImpl : MoreNavigation {

    private var flowerLoadingDialog: ACProgressFlower? = null

    override fun login(mainActivity: MainActivity, context: Context) {
        val intent = Intent(context, LoginActivity::class.java)
        mainActivity.startActivity(intent)
    }

    override fun reportBug(mainActivity: MainActivity, context: Context, bottomNavigationView: BottomNavigationView) {
        bottomNavigationView.visibleOrGone = true

        mainActivity.supportFragmentManager.beginTransaction()
            .replace(R.id.container, bugReportFragment(mainActivity, context), bugReportFragment(mainActivity, context)::javaClass.name)
            .commit()
    }

    override fun showAlert(title: String, message: String, mainActivity: MainActivity) {
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

    override fun showAcProgress(mainActivity: MainActivity) {
        flowerLoadingDialog = ACProgressFlower.Builder(mainActivity)
            .direction(ACProgressConstant.DIRECT_CLOCKWISE)
            .themeColor(Color.WHITE)
            .fadeColor(Color.DKGRAY).build()

        flowerLoadingDialog?.let { acProgressFlower ->
            acProgressFlower.show()
        }
    }

    override fun showProgress(mainActivity: MainActivity, context: Context) {
        val intent = Intent(context, ProgressUploadActivity::class.java)
        mainActivity.startActivity(intent)
    }

    override fun showMyProfile(activity: MainActivity, context: Context, isClicked: Boolean, fragmentManager: FragmentManager, id: Int, bottomNavigationView: BottomNavigationView) {
        if (isClicked) {
            bottomNavigationView.visibleOrGone = true
            fragmentManager.beginTransaction()
                .replace(
                    id,
                    profileFragment(
                        activity,
                        context
                    ),
                    profileFragment(activity, context)::javaClass.name
                )
                .commit()
        }
    }

    override fun hideAcProgress() {
        flowerLoadingDialog?.let { acProgressFlower ->
            acProgressFlower.dismiss()
        }
    }
}
