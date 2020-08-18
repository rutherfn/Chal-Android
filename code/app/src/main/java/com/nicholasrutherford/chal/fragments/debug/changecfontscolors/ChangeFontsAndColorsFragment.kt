package com.nicholasrutherford.chal.fragments.debug.changecfontscolors

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.databinding.FragmentFontsColorsBinding
import com.nicholasrutherford.chal.fragments.FragmentExt
import com.nicholasrutherford.chal.viewmodels.ChangeFontsAndColorsViewModel

class ChangeFontsAndColorsFragment : Fragment(), FragmentExt {

    private var binding: FragmentFontsColorsBinding? = null
    private var viewModel: ChangeFontsAndColorsViewModel? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentFontsColorsBinding.inflate(layoutInflater)
        bind()
        updateFragment()
        clickListeners()
        return binding?.root
    }


    override fun bind() {
    }

    override fun updateFragment() {
    }

    override fun clickListeners() {
    }

    override fun containerId(): Int {
        return R.id.container
    }

}