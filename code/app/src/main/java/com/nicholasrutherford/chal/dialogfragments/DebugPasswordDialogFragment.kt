package com.nicholasrutherford.chal.dialogfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.activitys.debug.DebugActivity
import com.nicholasrutherford.chal.databinding.DebugPasswordBinding
import com.nicholasrutherford.chal.fragments.FragmentExt
import com.nicholasrutherford.chal.helpers.visibleOrGone
import com.nicholasrutherford.chal.viewmodels.DebugPasswordViewModel
import kotlinx.android.synthetic.main.activity_main.view.*

class DebugPasswordDialogFragment : DialogFragment(), FragmentExt {

    // declarations
    var binding: DebugPasswordBinding? = null
    var viewModel: DebugPasswordViewModel? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DebugPasswordBinding.inflate(layoutInflater)
        viewModel = context?.let { activity?.let { it1 -> DebugPasswordViewModel(it1, it,this, DebugActivity()) } }
        bind()
        return binding?.root
    }

    override fun bind() {
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

    override fun updateColors() {
    }

    override fun updateTypefaces() {
    }

    override fun containerId(): Int {
        return R.id.container
    }


}