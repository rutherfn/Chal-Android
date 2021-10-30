package com.nicholasrutherford.chal.profile

import android.app.Application
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nicholasrutherford.chal.helper.fragment.visibleOrGone
import com.nicholasrutherford.chal.profile.databinding.FragmentProfileBinding
import com.nicholasrutherford.chal.ui.base_fragment.BaseFragment
import com.nicholasrutherford.chal.ui.typefaces.Typefaces
import com.squareup.picasso.Picasso
import con.nicholasrutherford.chal.data.challenges.ActiveChallengesListResponse
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment @Inject constructor(): BaseFragment<FragmentProfileBinding>(
    FragmentProfileBinding::inflate) {

    @Inject
    lateinit var typefaces: Typefaces

    @Inject
    lateinit var application: Application

    private var profileListAdapter: ProfileListAdapter? = null

    private val viewModel: ProfileViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            collectViewStateResult(viewModel.viewStateUpdated, viewModel._viewStateUpdated)
        }
        lifecycleScope.launch {
            viewModel.allUserActiveChallengesList.collect { challengesList ->
                if (challengesList.isNotEmpty()) {
                    viewModel.updateActiveChallengesSize(challengesList.size)
                    bindProfileListAdapter(challengesList)
                }
            }
        }
    }

    private fun bindProfileListAdapter(activeChallengesList: List<ActiveChallengesListResponse>) {
        binding?.let { binding ->
            binding.rvChallenges.isNestedScrollingEnabled = false
            binding.rvChallenges.layoutManager =
                LinearLayoutManager(application.applicationContext)

            profileListAdapter = ProfileListAdapter(
                application = application,
                typefaces = typefaces,
                activeChallengesListResponse = activeChallengesList,
                viewModel = viewModel
            )

            binding.rvChallenges.adapter = profileListAdapter
        }
    }

    override fun updateTypefaces() {
        binding?.let { binding ->
            binding.tbProfilePost.tbStock.setTitleTextAppearance(
                application,
                R.style.ToolbarTextAppearance
            )

            typefaces.setTextViewHeaderRegularTypeface(binding.clProfile.tvProfileEdit)

            typefaces.setTextViewSubHeaderBoldTypeface(binding.clProfile.tvCurrentProfileName)
            typefaces.setTextViewSubHeaderRegularTypeface(binding.clProfile.tvCurrentProfileSubTitle)
            typefaces.setTextViewSubHeaderRegularTypeface(binding.clProfile.tvProfileMyChallenges)
            typefaces.setTextViewSubHeaderRegularTypeface(binding.clProfile.tvProfileMyFriends)

            typefaces.setTextViewBodyRegularTypeface(binding.clProfile.tvDescription)

        }
    }

    override fun collectAlertAsUpdated() {
    }

    override fun onListener() {
        binding?.let { binding ->
            binding.tbProfilePost.tbStock.setOnClickListener { viewModel.onToolbarBackClicked() }
            binding.clProfile.tvProfileEdit.setOnClickListener { viewModel.onEditProfileClicked() }
        }
    }

    override fun updateView() {
        binding?.let { binding ->

            binding.tbProfilePost.tbStock.title = "My Profile"

            // place holder for now
            Picasso.get().load("https://tsico.com/wp-content/uploads/2019/05/3-Unique-Debt-Collection-Challenges.jpg")
                .into(binding.clProfile.ivProfile)

            val options = RequestOptions()
                .placeholder(R.drawable.circle)
                .error(R.drawable.circle)

            Glide.with(this).load(viewModel.viewState.profileImage).apply(options)
                .into(binding.clProfile.cvCurrentProfilePic)

            binding.clProfile.tvCurrentProfileName.text = viewModel.viewState.username
            binding.clProfile.tvDescription.text = viewModel.viewState.description

            binding.tbProfilePost.tbStock.setNavigationIcon(viewModel.toolbarBackImage)

            binding.clProfile.btnActive.text = application.getString(R.string.active_x, viewModel.viewState.activeChallengesSize)
        }
    }


}