package com.nicholasrutherford.chal.account.forgotpasswordredesign

import android.app.AlertDialog
import android.app.Application
import android.graphics.Color
import cc.cloudist.acplibrary.ACProgressConstant
import cc.cloudist.acplibrary.ACProgressFlower
import com.nicholasrutherford.chal.KeyboardImpl
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.account.redesignlogin.RedesignLoginFragment
import com.nicholasrutherford.chal.main.MainActivity
import com.nicholasrutherford.chal.navigationimpl.challengeredesign.container
import com.nicholasrutherford.chal.ui.typefaces.TypefacesImpl
import javax.inject.Inject

class ForgotPasswordRedesignNavigationImpl @Inject constructor(
    private val application: Application,
    private val mainActivity: MainActivity,
    private val typeface: TypefacesImpl,
    private val keyboard: KeyboardImpl
): ForgotPasswordRedesignNavigation {

    private var flowerLoadingDialog: ACProgressFlower? = null

    override fun showAcProgress() {
        flowerLoadingDialog = ACProgressFlower.Builder(mainActivity)
            .direction(ACProgressConstant.DIRECT_CLOCKWISE)
            .themeColor(Color.WHITE)
            .fadeColor(Color.DKGRAY).build()

        flowerLoadingDialog?.show()
    }

    override fun forgotPasswordAlert(title: String, message: String) {
        val errorForgPasswordDialogBuilder = AlertDialog.Builder(mainActivity)

        errorForgPasswordDialogBuilder.setMessage(message)
            .setCancelable(false)
            .setPositiveButton(mainActivity.getString(R.string.ok)) { dialog, _ ->
                dialog.cancel()
            }

        val errorAlert = errorForgPasswordDialogBuilder.create()
        errorAlert.setTitle(title)

        errorAlert.show()
    }

    override fun hideAcProgress() {
        flowerLoadingDialog?.dismiss()
    }

    override fun showLogin() {
        mainActivity.supportFragmentManager.beginTransaction()
            .replace(
                container,
                RedesignLoginFragment(
                    application = application,
                    typeface = typeface,
                    keyboard = keyboard
                ),
                RedesignLoginFragment(
                    application = application,
                    typeface = typeface,
                    keyboard = keyboard
                )::javaClass.name
            )
            .addToBackStack(null)
            .commit()
    }
}