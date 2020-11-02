package com.nicholasrutherford.chal.screens.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nicholasrutherford.chal.databinding.HomeWallLayoutBinding

class HomeAdapter(private val viewModel: HomeViewModel, private val mContext: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = HomeWallLayoutBinding.inflate(inflater, parent, false)
        return HomeViewHolder(binding, viewModel, mContext)
    }

    override fun getItemCount(): Int {
        return 3
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is HomeViewHolder) {
            holder.bind(position)
        }
    }

}