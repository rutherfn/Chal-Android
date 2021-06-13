package com.nicholasrutherford.chal.navigationimpl.newsfeed

import android.app.AlertDialog
import android.app.Application
import android.graphics.Color
import android.os.CountDownTimer
import cc.cloudist.acplibrary.ACProgressConstant
import cc.cloudist.acplibrary.ACProgressFlower
import com.nicholasrutherford.chal.main.MainActivity
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.navigationimpl.challengeredesign.container
import com.nicholasrutherford.chal.newsfeed.NewsFeedNavigation
import com.nicholasrutherford.chal.peoplelist.PeopleListFragment
import com.nicholasrutherford.chal.progressupload.ProgressUploadFragment
import com.nicholasrutherford.chal.progressupload.ProgressUploadParams
import javax.inject.Inject

class NewsFeedNavigationImpl @Inject constructor(private val application: Application, private val mainActivity: MainActivity) : NewsFeedNavigation {

    private var flowerLoadingDialog: ACProgressFlower? = null

    override fun hideAcProgress() {
        val timer = object  : CountDownTimer(1500, 100) {
            override fun onTick(millisUntilFinished: Long) = Unit

            override fun onFinish() {
                flowerLoadingDialog?.dismiss()
            }
        }
        timer.start()
    }

    override fun pop()  = mainActivity.supportFragmentManager.popBackStack()

    override fun showAcProgress() {
        flowerLoadingDialog = ACProgressFlower.Builder(mainActivity)
            .direction(ACProgressConstant.DIRECT_CLOCKWISE)
            .themeColor(Color.WHITE)
            .fadeColor(Color.DKGRAY).build()

        flowerLoadingDialog?.show()
    }

    override fun showPeopleList() {
        mainActivity.supportFragmentManager.beginTransaction()
            .replace(
                container,
                PeopleListFragment(application),
                PeopleListFragment(application)::javaClass.name
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

    override fun showProgress() {
        mainActivity.supportFragmentManager.beginTransaction()
            .replace(
                container,
                ProgressUploadFragment(application, ProgressUploadParams(isUpdate = false, title = null, caption = null, photoUri = null)),
                ProgressUploadFragment(application, ProgressUploadParams(isUpdate = false, title = null, caption = null, photoUri = null))::javaClass.name
            )
            .addToBackStack("")
            .commit()
    }

}
