package com.nicholasrutherford.chal.main.upload.progress

import android.R
import android.app.Application
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.nicholasrutherford.chal.main.upload.progress.databinding.FragmentUploadProgressBinding
import com.nicholasrutherford.chal.ui.base_fragment.BaseFragment
import com.nicholasrutherford.chal.ui.base_fragment.ROTATE_IMAGE_360
import com.nicholasrutherford.chal.ui.typefaces.Typefaces
import con.nicholasrutherford.chal.data.challenges.ActiveChallengesListResponse
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class UploadProgressFragment @Inject constructor() : BaseFragment<FragmentUploadProgressBinding>(
    FragmentUploadProgressBinding::inflate) {

    @Inject
    lateinit var typefaces: Typefaces

    @Inject
    lateinit var application: Application

    private val listOfChallenges = arrayListOf<String>()

    private val viewModel: UploadProgressViewModel by viewModels()

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
                this@UploadProgressFragment.id,
                viewModel.alert,
                viewModel._alert
            )
        }
        lifecycleScope.launch {
            viewModel.allUserActiveChallengesList.collect { userActiveChallengeList ->
                updateSpinners(userActiveChallengeList)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.onImageUpdate()
    }

    override fun updateTypefaces() {
        binding?.let { binding ->
          //  typefaces.setTextViewHeaderBoldTypeface(binding.tbUploadProgress.tb)

            typefaces.setTextViewHeaderBoldTypeface(binding.clPostProgress.tvPostProgress)

            typefaces.setTextViewSubHeaderRegularTypeface(binding.clPostProgress.tvSelectChallenge)
            typefaces.setTextViewSubHeaderRegularTypeface(binding.clPostProgress.tvAddCaption)
            typefaces.setTextViewSubHeaderRegularTypeface(binding.clPostProgress.tvUploadImage)

            typefaces.setTextViewSubHeaderRegularTypeface(binding.clPostProgress.tvUploadImage)
            typefaces.setTextViewSubHeaderRegularTypeface(binding.clPostProgress.btnCancelAndDiscardPost)
        }
    }

    fun updateSpinners(challengeList: List<ActiveChallengesListResponse>) {
        binding?.let { binding ->
            challengeList.forEach { data ->
                listOfChallenges.add(data.activeChallenges?.name ?: "")
            }

            val challengesAdapter = ArrayAdapter(
                application.applicationContext,
                R.layout.simple_spinner_item,
                listOfChallenges
            )
            challengesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            binding.clPostProgress.spSelectChallenge.adapter = challengesAdapter

//            if (params.isUpdate) {
//                listOfChallenges.forEachIndexed { index, challenges ->
//                    if (params.title == challenges) {
//                        bind.clPostProgress.spSelectChallenge.setSelection(index)
//                    }
//                }
//            }
        }
    }

    override fun collectAlertAsUpdated() = Unit

    private fun clearUI() {
        binding?.let { binding ->
            binding.clPostProgress.etAddCaption.setText("")
            binding.clPostProgress.ivUploadImage.setImageBitmap(null)
        }
    }

    override fun onListener() {
        binding?.let { binding ->
            binding.clPostProgress.btnCancelAndDiscardPost.setOnClickListener {
                viewModel.onDiscardPostClicked()
            }

            binding.clPostProgress.ivUploadImage.setOnClickListener {
                viewModel.updateIsPhotoReadyToBeUpdated(true)
                openGallery()
            }

            binding.tbUploadProgress.tbStock.setOnClickListener {
                viewModel.onBackClicked()
            }

            binding.clPostProgress.btnPostProgressToMyFeed.setOnClickListener {
                val title = binding.clPostProgress.spSelectChallenge.selectedItem.toString()
                val caption = binding.clPostProgress.etAddCaption.text.toString()
                viewModel.onPostProgressClicked(title, caption, listOfChallenges)
            }

        }
    }

    override fun updateView() {
        binding?.let { binding ->

            if (viewModel.viewState.isClearingUI) {
                clearUI()
            }

            binding.tbUploadProgress.tbStock.setNavigationIcon(viewModel.toolbarBackImage)
            binding.tbUploadProgress.tbStock.title =
                viewModel.viewState.toolbarTitle

            viewModel.viewState.imageTakeAPhotoBitmap?.let { bitmap ->
                binding.clPostProgress.ivUploadImage.setImageBitmap(bitmap.rotate(ROTATE_IMAGE_360))
            }
        }
    }

}