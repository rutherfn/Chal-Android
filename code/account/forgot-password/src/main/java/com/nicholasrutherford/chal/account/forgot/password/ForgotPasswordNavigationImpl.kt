package com.nicholasrutherford.chal.account.forgot.password

import android.app.Application
import android.graphics.Color
import cc.cloudist.acplibrary.ACProgressConstant
import cc.cloudist.acplibrary.ACProgressFlower
import javax.inject.Inject

class ForgotPasswordNavigationImpl @Inject constructor(
    private val application: Application
    ): ForgotPasswordNavigation {

    private var flowerLoadingDialog: ACProgressFlower? = null

    override fun showAcProgress() {
        flowerLoadingDialog = ACProgressFlower.Builder(application)
            .direction(ACProgressConstant.DIRECT_CLOCKWISE)
            .themeColor(Color.WHITE)
            .fadeColor(Color.DKGRAY).build()

        flowerLoadingDialog?.show()
    }

    override fun hideAcProgress() {
        flowerLoadingDialog?.dismiss()
    }

    override fun showForgotPasswordAlert(title: String, message: String) {
        TODO("Not yet implemented")
    }
}