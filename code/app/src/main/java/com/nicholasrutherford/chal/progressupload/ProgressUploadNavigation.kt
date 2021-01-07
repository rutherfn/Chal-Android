package com.nicholasrutherford.chal.progressupload

import android.content.Context
import com.nicholasrutherford.chal.MainActivity

interface ProgressUploadNavigation {
    fun openGallery(progressUploadActivity: ProgressUploadActivity)
    fun showMainActivity(progressUploadActivity: ProgressUploadActivity, appcontext: Context)
    fun showAcProgress(progressUploadActivity: ProgressUploadActivity)
    fun hideAcProgress()
    fun progressUploadAlert(alertMessageText: String, alertTitle: String, progressUploadActivity: ProgressUploadActivity)
    fun showAlert(alertMessageText: String, alertTitle: String, progressUploadActivity: ProgressUploadActivity, context: Context, isClicked: Boolean, id: Int)
}