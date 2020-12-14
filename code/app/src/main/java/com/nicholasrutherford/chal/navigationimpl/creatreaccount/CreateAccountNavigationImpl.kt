package com.nicholasrutherford.chal.navigationimpl.creatreaccount

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import cc.cloudist.acplibrary.ACProgressConstant
import cc.cloudist.acplibrary.ACProgressFlower
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.account.createaccount.CreateAccountNavigation
import com.nicholasrutherford.chal.account.createaccount.CreateAccountActivity
import com.nicholasrutherford.chal.account.login.LoginActivity
import com.nicholasrutherford.chal.account.uploadphoto.UploadPhotoActivity

class CreateAccountNavigationImpl : CreateAccountNavigation {

    private var flowerLoadingDialog: ACProgressFlower? = null

    override fun login(createAccountActivity: CreateAccountActivity) {
        val intent = Intent(createAccountActivity.applicationContext, LoginActivity::class.java)

        createAccountActivity.startActivity(intent)
        createAccountActivity.finish()
    }

    override fun uploadPhoto(userName: String, email: String, password: String, createAccountActivity: CreateAccountActivity) {
        val intent = Intent(createAccountActivity.applicationContext, UploadPhotoActivity::class.java)

        intent.putExtra("username", userName)
        intent.putExtra("email", email)
        intent.putExtra("password", password)

        createAccountActivity.startActivity(intent)
        createAccountActivity.finish()
    }

    override fun showAcProgress(createAccountActivity: CreateAccountActivity) {
        flowerLoadingDialog = ACProgressFlower.Builder(createAccountActivity)
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

    override fun errorCreate(errorMessageText: String, createAccountActivity: CreateAccountActivity) {
        val errorDialogBuilder = AlertDialog.Builder(createAccountActivity)

        errorDialogBuilder.setMessage(errorMessageText)

            .setCancelable(false)

            .setPositiveButton(createAccountActivity.getString(R.string.ok)) { dialog, _ ->
                dialog.cancel()
            }

        val errorAlert = errorDialogBuilder.create()

        errorAlert.setTitle(createAccountActivity.getString(R.string.error_cant_create_account))

        errorAlert.show()
    }

}