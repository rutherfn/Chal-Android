package com.nicholasrutherford.chal.create.account.uploadphoto

import android.app.Application
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.nicholasrutherford.chal.create.account.databinding.UploadPhotoFragmentBinding
import com.nicholasrutherford.chal.ui.base_fragment.BaseFragment
import com.nicholasrutherford.chal.ui.typefaces.Typefaces
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UploadPhotoFragment @Inject constructor(): BaseFragment<UploadPhotoFragmentBinding>(
    UploadPhotoFragmentBinding::inflate) {

    @Inject
    lateinit var typeface: Typefaces

    @Inject
    lateinit var application: Application

    private val viewModel: UploadPhotoViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            collectViewStateResult(viewModel.viewStateUpdated, viewModel._viewStateUpdated)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.onImageUpdate()
    }

    override fun updateTypefaces() {
    }

    override fun collectAlertAsUpdated() {
    }

    override fun onListener() {
        binding.btnChooseFormLibrary.setOnClickListener {
            viewModel.updateIsPhotoReadyToBeUpdated(true)
            openGallery()
        }
        binding.cvTakeAPhoto.setOnClickListener {
            viewModel.updateIsPhotoReadyToBeUpdated(true)
            openCamera("Test", "Test two")
        }
    }

    override fun updateView() {
        viewModel.viewState.imageTakeAPhotoBitmap?.let { bitmap ->
            binding.cvTakeAPhoto.setImageBitmap(bitmap)
        }
    }
}