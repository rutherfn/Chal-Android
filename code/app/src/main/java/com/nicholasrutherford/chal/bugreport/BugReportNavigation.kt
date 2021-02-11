package com.nicholasrutherford.chal.bugreport

import android.content.Context

interface BugReportNavigation {
    fun sendEmailForBugReport(bugReportFragment: BugReportFragment, reporterName: String, bugTitle: String, bugDesc: String, priorityLevel: String )
    fun showAlert(alertTitle: String, alertMessage: String, bugReportFragment: BugReportFragment, context: Context)
}