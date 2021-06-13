package com.nicholasrutherford.chal.navigationimpl.progressupload

import android.app.AlertDialog
import android.app.Application
import android.content.Intent
import android.graphics.Color
import cc.cloudist.acplibrary.ACProgressConstant
import cc.cloudist.acplibrary.ACProgressFlower
import com.nicholasrutherford.chal.main.MainActivity
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.navigationimpl.uploadphoto.GALLERY_REQUEST_CODE
import com.nicholasrutherford.chal.navigationimpl.uploadphoto.GALLERY_TYPE
import com.nicholasrutherford.chal.progressupload.ProgressUploadNavigation
import javax.inject.Inject

class ProgressUploadNavigationImpl @Inject constructor(
    private val application: Application,
    private val activity: MainActivity)
    : ProgressUploadNavigation {

    private var flowerLoadingDialog: ACProgressFlower? = null

    override fun finish() {
        activity.finish()
    }

    override fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = GALLERY_TYPE

        activity.startActivityForResult(intent, GALLERY_REQUEST_CODE)
    }

    override fun showMainActivity() {
        val intent = Intent(application.applicationContext, MainActivity::class.java)
        activity.startActivity(intent)
        activity.finish()
    }

    override fun showAcProgress() {
        flowerLoadingDialog = ACProgressFlower.Builder(activity)
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

    override fun progressUploadAlert(alertMessageText: String, alertTitle: String) {
        val alertDialogBuilder = AlertDialog.Builder(activity)

        alertDialogBuilder.setMessage(alertMessageText)
            .setCancelable(false)
            .setPositiveButton(activity.getString(R.string.ok)) { dialog, _ ->
                dialog.cancel()
            }

        val alert = alertDialogBuilder.create()

        alert.setTitle(alertTitle)
        alert.show()
    }

    override fun showAlert(alertMessageText: String, alertTitle: String) {
        val alertDialogBuilder = AlertDialog.Builder(activity)

        alertDialogBuilder.setMessage(alertMessageText)
            .setCancelable(false)
            .setPositiveButton(activity.getString(R.string.yes)) { dialog, _ ->
                dialog.cancel()
                showMainActivity()
            }
            .setNegativeButton(activity.getString(R.string.no)) { dialog, _ ->
                dialog.cancel()
            }

        val alert = alertDialogBuilder.create()

        alert.setTitle(alertTitle)
        alert.show()
    }

    override fun showCancelAndDiscardAlert(message: String, title: String) {

        val alertDialogBuilder = AlertDialog.Builder(activity)

        alertDialogBuilder.setMessage(message)
            .setCancelable(false)
            .setPositiveButton(activity.getString(R.string.yes)) { dialog, _ ->
                dialog.cancel()
                finish()
            }
            .setNegativeButton(activity.getString(R.string.no)) { dialog, _ ->
                dialog.cancel()
            }

        val alert = alertDialogBuilder.create()

        alert.setTitle(title)
        alert.show()
    }
}
