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
import androidx.navigation.fragment.findNavController
import com.nicholasrutherford.chal.create.account.EMAIL
import com.nicholasrutherford.chal.create.account.PASSWORD
import com.nicholasrutherford.chal.create.account.USERNAME
import kotlinx.coroutines.flow.collect
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
        viewModel.setParams(
            email = arguments?.getString(EMAIL),
            password = arguments?.getString(PASSWORD),
            username = arguments?.getString(USERNAME)
        )
        lifecycleScope.launch {
            collectViewStateResult(viewModel.viewStateUpdated, viewModel._viewStateUpdated)
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
    }

    override fun collectAlertAsUpdated() {
        lifecycleScope.launch {
            viewModel.shouldShowAlert.collect { isShouldShowAlert ->
                if (isShouldShowAlert) {
                    showOkAlert(title = viewModel.alertTitle, message = viewModel.alertMessage)
                }
                viewModel._shouldShowAlert.value = false
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.onImageUpdate()
    }

    override fun updateTypefaces() {
        typeface.setTextViewHeaderBoldTypeface(binding.tvTakeAPictureOrChooseFromLibrary)

        typeface.setTextViewBodyBoldTypeface(binding.btnChooseFormLibrary)
        typeface.setTextViewBodyBoldTypeface(binding.btnContinueUpload)
    }

    override fun onListener() {
        binding.btnChooseFormLibrary.setOnClickListener {
            viewModel.updateIsPhotoReadyToBeUpdated(true)
            openGallery()
        }
        binding.cvTakeAPhoto.setOnClickListener {
            viewModel.updateIsPhotoReadyToBeUpdated(true)
            openCamera()
        }
        binding.btnContinueUpload.setOnClickListener {
            viewModel.onContinueClicked()
        }
    }

    override fun updateView() {
        viewModel.viewState.imageTakeAPhotoBitmap?.let { bitmap ->
            binding.cvTakeAPhoto.setImageBitmap(bitmap)
        }
    }
}