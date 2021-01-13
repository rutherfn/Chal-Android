package com.nicholasrutherford.chal.bugreport

import com.nicholasrutherford.chal.ViewState

interface BugReportViewState : ViewState {
    var reporterName: String
    var bugTitle: String
    var bugDescrption: String
    var bugPriorityLevel: String
}