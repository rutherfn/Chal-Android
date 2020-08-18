package com.nicholasrutherford.chal.recycler.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nicholasrutherford.chal.databinding.DebugLayoutBinding
import com.nicholasrutherford.chal.recycler.viewholders.DebugViewHolder
import com.nicholasrutherford.chal.fragments.debug.debugmenu.DebugViewModel

class DebugAdapter(private val viewModel: DebugViewModel, private val mContext: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DebugLayoutBinding.inflate(inflater, parent, false)
        return DebugViewHolder(binding, viewModel)
    }

    override fun getItemCount(): Int {
        return viewModel.viewState.debugOptionsList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is DebugViewHolder) {
            holder.main(position)
        }
    }


}