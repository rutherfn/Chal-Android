package com.nicholasrutherford.chal.ext.activitys

import com.nicholasrutherford.chal.databinding.ActivityProgressUploadBinding
import com.nicholasrutherford.chal.progressupload.ProgressUploadViewModel

interface ProgressUploadExtension {
    fun main(bind: ActivityProgressUploadBinding, viewModel: ProgressUploadViewModel)
    fun updateTypefaces(bind: ActivityProgressUploadBinding, viewModel: ProgressUploadViewModel)
    fun containerId(): Int
    fun updateSpinners(bind: ActivityProgressUploadBinding, viewModel: ProgressUploadViewModel, challengeAndCategoryList: List<ProgressUploadViewModel.ActiveChallengeAndCategoryResponse>)
    fun clickListeners(bind: ActivityProgressUploadBinding, viewModel: ProgressUploadViewModel)
}
