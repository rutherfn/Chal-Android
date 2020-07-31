package com.nicholasrutherford.chal.recycler.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.nicholasrutherford.chal.databinding.DebugLayoutBinding
import com.nicholasrutherford.chal.helpers.visibleOrGone
import com.nicholasrutherford.chal.viewmodels.DebugViewModel

class DebugViewHolder(private var binding: DebugLayoutBinding, private var viewModel: DebugViewModel) : RecyclerView.ViewHolder(binding.root) {

    fun main(position: Int) {
        binding.tvDebugEntry.text = viewModel.viewState.debugOptionsList[position].options
        binding.swDebugOption.visibleOrGone = viewModel.viewState.debugModeVisible
    }

}