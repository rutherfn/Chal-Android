package com.nicholasrutherford.chal.progressupload

interface ProgressUploadNavigation {
    fun pop()
    fun finish()
    fun openGallery()
    fun showAcProgress()
    fun hideAcProgress()
    fun progressUploadAlert(alertMessageText: String, alertTitle: String)
    fun showAddedProgress(isChallengeComplete: Boolean)
    fun showAlert(alertMessageText: String, alertTitle: String)
    fun showNewsFeed()
    fun showCancelAndDiscardAlert(message: String, title: String)
}
