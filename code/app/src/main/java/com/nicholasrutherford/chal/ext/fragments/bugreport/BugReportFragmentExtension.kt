package com.nicholasrutherford.chal.ext.fragments.bugreport

import com.nicholasrutherford.chal.bugreport.BugReportViewModel
import com.nicholasrutherford.chal.databinding.FragmentBugReportBinding

interface BugReportFragmentExtension {
    fun updateTypefaces(bind: FragmentBugReportBinding)
    fun clickListeners(bind: FragmentBugReportBinding, viewModel: BugReportViewModel)
    fun containerId(): Int
    fun updateView(bind: FragmentBugReportBinding, viewModel: BugReportViewModel)
}