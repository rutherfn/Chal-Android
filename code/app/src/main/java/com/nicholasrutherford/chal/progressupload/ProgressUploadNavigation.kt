package com.nicholasrutherford.chal.progressupload

import android.content.Context

interface ProgressUploadNavigation {
    fun finish(progressUploadActivity: ProgressUploadActivity)
    fun openGallery(progressUploadActivity: ProgressUploadActivity)
    fun showMainActivity(progressUploadActivity: ProgressUploadActivity, appcontext: Context)
    fun showAcProgress(progressUploadActivity: ProgressUploadActivity)
    fun hideAcProgress()
    fun progressUploadAlert(alertMessageText: String, alertTitle: String, progressUploadActivity: ProgressUploadActivity)
    fun showAlert(alertMessageText: String, alertTitle: String, progressUploadActivity: ProgressUploadActivity, context: Context, isClicked: Boolean, id: Int)
    fun showCancelAndDiscardAlert(message: String, title: String, progressUploadActivity: ProgressUploadActivity)
}
