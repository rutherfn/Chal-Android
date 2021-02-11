package com.nicholasrutherford.chal.bugreport

import android.widget.ArrayAdapter
import com.nicholasrutherford.chal.ViewState

interface BugReportViewState : ViewState {
    var profilePicture: String
    var username: String
    var reporterAdapter: ArrayAdapter<String>?
    var priorityAdapter: ArrayAdapter<String>?
}