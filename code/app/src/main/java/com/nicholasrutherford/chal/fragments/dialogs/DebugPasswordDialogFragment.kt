package com.nicholasrutherford.chal.fragments.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.nicholasrutherford.chal.databinding.DebugPasswordBinding

class DebugPasswordDialogFragment : DialogFragment() {
    var binding: DebugPasswordBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DebugPasswordBinding.inflate(layoutInflater)
        isCancelable = false
        bind()
        return binding?.root
    }

    private fun bind() {

    }

}