package com.nicholasrutherford.chal.profile

import android.app.Application
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.nicholasrutherford.chal.profile.databinding.FragmentProfileBinding
import com.nicholasrutherford.chal.ui.base_fragment.BaseFragment
import com.nicholasrutherford.chal.ui.typefaces.Typefaces
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment @Inject constructor(): BaseFragment<FragmentProfileBinding>(
    FragmentProfileBinding::inflate) {

    @Inject
    lateinit var typefaces: Typefaces

    @Inject
    lateinit var application: Application

    private val viewModel: ProfileViewModel by viewModels()

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