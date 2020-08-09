package com.nicholasrutherford.chal.fragments.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.nicholasrutherford.chal.databinding.DebugPasswordBinding
import com.nicholasrutherford.chal.fragments.Fragment
import com.nicholasrutherford.chal.helpers.visibleOrGone
import com.nicholasrutherford.chal.viewmodels.DebugPasswordViewModel

class DebugPasswordDialogFragment : DialogFragment(), Fragment {

    // declarations
    var binding: DebugPasswordBinding? = null
    var viewModel: DebugPasswordViewModel? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DebugPasswordBinding.inflate(layoutInflater)
        viewModel = context?.let { DebugPasswordViewModel(it,this) }
        bind()
        return binding?.root
    }

    private fun bind() {
        updateFragment()
        clickListeners()
    }

    override fun updateFragment() {
        binding?.ivErrorPasswordDebug?.visibleOrGone = viewModel?.viewState?.errorDisplayForUserVisible!!
        binding?.tvDebugIncorrectPassword?.visibleOrGone = viewModel?.viewState?.errorDisplayForUserVisible!!
    }

    override fun clickListeners() {
        binding?.btnDebugPassword?.setOnClickListener {
            binding?.etDebugPassword?.let { it1 -> viewModel?.onButtonDebugPasswordClicked(it1) }
            updateFragment()
            viewModel?.checkIfWereReadyToStartDebugActivity()
        }

        binding?.ivClose?.setOnClickListener {viewModel?.onCloseClicked()}
    }


}