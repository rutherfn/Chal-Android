package com.nicholasrutherford.chal.splashredesign

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.nicholasrutherford.chal.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashRedesignFragment @Inject constructor() : Fragment() {

    private val viewModel: SplashRedesignViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val bind = FragmentSplashBinding.inflate(layoutInflater)

        viewModel.checkIfUserIsSignedIn()

        return bind.root
    }
}