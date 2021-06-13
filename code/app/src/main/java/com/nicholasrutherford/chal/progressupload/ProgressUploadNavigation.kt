package com.nicholasrutherford.chal.progressupload

interface ProgressUploadNavigation {
    fun finish()
    fun openGallery()
    fun showMainActivity()
    fun showAcProgress()
    fun hideAcProgress()
    fun progressUploadAlert(alertMessageText: String, alertTitle: String)
    fun showAlert(alertMessageText: String, alertTitle: String)
    fun showCancelAndDiscardAlert(message: String, title: String)
}
