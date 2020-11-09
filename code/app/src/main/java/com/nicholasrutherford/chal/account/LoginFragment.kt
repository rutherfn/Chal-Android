package com.nicholasrutherford.chal.account

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.databinding.FragmentLoginBinding
import com.nicholasrutherford.chal.fragments.FragmentExt
import com.nicholasrutherford.chal.helpers.Helper
import com.nicholasrutherford.chal.helpers.Typeface

class LoginFragment(private val appContext: Context) : Fragment(), FragmentExt {
    private var typeface = Typeface()
    private var helper = Helper()
    private var binding: FragmentLoginBinding? = null
    private var viewModel = LoginViewModel(appContext)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater)
        bind()
        updateFragment()
        clickListeners()
        return binding?.root
    }

    override fun bind() {
    }

    override fun updateFragment() {
        updateTypefaces()
        updateColors()
    }

    override fun clickListeners() {

    }

    override fun containerId(): Int {
        return R.id.container
    }

    override fun updateTypefaces() {
       // viewModel.headerBold(binding?.tvTitle, appContext)
    }

    override fun updateColors() {

    }

}