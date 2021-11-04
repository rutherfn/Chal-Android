package com.nicholarutherford.chal.more.bugreport

import android.app.Application
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.nicholarutherford.chal.more.R
import com.nicholarutherford.chal.more.databinding.FragmentBugReportBinding
import com.nicholasrutherford.chal.ui.base_fragment.BaseFragment
import com.nicholasrutherford.chal.ui.typefaces.Typefaces
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class BugReportFragment @Inject constructor() : BaseFragment<FragmentBugReportBinding>(
    FragmentBugReportBinding::inflate) {

    @Inject
    lateinit var typefaces: Typefaces

    @Inject
    lateinit var application: Application

    private val viewModel: BugReportViewModel by viewModels()

    // report info
    private var reporterName = ""
    private var bugTitle = ""
    private var bugDesc = ""
    private var bugPriorityLevel = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            collectViewStateResult(
                viewModel.viewStateUpdated,
                viewModel._viewStateUpdated)
        }
        lifecycleScope.launch {
            collectShouldShowProgressResult(
                viewModel.shouldShowProgress,
                viewModel._shouldShowProgress
            )
        }
        lifecycleScope.launch {
            collectShouldDismissProgressResult(
                viewModel.shouldDismissProgress,
                viewModel._shouldDismissProgress
            )
        }
        lifecycleScope.launch {
            collectShouldShowAlertResult(
                this@BugReportFragment.id,
                viewModel.alert,
                viewModel._alert
            )
        }
        lifecycleScope.launch {
            viewModel.shouldCreateEmail.collect { isCreateEmail ->
                if (isCreateEmail) {
                    showCreateEmailForBug(
                        reporterName = reporterName,
                        bugTitle = bugTitle,
                        bugDesc = bugDesc,
                        priorityLevel = bugPriorityLevel
                    )
                }
            }
        }
    }

    override fun updateTypefaces() {
        binding?.let { binding ->
            binding.tbBugReport.tbStock.setTitleTextAppearance(
                application,
                R.style.ToolbarTextAppearance
            )

            typefaces.setTextViewHeaderBoldTypeface(binding.clBugReport.tvBugReportTitle)

            typefaces.setTextViewSubHeaderBoldTypeface(binding.clBugReport.tvReporterName)
            typefaces.setTextViewSubHeaderBoldTypeface(binding.clBugReport.tvBugTitle)

            typefaces.setTextViewSubHeaderRegularTypeface(binding.clBugReport.etBugTitle)
            typefaces.setTextViewSubHeaderRegularTypeface(binding.clBugReport.etBugDescription)

            typefaces.setTextViewSubHeaderBoldTypeface(binding.clBugReport.tvBugDescription)
            typefaces.setTextViewSubHeaderBoldTypeface(binding.clBugReport.tvBugPriorityLevel)
        }
    }

    override fun collectAlertAsUpdated() = Unit

    override fun onListener() {
        binding?.let { binding ->
            binding.clBugReport.btnReportBug.setOnClickListener {
                reporterName = binding.clBugReport.spReporter.selectedItem.toString()
                bugTitle = binding.clBugReport.etBugTitle.text.toString()
                bugDesc = binding.clBugReport.etBugDescription.text.toString()
                bugPriorityLevel = binding.clBugReport.spPriorityLevel.selectedItem.toString()

                viewModel.onReportBugClicked(reporterName, bugTitle, bugDesc, bugPriorityLevel)
            }
            binding.tbBugReport.tbStock.setOnClickListener {
                viewModel.onToolbarBackClicked()
            }
        }
    }

    override fun updateView() {
        binding?.let { binding ->
            binding.tbBugReport.tbStock.setNavigationIcon(viewModel.toolbarBackImage)
            binding.tbBugReport.tbStock.title =
                application.getString(R.string.report_bug)

            binding.clBugReport.spPriorityLevel.adapter = viewModel.viewState.priorityAdapter
            binding.clBugReport.spReporter.adapter = viewModel.viewState.reporterAdapter
        }
    }
}