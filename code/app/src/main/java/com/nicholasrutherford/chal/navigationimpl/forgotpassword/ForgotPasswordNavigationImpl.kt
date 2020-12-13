package com.nicholasrutherford.chal.navigationimpl.forgotpassword

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import cc.cloudist.acplibrary.ACProgressConstant
import cc.cloudist.acplibrary.ACProgressFlower
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.account.forgotpassword.ForgotPasswordActivity
import com.nicholasrutherford.chal.account.forgotpassword.ForgotPasswordNavigation
import com.nicholasrutherford.chal.account.login.LoginActivity

class ForgotPasswordNavigationImpl : ForgotPasswordNavigation {

    private var flowerLoadingDialog: ACProgressFlower? = null

    override fun showAcProgress(forgotPasswordActivity: ForgotPasswordActivity) {
        flowerLoadingDialog = ACProgressFlower.Builder(forgotPasswordActivity)
            .direction(ACProgressConstant.DIRECT_CLOCKWISE)
            .themeColor(Color.WHITE)
            .fadeColor(Color.DKGRAY).build()

        flowerLoadingDialog?.let {acProgressFlower ->
            acProgressFlower.show()
        }
    }

    override fun forgotPasswordAlert(title: String, message: String, forgotPasswordActivity: ForgotPasswordActivity) {
        val errorForgPasswordDialogBuilder = AlertDialog.Builder(forgotPasswordActivity)

        errorForgPasswordDialogBuilder.setMessage(message)
            .setCancelable(false)
            .setPositiveButton(forgotPasswordActivity.getString(R.string.ok)) { dialog, _ ->
                dialog.cancel()
            }

        val errorAlert = errorForgPasswordDialogBuilder.create()
        errorAlert.setTitle(title)

        errorAlert.show()
    }

    override fun hideAcProgress() {
        flowerLoadingDialog?.let {acProgressFlower ->
            acProgressFlower.dismiss()
        }
    }

    override fun login(appContext: Context, forgotPasswordActivity: ForgotPasswordActivity) {
        val intent = Intent(appContext, LoginActivity::class.java)
        forgotPasswordActivity.startActivity(intent)
        forgotPasswordActivity.finish()
    }

}