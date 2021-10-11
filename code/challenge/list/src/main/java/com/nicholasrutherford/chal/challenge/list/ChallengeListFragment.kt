package com.nicholasrutherford.chal.challenge.list

import android.app.Application
import android.os.Bundle
import android.view.View
import com.nicholasrutherford.chal.challenge.list.databinding.FragmentChallengeListBinding
import com.nicholasrutherford.chal.ui.base_fragment.BaseFragment
import com.nicholasrutherford.chal.ui.typefaces.Typefaces
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ChallengeListFragment @Inject constructor(): BaseFragment<FragmentChallengeListBinding>(
    FragmentChallengeListBinding::inflate) {

    @Inject
    lateinit var typefaces: Typefaces

    @Inject
    lateinit var application: Application

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