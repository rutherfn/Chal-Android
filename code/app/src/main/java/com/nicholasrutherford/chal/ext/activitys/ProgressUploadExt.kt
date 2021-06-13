package com.nicholasrutherford.chal.ext.activitys

import com.nicholasrutherford.chal.databinding.FragmentProgressUploadBinding
import com.nicholasrutherford.chal.progressupload.ProgressUploadViewModel

interface ProgressUploadExt {
    fun main(bind: FragmentProgressUploadBinding, viewModel: ProgressUploadViewModel)
    fun updateTypefaces(bind: FragmentProgressUploadBinding, viewModel: ProgressUploadViewModel)
    fun updateSpinners(bind: FragmentProgressUploadBinding, viewModel: ProgressUploadViewModel, challengeAndCategoryList: List<ProgressUploadViewModel.ActiveChallengeAndCategoryResponse>)
    fun clickListeners(bind: FragmentProgressUploadBinding, viewModel: ProgressUploadViewModel)
}
