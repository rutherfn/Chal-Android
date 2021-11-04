package com.nicholarutherford.chal.more.bugreport

import android.widget.ArrayAdapter

interface BugReportViewState {
    var reporterAdapter: ArrayAdapter<String>?
    var priorityAdapter: ArrayAdapter<String>?
}