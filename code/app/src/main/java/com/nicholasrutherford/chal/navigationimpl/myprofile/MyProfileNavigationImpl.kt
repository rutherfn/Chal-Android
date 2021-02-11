package com.nicholasrutherford.chal.navigationimpl.myprofile

import android.content.Context
import android.graphics.Color
import androidx.fragment.app.FragmentManager
import cc.cloudist.acplibrary.ACProgressConstant
import cc.cloudist.acplibrary.ACProgressFlower
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nicholasrutherford.chal.MainActivity
import com.nicholasrutherford.chal.editMyProfileFragment
import com.nicholasrutherford.chal.helpers.visibleOrGone
import com.nicholasrutherford.chal.profile.profiles.MyProfileNavigation

class MyProfileNavigationImpl : MyProfileNavigation {

    private var flowerLoadingDialog: ACProgressFlower? = null

    override fun showAcProgress(mainActivity: MainActivity) {
        flowerLoadingDialog = ACProgressFlower.Builder(mainActivity)
            .direction(ACProgressConstant.DIRECT_CLOCKWISE)
            .themeColor(Color.WHITE)
            .fadeColor(Color.DKGRAY).build()

        flowerLoadingDialog?.let { acProgressFlower ->
            acProgressFlower.show()
        }
    }

    override fun showEditMyProfile(activity: MainActivity, context: Context, isClicked: Boolean, fragmentManager: FragmentManager, id: Int, bottomNavigationView: BottomNavigationView) {
        if (isClicked) {
            bottomNavigationView.visibleOrGone = true
            fragmentManager.beginTransaction()
                .replace(id,
                    editMyProfileFragment(
                        activity,
                        context
                    ),
                    editMyProfileFragment(
                        activity,
                        context
                    )::javaClass.name)
                .commit()
        }
    }

    override fun hideAcProgress() {
        flowerLoadingDialog?.let { acProgressFlower ->
            acProgressFlower.dismiss()
        }
    }
}