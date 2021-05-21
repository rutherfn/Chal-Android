package com.nicholasrutherford.chal.navigationimpl.myprofile

import android.app.Application
import android.graphics.Color
import android.os.CountDownTimer
import cc.cloudist.acplibrary.ACProgressConstant
import cc.cloudist.acplibrary.ACProgressFlower
import com.nicholasrutherford.chal.MainActivity
import com.nicholasrutherford.chal.helpers.visibleOrGone
import com.nicholasrutherford.chal.navigationimpl.challengeredesign.container
import com.nicholasrutherford.chal.profile.editprofile.EditProfileFragment
import com.nicholasrutherford.chal.profile.profiles.MyProfileNavigation
import javax.inject.Inject

class MyProfileNavigationImpl @Inject constructor(private val application: Application, private val mainActivity: MainActivity) : MyProfileNavigation {

    private var flowerLoadingDialog: ACProgressFlower? = null

    override fun showAcProgress() {
        flowerLoadingDialog = ACProgressFlower.Builder(mainActivity)
            .direction(ACProgressConstant.DIRECT_CLOCKWISE)
            .themeColor(Color.WHITE)
            .fadeColor(Color.DKGRAY).build()

        flowerLoadingDialog?.show()

        val timer = object : CountDownTimer(2000, 100) {

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