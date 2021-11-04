package com.nicholarutherford.chal.more.bugreport

import android.app.Application
import android.widget.ArrayAdapter
import androidx.hilt.lifecycle.ViewModelInject
import com.nicholarutherford.chal.more.R
import com.nicholasrutherford.chal.data.elert.AlertType
import com.nicholasrutherford.chal.main.navigation.Navigator
import com.nicholasrutherford.chal.ui.base_vm.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class BugReportViewModel @ViewModelInject constructor(
    private val application: Application,
    private val navigator: Navigator
) : BaseViewModel() {

    val _shouldCreateEmail = MutableStateFlow(false)
    val shouldCreateEmail: StateFlow<Boolean> = _shouldCreateEmail

    val viewState = BugReportViewStateImpl()

    init {
        initArrayAdapters()
    }

    private fun initArrayAdapters() {
        val qaTester1 = application.getString(R.string.qa_tester_1)
        val qaTester2 = application.getString(R.string.qa_tester_2)
        val qaTester3 = application.getString(R.string.qa_tester_3)

        viewState.reporterAdapter = ArrayAdapter(
            application,
            android.R.layout.simple_spinner_item, arrayOf(qaTester1,
                qaTester2,
                qaTester3
            ))
        viewState.reporterAdapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        val lowPriority = application.getString(R.string.low)
        val mediumPriority = application.getString(R.string.medium)
        val highPriority = application.getString(R.string.high)

        viewState.priorityAdapter = ArrayAdapter(
            application,
            android.R.layout.simple_spinner_item,
            arrayOf(lowPriority,
                mediumPriority,
                highPriority
            ))
        viewState.priorityAdapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        setViewStateAsUpdated()
    }

    fun onReportBugClicked(reporterName: String, bugTitle: String, bugDesc: String, bugPriorityLevel: String) {
        when {
            bugTitle.isEmpty() -> {
                onShowAlert(
                    title = application.getString(R.string.missing_title_message),
                    message = application.getString(R.string.missing_title_desc_message)
                )
            }
            bugDesc.isEmpty() -> {
                onShowAlert(
                    title = application.getString(R.string.missing_desc_message),
                    message = application.getString(R.string.missing_desc_alert_message)
                )
            }
            else -> {
                _shouldCreateEmail.value = true
            }
        }
    }

    fun onShowAlert(title: String, message: String) {
        setShouldSetAlertAsUpdated(
            title = title,
            message = message,
            type = AlertType.REGULAR_OK_ALERT,
            shouldCloseAppAfterDone = false
        )
    }

    fun onToolbarBackClicked() = navigator.navigateBack()

    inner class BugReportViewStateImpl : BugReportViewState {
        override var reporterAdapter: ArrayAdapter<String>? = null
        override var priorityAdapter: ArrayAdapter<String>? = null
    }
}