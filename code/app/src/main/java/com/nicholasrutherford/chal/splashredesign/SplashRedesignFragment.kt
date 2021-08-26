package com.nicholasrutherford.chal.splashredesign

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.nicholasrutherford.chal.databinding.FragmentSplashBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class SplashRedesignFragment @Inject constructor() : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)
            .get(SplashRedesignViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val bind = FragmentSplashBinding.inflate(layoutInflater)

        viewModel.checkIfUserIsSignedIn()

        return bind.root
    }
}