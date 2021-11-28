package com.nicholasrutherford.chal.challenge.manager.home

import android.app.Application
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.nicholasrutherford.chal.challenge.manager.databinding.ChallengeManagerHomeFragmentBinding
import com.nicholasrutherford.chal.ui.base_fragment.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ChallengeManagerHomeFragment @Inject constructor() : BaseFragment<ChallengeManagerHomeFragmentBinding>(
    ChallengeManagerHomeFragmentBinding::inflate) {

    @Inject
    lateinit var application: Application

    private val viewModel: ChallengeManagerHomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun updateTypefaces() {

    }

    override fun collectAlertAsUpdated() {
    }

    override fun onListener() {
    }

    override fun updateView() {
    }

}