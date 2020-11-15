package com.nicholasrutherford.chal.navigationimpl

import android.content.Intent
import android.graphics.Color
import cc.cloudist.acplibrary.ACProgressConstant
import cc.cloudist.acplibrary.ACProgressFlower
import com.nicholasrutherford.chal.account.createaccount.CreateAccountNavigation
import com.nicholasrutherford.chal.account.createaccount.CreateAccountActivity
import com.nicholasrutherford.chal.activitys.accounts.UploadPhotoActivity

class CreateAccountNavigationImpl : CreateAccountNavigation {

    private var flowerLoadingDialog: ACProgressFlower? = null

    override fun signUp(createAccountActivity: CreateAccountActivity) {
        val intent = Intent(createAccountActivity.applicationContext, CreateAccountActivity::class.java)

        createAccountActivity.startActivity(intent)
        createAccountActivity.finish()
    }

    override fun uploadPhoto(userName: String, email: String, password: String, createAccountActivity: CreateAccountActivity) {
        val intent = Intent(createAccountActivity.applicationContext, UploadPhotoActivity::class.java)

        intent.putExtra("userName", userName)
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

}