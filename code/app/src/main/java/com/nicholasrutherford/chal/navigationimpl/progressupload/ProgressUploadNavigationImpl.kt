package com.nicholasrutherford.chal.navigationimpl.progressupload

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import cc.cloudist.acplibrary.ACProgressConstant
import cc.cloudist.acplibrary.ACProgressFlower
import com.nicholasrutherford.chal.MainActivity
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.navigationimpl.uploadphoto.GALLERY_REQUEST_CODE
import com.nicholasrutherford.chal.navigationimpl.uploadphoto.GALLERY_TYPE
import com.nicholasrutherford.chal.progressupload.ProgressUploadActivity
import com.nicholasrutherford.chal.progressupload.ProgressUploadNavigation

class ProgressUploadNavigationImpl : ProgressUploadNavigation {

    private var flowerLoadingDialog: ACProgressFlower? = null

    override fun openGallery(progressUploadActivity: ProgressUploadActivity) {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = GALLERY_TYPE

        progressUploadActivity.startActivityForResult(intent, GALLERY_REQUEST_CODE)
    }

    override fun showMainActivity(
        progressUploadActivity: ProgressUploadActivity,
        appcontext: Context
    ) {
        val intent = Intent(appcontext, MainActivity::class.java)
        progressUploadActivity.startActivity(intent)
    }

    override fun showAcProgress(progressUploadActivity: ProgressUploadActivity) {
        flowerLoadingDialog = ACProgressFlower.Builder(progressUploadActivity)
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

    override fun progressUploadAlert(
        alertMessageText: String,
        alertTitle: String,
        progressUploadActivity: ProgressUploadActivity
    ) {
        val alertDialogBuilder = AlertDialog.Builder(progressUploadActivity)

        alertDialogBuilder.setMessage(alertMessageText)
            .setCancelable(false)
            .setPositiveButton(progressUploadActivity.getString(R.string.ok)) { dialog, _ ->
                dialog.cancel()
            }

        val alert = alertDialogBuilder.create()

        alert.setTitle(alertTitle)
        alert.show()
    }

    override fun showAlert(
        alertMessageText: String,
        alertTitle: String,
        progressUploadActivity: ProgressUploadActivity,
        context: Context,
        isClicked: Boolean,
        id: Int
    ) {
        val alertDialogBuilder = AlertDialog.Builder(progressUploadActivity)

        alertDialogBuilder.setMessage(alertMessageText)
            .setCancelable(false)
            .setPositiveButton(progressUploadActivity.getString(R.string.yes)) { dialog, _ ->
                dialog.cancel()
                showMainActivity(progressUploadActivity, context)
            }
            .setNegativeButton(progressUploadActivity.getString(R.string.no)) { dialog, _ ->
                dialog.cancel()
            }

        val alert = alertDialogBuilder.create()

        alert.setTitle(alertTitle)
        alert.show()
    }
}