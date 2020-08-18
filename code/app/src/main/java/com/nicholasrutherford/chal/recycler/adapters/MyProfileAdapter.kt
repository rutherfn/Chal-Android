package com.nicholasrutherford.chal.recycler.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nicholasrutherford.chal.databinding.ProfileChallengesBinding
import com.nicholasrutherford.chal.fragments.myprofile.MyProfileFragment
import com.nicholasrutherford.chal.recycler.viewholders.SettingsViewHolder
import com.nicholasrutherford.chal.fragments.myprofile.MyProfileViewModel

class MyProfileAdapter (private val viewModel: MyProfileViewModel, private val mContext: Context, private val fragment: MyProfileFragment) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ProfileChallengesBinding.inflate(inflater, parent, false)
        return SettingsViewHolder(binding, viewModel, mContext, fragment)
    }

    override fun getItemCount(): Int {
        return 18
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is SettingsViewHolder) {
            holder.bind(position)
        }
    }

}