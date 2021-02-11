package com.nicholasrutherford.chal.navigationimpl.bugreport

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.bugreport.BugReportFragment
import com.nicholasrutherford.chal.bugreport.BugReportNavigation

const val SEND_EMAIL_TYPE = "message/rfc822"

class BugReportNavigationImpl: BugReportNavigation {

    override fun sendEmailForBugReport(bugReportFragment: BugReportFragment, reporterName: String, bugTitle: String, bugDesc: String, priorityLevel: String) {

        try {
            val intent = Intent(Intent.ACTION_SEND)

            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(bugReportFragment.getString(R.string.dev_email)))
            intent.putExtra(Intent.EXTRA_SUBJECT, "$bugTitle ${bugReportFragment.getString(R.string.priority_level)} $priorityLevel")
            intent.putExtra(
                Intent.EXTRA_TEXT,
                "${bugReportFragment.getString(R.string.bug_descrption)} $bugDesc \n\n\n\t${bugReportFragment.getString(R.string.reporter_name)} $reporterName"
            )

            intent.type = SEND_EMAIL_TYPE
            bugReportFragment.startActivity(Intent.createChooser(intent, bugReportFragment.getString(R.string.sending_email)))
        }catch (t: Throwable) {
            t.printStackTrace()
        }
    }

    override fun showAlert(alertTitle: String, alertMessage: String, bugReportFragment: BugReportFragment, context: Context) {
        val alertDialogBuilder = AlertDialog.Builder(bugReportFragment.activity)

        alertDialogBuilder.setMessage(alertMessage)
            .setCancelable(false)
            .setPositiveButton(context.getString(R.string.ok)) { dialog, _ ->
                dialog.cancel()
            }

        val alert = alertDialogBuilder.create()

        alert.setTitle(alertTitle)
        alert.show()
    }
}