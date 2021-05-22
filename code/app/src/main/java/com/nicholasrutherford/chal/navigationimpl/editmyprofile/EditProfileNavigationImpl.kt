package com.nicholasrutherford.chal.navigationimpl.editmyprofile

import android.app.Application
import android.graphics.Color
import cc.cloudist.acplibrary.ACProgressConstant
import cc.cloudist.acplibrary.ACProgressFlower
import com.nicholasrutherford.chal.MainActivity
import com.nicholasrutherford.chal.helpers.visibleOrGone
import com.nicholasrutherford.chal.navigationimpl.challengeredesign.container
import com.nicholasrutherford.chal.profile.editprofile.EditProfileNavigation
import com.nicholasrutherford.chal.profile.profiles.MyProfileFragment
import javax.inject.Inject

class EditProfileNavigationImpl @Inject constructor(private val application: Application, private val mainActivity: MainActivity) : EditProfileNavigation {

    private var flowerLoadingDialog: ACProgressFlower? = null

    override fun pop() = mainActivity.supportFragmentManager.popBackStack()

    override fun showMyProfile() {
        mainActivity.binding?.bvNavigation?.visibleOrGone = true

        mainActivity.supportFragmentManager.beginTransaction()
            .replace(
                container,
                MyProfileFragment(application),
                MyProfileFragment(application).javaClass.name
            )
            .addToBackStack("")
            .commit()
    }

    override fun showAcProgress() {
        flowerLoadingDialog = ACProgressFlower.Builder(mainActivity)
            .direction(ACProgressConstant.DIRECT_CLOCKWISE)
            .themeColor(Color.WHITE)
            .fadeColor(Color.DKGRAY).build()

        flowerLoadingDialog?.show()
    }

    override fun hideAcProgress() {
        flowerLoadingDialog?.dismiss()
    }
}