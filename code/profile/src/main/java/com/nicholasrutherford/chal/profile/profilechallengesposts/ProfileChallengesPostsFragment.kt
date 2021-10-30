package com.nicholasrutherford.chal.profile.profilechallengesposts

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.nicholasrutherford.chal.data.post.PostListResponse
import com.nicholasrutherford.chal.helper.constants.INDEX
import com.nicholasrutherford.chal.profile.databinding.FragmentProfileBinding
import com.nicholasrutherford.chal.profile.databinding.FragmentProfileChallengesPostsBinding
import com.nicholasrutherford.chal.ui.base_fragment.BaseFragment
import com.nicholasrutherford.chal.ui.typefaces.Typefaces
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ProfileChallengesPostsFragment @Inject constructor(): BaseFragment<FragmentProfileChallengesPostsBinding>(
    FragmentProfileChallengesPostsBinding::inflate) {

    @Inject
    lateinit var typefaces: Typefaces

    @Inject
    lateinit var application: Application

    private var profileChallengesPostAdapter: ProfileChallengesPostsAdapter? = null

    private val viewModel: ProfileChallengesPostViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.index = arguments?.getString(INDEX) ?: "0"
        lifecycleScope.launch {
            collectViewStateResult(viewModel.viewStateUpdated, viewModel._viewStateUpdated)
        }
        lifecycleScope.launch {
            viewModel.postList.collect { newsFeedList ->
                bindListAdapter(newsFeedList)
            }
        }

        viewModel.fetchAllUserPosts()
    }

    fun bindListAdapter(newsFeedList: List<PostListResponse>) {
        binding?.let { binding ->
            binding.rvChallengesPosts.isNestedScrollingEnabled =
                false
            binding.rvChallengesPosts.layoutManager = LinearLayoutManager(activity)

            profileChallengesPostAdapter =
                ProfileChallengesPostsAdapter(application, newsFeedList.reversed(), typefaces)
            binding.rvChallengesPosts.adapter = profileChallengesPostAdapter
        }
    }

    override fun updateTypefaces() = Unit

    override fun collectAlertAsUpdated() = Unit

    override fun onListener() {
        binding?.let { binding ->
            binding.tbProfileChallengesPosts.setOnClickListener { viewModel.onNavigateBack() }
        }
    }

    override fun updateView() {
        binding?.let { binding ->
            binding.tbProfileChallengesPosts.setNavigationIcon(viewModel.toolbarBackImage)
            binding.tbProfileChallengesPosts.title = "Challenge Posts"
        }
    }
}