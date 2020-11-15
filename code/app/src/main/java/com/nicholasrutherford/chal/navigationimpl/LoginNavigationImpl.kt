package com.nicholasrutherford.chal.navigationimpl

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import cc.cloudist.acplibrary.ACProgressConstant
import cc.cloudist.acplibrary.ACProgressFlower
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.account.login.LoginActivity
import com.nicholasrutherford.chal.account.login.LoginNavigation
import com.nicholasrutherford.chal.activitys.MainActivity
import com.nicholasrutherford.chal.activitys.accounts.ForgotPasswordActivity
import com.nicholasrutherford.chal.activitys.accounts.SignUpActivity

class LoginNavigationImpl : LoginNavigation {

    private var flowerLoadingDialog: ACProgressFlower? = null

    override fun signUp(context: Context, loginActivity: LoginActivity) {
        val intent = Intent(context, SignUpActivity::class.java)

        loginActivity.startActivity(intent)
        loginActivity.finish()
    }

    override fun forgotPassword(context: Context, loginActivity: LoginActivity) {
        val intent = Intent(context, ForgotPasswordActivity::class.java)

        loginActivity.startActivity(intent)
        loginActivity.finish()
    }

    override fun errorLogin(errorMessageText: String, loginActivity: LoginActivity) {
        val errorDialogBuilder = AlertDialog.Builder(loginActivity)

        errorDialogBuilder.setMessage(errorMessageText)

            .setCancelable(false)

            .setPositiveButton(loginActivity.getString(R.string.ok)) { dialog, _ ->
                dialog.cancel()
            }

        val errorAlert = errorDialogBuilder.create()

        errorAlert.setTitle(loginActivity.getString(R.string.error_cant_log_in))

        errorAlert.show()
    }

    override fun showAcProgress(loginActivity: LoginActivity) {
        flowerLoadingDialog = ACProgressFlower.Builder(loginActivity)
            .direction(ACProgressConstant.DIRECT_CLOCKWISE)
            .themeColor(Color.WHITE)
            .fadeColor(Color.DKGRAY).build()

        flowerLoadingDialog?.let {acProgressFlower ->
            acProgressFlower.show()
        }
    }

    override fun hideAcProgress() {
        flowerLoadingDialog?.let {acProgressFlower ->
            acProgressFlower.dismiss()
        }
    }

    override fun loginToApp(loginActivity: LoginActivity) {
        val intent = Intent(loginActivity.applicationContext, MainActivity::class.java)
        loginActivity.startActivity(intent)
        loginActivity.finish()
    }

}