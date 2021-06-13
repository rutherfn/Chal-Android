package com.nicholasrutherford.chal.navigationimpl.myprofile

import android.app.Application
import android.graphics.Color
import android.os.CountDownTimer
import cc.cloudist.acplibrary.ACProgressConstant
import cc.cloudist.acplibrary.ACProgressFlower
import com.nicholasrutherford.chal.main.MainActivity
import com.nicholasrutherford.chal.helpers.visibleOrGone
import com.nicholasrutherford.chal.navigationimpl.challengeredesign.container
import com.nicholasrutherford.chal.profile.editprofile.EditProfileFragment
import com.nicholasrutherford.chal.profile.profiles.MyProfileNavigation
import javax.inject.Inject

@Suppress("MagicNumber")
const val COUNT_DOWN_IN_FUTURE: Long = 2000
const val COUNT_DOWN_INTERVAL: Long = 100

class MyProfileNavigationImpl @Inject constructor(private val application: Application, private val mainActivity: MainActivity) : MyProfileNavigation {

    private var flowerLoadingDialog: ACProgressFlower? = null

    override fun showAcProgress() {
        flowerLoadingDialog = ACProgressFlower.Builder(mainActivity)
            .direction(ACProgressConstant.DIRECT_CLOCKWISE)
            .themeColor(Color.WHITE)
            .fadeColor(Color.DKGRAY).build()

        flowerLoadingDialog?.show()

        val timer = object : CountDownTimer(COUNT_DOWN_IN_FUTURE, COUNT_DOWN_INTERVAL) {

            override fun onTick(millisUntilFinished: Long) = Unit

            override fun onFinish() {
                hideAcProgress()
            }
        }
        timer.start()
    }

    override fun showEditMyProfile() {
        mainActivity.binding?.bvNavigation?.visibleOrGone = true

        mainActivity.supportFragmentManager.beginTransaction()
            .replace(
                container,
                EditProfileFragment(application),
                EditProfileFragment(application)::javaClass.name
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