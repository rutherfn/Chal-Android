package com.nicholasrutherford.chal.ext.activitys

import com.nicholasrutherford.chal.data.responses.ActiveChallengeAndCategoryResponse
import com.nicholasrutherford.chal.databinding.FragmentProgressUploadBinding

interface ProgressUploadExt {
    fun updateTypefaces(bind: FragmentProgressUploadBinding)
    fun updateSpinners(bind: FragmentProgressUploadBinding, challengeAndCategoryList: List<ActiveChallengeAndCategoryResponse>)
    fun clickListeners(bind: FragmentProgressUploadBinding)
    fun updateView(bind: FragmentProgressUploadBinding)
}
