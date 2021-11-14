package com.nicholasrutherford.chal.profile.edit

import android.app.Application
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.nicholasrutherford.chal.profile.R
import com.nicholasrutherford.chal.profile.databinding.FragmentEditProfileBinding
import com.nicholasrutherford.chal.ui.base_fragment.BaseFragment
import com.nicholasrutherford.chal.ui.typefaces.Typefaces
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class EditProfileFragment @Inject constructor(): BaseFragment<FragmentEditProfileBinding>(
    FragmentEditProfileBinding::inflate){

    @Inject
    lateinit var typeface: Typefaces

    @Inject
    lateinit var application: Application

    private val viewModel: EditProfileViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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

    override fun updateTypefaces() {
        binding?.let { binding ->
            binding.tbEditProfile.tbStock.setTitleTextAppearance(
                application,
                R.style.ToolbarTextAppearance
            )

            typeface.setTextViewSubHeaderBoldTypeface(binding.clEditProfile.tvEditMyProfile)
            typeface.setTextViewBodyItalicTypeface(binding.clEditProfile.tvEditMyProfileDescription)

            typeface.setTextViewBodyBoldTypeface(binding.clEditProfile.tvEditProfileUsername)
            typeface.setTextViewBodyItalicTypeface(binding.clEditProfile.etEditProfileUsername)

            typeface.setTextViewBodyBoldTypeface(binding.clEditProfile.tvEditFirstName)
            typeface.setTextViewBodyItalicTypeface(binding.clEditProfile.etFirstName)

            typeface.setTextViewBodyBoldTypeface(binding.clEditProfile.tvLastName)
            typeface.setTextViewBodyItalicTypeface(binding.clEditProfile.etLastName)

            typeface.setTextViewBodyBoldTypeface(binding.clEditProfile.tvEditBio)
            typeface.setTextViewBodyItalicTypeface(binding.clEditProfile.etBio)

            typeface.setTextViewSubHeaderBoldTypeface(binding.clEditProfile.btnEditProfile)
            typeface.setTextViewSubHeaderRegularTypeface(binding.clEditProfile.tvCancelEditProfile)
        }
    }

    override fun collectAlertAsUpdated() = Unit

    override fun onListener() {
        binding?.let { binding ->
            binding.clEditProfile.tvCancelEditProfile.setOnClickListener { viewModel.onCancelAndDiscardChanges() }

            binding.clEditProfile.btnEditProfile.setOnClickListener {
                val username = binding.clEditProfile.etEditProfileUsername.text.toString()
                val firstName = binding.clEditProfile.etFirstName.text.toString()
                val lastName = binding.clEditProfile.etLastName.text.toString()
                val bio = binding.clEditProfile.etBio.text.toString()

                viewModel.onEditProfileClicked(username, firstName, lastName, bio)
            }
            binding.tbEditProfile.tbStock.setOnClickListener { viewModel.onToolbarBackClicked() }
        }
    }

    override fun updateView() {
        binding?.let { binding ->
            binding.tbEditProfile.tbStock.setNavigationIcon(viewModel.toolbarBackImage)

            // placeholder
            Glide.with(this)
                .load("https://tsico.com/wp-content/uploads/2019/05/3-Unique-Debt-Collection-Challenges.jpg")
                .into(binding.clEditProfile.ivEditMyProfile)

            binding.clEditProfile.etEditProfileUsername.setText(viewModel.viewState.username)
            binding.clEditProfile.etFirstName.setText(viewModel.viewState.firstName)
            binding.clEditProfile.etLastName.setText(viewModel.viewState.lastName)
            binding.clEditProfile.etBio.setText(viewModel.viewState.bio)

            binding.tbEditProfile.tbStock.title = viewModel.viewState.toolbarText
        }
    }
}