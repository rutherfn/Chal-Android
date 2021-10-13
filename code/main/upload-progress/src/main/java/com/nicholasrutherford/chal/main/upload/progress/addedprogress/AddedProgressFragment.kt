package com.nicholasrutherford.chal.main.upload.progress.addedprogress

import android.app.Application
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.nicholasrutherford.chal.helper.constants.CHALLENGE_ADDED_PROGRESS_DAY
import com.nicholasrutherford.chal.helper.constants.CHALLENGE_ADDED_PROGRESS_TITLE
import com.nicholasrutherford.chal.main.upload.progress.databinding.FragmentAddedProgressBinding
import com.nicholasrutherford.chal.ui.base_fragment.BaseFragment
import com.nicholasrutherford.chal.ui.typefaces.Typefaces
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class AddedProgressFragment @Inject constructor() : BaseFragment<FragmentAddedProgressBinding>(
    FragmentAddedProgressBinding::inflate) {

    @Inject
    lateinit var typefaces: Typefaces

    @Inject
    lateinit var application: Application

    var currentDay = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        currentDay = arguments?.getInt(CHALLENGE_ADDED_PROGRESS_DAY) ?: 0
        viewModel.setViewState(
            challengeTitle = arguments?.getString(CHALLENGE_ADDED_PROGRESS_TITLE) ?: "",
            challengeDay = currentDay
        )

        lifecycleScope.launch {
            collectViewStateResult(viewModel.viewStateUpdated, viewModel._viewStateUpdated)
        }
    }

    private val viewModel: AddedProgressViewModel by viewModels()

    override fun updateTypefaces() {

    }

    override fun collectAlertAsUpdated() = Unit

    override fun onListener() {
//        binding?.let { binding ->
//            binding.clAddedProgress.btnAddMoreProgress.setOnClickListener {
//                viewModel.onAddMoreProgressClicked()
//            }
//            binding.clAddedProgress.btnContinue.setOnClickListener {
//                viewModel.onContinueClicked()
//            }
//        }
    }

    override fun updateView() {
        binding?.let { binding ->
           // binding.tbAddedProgress.tbStock.title = viewModel.toolbarTitle

//            if (currentDay == 7) {
//                binding.clAddedProgress.tvProgressAddedDesc.text =
//                    "Congrats you added progress to ${viewModel.viewState.challengeTitle} . You are " +
//                            "currently on your ${viewModel.viewState.challengeDay} of your challenge. Which means you ompleted said challenge " +
//                            "Click continue to continue adding progress to another challenge, or stop in order to see your progression"
//            } else {
//                binding.clAddedProgress.tvProgressAddedDesc.text =
//                    "Congrats you added progress to ${viewModel.viewState.challengeTitle} . You are " +
//                            "currently on your ${viewModel.viewState.challengeDay} of your challenge. " +
//                            "Click continue to continue posting progress, or stop in order to see your progression"
//            }
        }
    }
}