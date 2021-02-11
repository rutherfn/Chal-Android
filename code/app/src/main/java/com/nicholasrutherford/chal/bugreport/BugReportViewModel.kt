package com.nicholasrutherford.chal.bugreport

import android.content.Context
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModel
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.firebase.read.ReadAccountFirebase
import com.nicholasrutherford.chal.navigationimpl.bugreport.BugReportNavigationImpl

class BugReportViewModel(private val bugReportFragment: BugReportFragment, private val appContext: Context)
        : ViewModel() {

    private val navigation = BugReportNavigationImpl()
    private val readProfileDetailsFirebase = ReadAccountFirebase(appContext)

    val viewState = BugReportViewStateImpl()

    init {
        fetchUsername()
        fetchProfilePicture()

        initReporterAdapter()
        initPriorityAdapter()
    }

    private fun fetchUsername() {
        readProfileDetailsFirebase.getUsername()?.let { username ->
            viewState.username = username
        }
    }

    private fun fetchProfilePicture() {
        readProfileDetailsFirebase.getUserProfilePicture()?.let { profilePicture ->
            viewState.profilePicture = profilePicture
        }
    }

    private fun initReporterAdapter() {
        val qaTester1 = appContext.getString(R.string.qa_tester_1)
        val qaTester2 = appContext.getString(R.string.qa_tester_2)
        val qaTester3 = appContext.getString(R.string.qa_tester_3)

        viewState.reporterAdapter = ArrayAdapter(appContext, android.R.layout.simple_spinner_item, arrayOf(qaTester1, qaTester2, qaTester3))
        viewState.reporterAdapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    }

    private fun initPriorityAdapter() {
        val lowPriority = appContext.getString(R.string.low)
        val mediumPriority = appContext.getString(R.string.medium)
        val highPriority = appContext.getString(R.string.high)

        viewState.priorityAdapter = ArrayAdapter(appContext, android.R.layout.simple_spinner_item, arrayOf(lowPriority, mediumPriority, highPriority))
        viewState.priorityAdapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    }

    fun onReportBugButtonClicked(reporterName: String, bugTitle: String, bugDesc: String, bugPriorityLevel: String) {
        when {
            bugTitle.isEmpty() -> {
                val missingBugTitle = appContext.getString(R.string.missing_title_message)
                val missingBugDesc = appContext.getString(R.string.missing_title_desc_message)
                navigation.showAlert(missingBugTitle, missingBugDesc, bugReportFragment,  appContext)
            }
            bugDesc.isEmpty() -> {
                val missingBugDesc = appContext.getString(R.string.missing_desc_message)
                val missingBugDescMessage = appContext.getString(R.string.missing_desc_alert_message)
                navigation.showAlert(missingBugDesc, missingBugDescMessage, bugReportFragment, appContext)
            }
            else -> {
                navigation.sendEmailForBugReport(bugReportFragment, reporterName, bugTitle, bugDesc, bugPriorityLevel)
            }
        }
    }

    inner class BugReportViewStateImpl: BugReportViewState {
        override var profilePicture: String = ""
        override var username: String = ""
        override var reporterAdapter: ArrayAdapter<String>? = null
        override var priorityAdapter: ArrayAdapter<String>? = null
    }

        }