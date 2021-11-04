package com.nicholasrutherford.chal.ui.base_fragment

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import cc.cloudist.acplibrary.ACProgressConstant
import cc.cloudist.acplibrary.ACProgressFlower

const val SEND_EMAIL_TYPE = "message/rfc822"

class BaseFragmentNavigation(private val fragmentActivity: FragmentActivity) {

    private var flowerLoadingDialog: ACProgressFlower? = null

    private fun finish() = fragmentActivity.finish()

    private fun navigateUp(id: Int) = fragmentActivity.findNavController(id).navigateUp()

    fun showFlowerProgess() {
        flowerLoadingDialog = ACProgressFlower.Builder(fragmentActivity)
            .direction(ACProgressConstant.DIRECT_CLOCKWISE)
            .themeColor(Color.WHITE)
            .fadeColor(Color.DKGRAY).build()

        flowerLoadingDialog?.show()
    }

    fun hideFlowerProgress() = flowerLoadingDialog?.dismiss()

    fun showCreateEmailForBug(reporterName: String, bugTitle: String, bugDesc: String, priorityLevel: String) {
        try {
            val intent = Intent(Intent.ACTION_SEND)

            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(fragmentActivity.getString(R.string.dev_email)))
            intent.putExtra(Intent.EXTRA_SUBJECT, "$bugTitle ${fragmentActivity.getString(R.string.priority_level)} $priorityLevel")
            intent.putExtra(
                Intent.EXTRA_TEXT,
                "${fragmentActivity.getString(R.string.bug_descrption)} $bugDesc \n\n\n\t${fragmentActivity.getString(R.string.reporter_name)} $reporterName"
            )

            intent.type = SEND_EMAIL_TYPE
            fragmentActivity.startActivity(Intent.createChooser(intent, fragmentActivity.getString(R.string.sending_email)))
        } catch (t: Throwable) {
            t.printStackTrace()
        }
    }

    fun showOkAlert(title: String?, message: String?, shouldCloseApp: Boolean) {
        val errorForgotPasswordDialogBuilder = AlertDialog.Builder(fragmentActivity)

        errorForgotPasswordDialogBuilder.setMessage(message)
            .setCancelable(false)
            .setPositiveButton(fragmentActivity.getString(R.string.ok)) { dialog, _ ->
                if (shouldCloseApp) {
                    dialog.cancel()
                    finish()
                } else {
                    dialog.cancel()
                }
            }

        val errorAlert = errorForgotPasswordDialogBuilder.create()
        errorAlert.setTitle(title)

        errorAlert.show()
    }

    fun showYesAlert(resId: Int, title: String?, message: String?, shouldCloseApp: Boolean) {
        val closingOutAppProgressAlertDialogBuilder = AlertDialog.Builder(fragmentActivity)

        closingOutAppProgressAlertDialogBuilder.setMessage(message)
            .setCancelable(false)
            .setPositiveButton(fragmentActivity.getString(R.string.yes)) { dialog, _ ->
                if (shouldCloseApp) {
                    dialog.cancel()
                    finish()
                } else {
                    dialog.cancel()
                    navigateUp(resId)
                }
            }

        val appProgressAlert = closingOutAppProgressAlertDialogBuilder.create()
        appProgressAlert.setTitle(title)

        appProgressAlert.show()
    }

    fun showYesAndNoAlert(resId: Int, title: String?, message: String?) {
        val closingOutAppProgressAlertDialogBuilder = AlertDialog.Builder(fragmentActivity)

        closingOutAppProgressAlertDialogBuilder.setMessage(message)
            .setCancelable(false)
            .setPositiveButton(fragmentActivity.getString(R.string.yes)) { dialog, _ ->
                dialog.cancel()
                navigateUp(resId)
            }
            .setNegativeButton(fragmentActivity.getString(R.string.no)) { dialog, _ ->
                dialog.cancel()
            }

        val appProgressAlert = closingOutAppProgressAlertDialogBuilder.create()
        appProgressAlert.setTitle(title)

        appProgressAlert.show()
    }
}