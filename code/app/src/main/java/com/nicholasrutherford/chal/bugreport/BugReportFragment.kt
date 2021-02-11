package com.nicholasrutherford.chal.bugreport

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.databinding.FragmentBugReportBinding
import com.nicholasrutherford.chal.ext.fragments.bugreport.BugReportFragmentExtension
import com.nicholasrutherford.chal.helpers.Typeface

class BugReportFragment(private val appContext: Context) : Fragment(), BugReportFragmentExtension {

    private var viewModel: BugReportViewModel? = null
    private val typeface = Typeface()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val bind = FragmentBugReportBinding.inflate(layoutInflater)
        viewModel = BugReportViewModel(this, appContext)

        viewModel?.let { bugReportViewModel ->
            updateView(bind, bugReportViewModel)
            clickListeners(bind, bugReportViewModel)
        }

        return bind.root
    }

    override fun updateTypefaces(bind: FragmentBugReportBinding) {
        typeface.setTypefaceForHeaderBold(bind.tbBugReport.tvTitle, appContext)

        typeface.setTypefaceForHeaderBold(bind.clBugReport.tvBugReportTitle, appContext)
        typeface.setTypefaceForSubHeaderBold(bind.clBugReport.tvReporterName, appContext)
        typeface.setTypefaceForSubHeaderBold(bind.clBugReport.tvBugTitle, appContext)
        typeface.setTypefaceForSubHeaderRegular(bind.clBugReport.etBugTitle, appContext)
        typeface.setTypefaceForSubHeaderBold(bind.clBugReport.tvBugDescription, appContext)
        typeface.setTypefaceForSubHeaderRegular(bind.clBugReport.etBugDescription, appContext)
        typeface.setTypefaceForSubHeaderBold(bind.clBugReport.tvBugPriorityLevel, appContext)
        typeface.setTypefaceForSubHeaderBold(bind.clBugReport.btnReportBug, appContext)
    }

    override fun containerId(): Int {
        return R.id.container
    }

    override fun updateView(bind: FragmentBugReportBinding, viewModel: BugReportViewModel) {
        updateTypefaces(bind)

        Glide.with(appContext).load(viewModel.viewState.profilePicture)
            .into(bind.tbBugReport.cvProfile)
        bind.tbBugReport.tvTitle.text = viewModel.viewState.username

        bind.clBugReport.spPriorityLevel.adapter = viewModel.viewState.priorityAdapter
        bind.clBugReport.spReporter.adapter = viewModel.viewState.reporterAdapter
    }

    override fun clickListeners(bind: FragmentBugReportBinding, viewModel: BugReportViewModel) {
        bind.clBugReport.btnReportBug.setOnClickListener {
            val reporterName = bind.clBugReport.spReporter.selectedItem.toString()
            val bugTitle = bind.clBugReport.etBugTitle.text.toString()
            val bugDesc = bind.clBugReport.etBugDescription.text.toString()
            val bugPriorityLevel = bind.clBugReport.spPriorityLevel.selectedItem.toString()

            viewModel.onReportBugButtonClicked(reporterName, bugTitle, bugDesc, bugPriorityLevel)
        }
    }
}