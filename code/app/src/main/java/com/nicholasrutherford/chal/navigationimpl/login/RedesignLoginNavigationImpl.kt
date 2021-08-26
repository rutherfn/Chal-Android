package com.nicholasrutherford.chal.navigationimpl.login

import android.app.AlertDialog
import android.app.Application
import android.content.Intent
import android.graphics.Color
import cc.cloudist.acplibrary.ACProgressConstant
import cc.cloudist.acplibrary.ACProgressFlower
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.account.forgotpassword.ForgotPasswordActivity
import com.nicholasrutherford.chal.account.redesignlogin.RedesignLoginViewNavigation
import com.nicholasrutherford.chal.account.signup.SignUpActivity
import com.nicholasrutherford.chal.main.MainActivity
import com.nicholasrutherford.chal.navigationimpl.challengeredesign.container
import com.nicholasrutherford.chal.newsfeed.NewsFeedFragment
import javax.inject.Inject

class RedesignLoginNavigationImpl @Inject constructor(
    private val application: Application,
    private val main: MainActivity
    ) : RedesignLoginViewNavigation {

    private var flowerLoadingDialog: ACProgressFlower? = null

    override fun signUp() {
        val intent = Intent(main.applicationContext, SignUpActivity::class.java)

        main.startActivity(intent)
        main.finish()
    }

    override fun forgotPassword() {
        val intent = Intent(main.applicationContext, ForgotPasswordActivity::class.java)

        main.startActivity(intent)
        main.finish()
    }

    override fun errorLogin(errorMessageText: String) {
        val errorDialogBuilder = AlertDialog.Builder(main)

        errorDialogBuilder.setMessage(errorMessageText)

            .setCancelable(false)

            .setPositiveButton(main.getString(R.string.ok)) { dialog, _ ->
                dialog.cancel()
            }

        val errorAlert = errorDialogBuilder.create()

        errorAlert.setTitle(main.getString(R.string.error_cant_log_in))

        errorAlert.show()
    }

    override fun showAcProgress() {
        flowerLoadingDialog = ACProgressFlower.Builder(main)
            .direction(ACProgressConstant.DIRECT_CLOCKWISE)
            .themeColor(Color.WHITE)
            .fadeColor(Color.DKGRAY).build()

        flowerLoadingDialog?.show()
    }

    override fun hideAcProgress() {
        flowerLoadingDialog?.dismiss()
    }

    override fun loginToApp() {
        main.supportFragmentManager.beginTransaction()
            .replace(
                container,
                NewsFeedFragment(application),
                NewsFeedFragment(application)::javaClass.name
            )
            .addToBackStack(null)
            .commit()
    }
}