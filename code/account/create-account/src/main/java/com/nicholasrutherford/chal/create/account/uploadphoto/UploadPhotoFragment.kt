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
import com.nicholasrutherford.chal.create.account.R
import com.nicholasrutherford.chal.helper.constants.EMAIL
import com.nicholasrutherford.chal.helper.constants.PASSWORD
import com.nicholasrutherford.chal.helper.constants.USERNAME
import com.nicholasrutherford.chal.ui.base_fragment.ROTATE_IMAGE_360
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
        lifecycleScope.launch {
            collectIsGalleryResult()
        }
        lifecycleScope.launch {
            collectIsTakePictureResult()
        }
        lifecycleScope.launch {
            collectShouldShowAlertResult(
                this@UploadPhotoFragment.id,
                viewModel.alert,
                viewModel._alert
            )
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.onImageUpdate()
    }

    override fun collectAlertAsUpdated() = Unit

    override fun updateTypefaces() {
        binding?.let { binding ->
            typeface.setTextViewHeaderBoldTypeface(binding.tvTakeAPictureOrChooseFromLibrary)
            binding.tbUploadPhoto.tbStock.setTitleTextAppearance(
                application,
                R.style.ToolbarTextAppearance
            )
            typeface.setTextViewBodyBoldTypeface(binding.btnContinueUpload)
        }
    }

    override fun onListener() {
        binding?.let { binding ->
            binding.cvTakeAPhoto.setOnClickListener {
                viewModel.updateIsPhotoReadyToBeUpdated(true)
            }
            binding.btnContinueUpload.setOnClickListener {
                viewModel.onContinueClicked()
            }
        }
    }

    override fun updateView() {
        binding?.let { binding ->
            binding.tbUploadPhoto.tbStock.title = viewModel.viewState.toolbarText
            viewModel.viewState.imageTakeAPhotoBitmap?.let { bitmap ->
                binding.cvTakeAPhoto.setImageBitmap(bitmap.rotate(ROTATE_IMAGE_360))
            }
        }
    }

}