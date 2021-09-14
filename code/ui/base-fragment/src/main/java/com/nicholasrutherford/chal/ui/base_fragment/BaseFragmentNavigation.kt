package com.nicholasrutherford.chal.ui.base_fragment

import android.app.AlertDialog
import android.graphics.Color
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import cc.cloudist.acplibrary.ACProgressConstant
import cc.cloudist.acplibrary.ACProgressFlower

class BaseFragmentNavigation(private val fragmentActivity: FragmentActivity) {

    private var flowerLoadingDialog: ACProgressFlower? = null

    fun showFlowerProgess() {
        flowerLoadingDialog = ACProgressFlower.Builder(fragmentActivity)
            .direction(ACProgressConstant.DIRECT_CLOCKWISE)
            .themeColor(Color.WHITE)
            .fadeColor(Color.DKGRAY).build()

        flowerLoadingDialog?.show()
    }

    fun hideFlowerProgress() = flowerLoadingDialog?.dismiss()

    fun showOkAlert(title: String, message: String) {
        val errorForgotPasswordDialogBuilder = AlertDialog.Builder(fragmentActivity)

        errorForgotPasswordDialogBuilder.setMessage(message)
            .setCancelable(false)
            .setPositiveButton(fragmentActivity.getString(R.string.ok)) { dialog, _ ->
                dialog.cancel()
            }

        val errorAlert = errorForgotPasswordDialogBuilder.create()
        errorAlert.setTitle(title)

        errorAlert.show()
    }

    fun showClosingOutAppProgressAlert(resId: Int, title: String, message: String) {
        val closingOutAppProgressAlertDialogBuilder = AlertDialog.Builder(fragmentActivity)

        closingOutAppProgressAlertDialogBuilder.setMessage(message)
            .setCancelable(false)
            .setPositiveButton(fragmentActivity.getString(R.string.no)) { dialog, _ ->
                dialog.cancel()
            }
            .setNegativeButton(fragmentActivity.getString(R.string.no)) { dialog, _ ->
                fragmentActivity.findNavController(resId).popBackStack()
            }

        val appProgressAlert = closingOutAppProgressAlertDialogBuilder.create()
        appProgressAlert.setTitle(title)

        appProgressAlert.show()
    }
}