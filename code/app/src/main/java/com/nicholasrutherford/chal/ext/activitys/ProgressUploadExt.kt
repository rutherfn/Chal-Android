package com.nicholasrutherford.chal.ext.activitys

import com.nicholasrutherford.chal.data.responses.ActiveChallengeResponse
import com.nicholasrutherford.chal.databinding.FragmentProgressUploadBinding

interface ProgressUploadExt {
    fun updateSelectedPhotoUri()
    fun updateTypefaces(bind: FragmentProgressUploadBinding)
    fun updateSpinners(bind: FragmentProgressUploadBinding, challengeAndCategoryList: List<ActiveChallengeResponse>)
    fun clickListeners(bind: FragmentProgressUploadBinding)
    fun updateView(bind: FragmentProgressUploadBinding)
}
