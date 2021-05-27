package com.nicholasrutherford.chal.navigationimpl.newsfeed

import android.app.AlertDialog
import android.app.Application
import android.content.Intent
import android.graphics.Color
import android.os.CountDownTimer
import cc.cloudist.acplibrary.ACProgressConstant
import cc.cloudist.acplibrary.ACProgressFlower
import com.nicholasrutherford.chal.MainActivity
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.navigationimpl.challengeredesign.container
import com.nicholasrutherford.chal.newsfeed.NewsFeedNavigation
import com.nicholasrutherford.chal.peoplelist.PeopleListFragment
import com.nicholasrutherford.chal.progressupload.ProgressUploadActivity
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
        val alertDialogBuilder = AlertDialog.Builder(application.applicationContext)

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
        val intent = Intent(application.applicationContext, ProgressUploadActivity::class.java)
        mainActivity.startActivity(intent)
    }
}
