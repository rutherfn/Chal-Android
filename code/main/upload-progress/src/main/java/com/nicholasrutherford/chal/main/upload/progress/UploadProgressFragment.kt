package com.nicholasrutherford.chal.main.upload.progress

import android.app.Application
import com.nicholasrutherford.chal.main.upload.progress.databinding.FragmentUploadProgressBinding
import com.nicholasrutherford.chal.ui.base_fragment.BaseFragment
import com.nicholasrutherford.chal.ui.typefaces.Typefaces
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UploadProgressFragment @Inject constructor() : BaseFragment<FragmentUploadProgressBinding>(
    FragmentUploadProgressBinding::inflate) {

    @Inject
    lateinit var typefaces: Typefaces

    @Inject
    lateinit var application: Application

    override fun updateTypefaces() {
    }

    override fun collectAlertAsUpdated() {
    }

    override fun onListener() {
    }

    override fun updateView() {
    }

}