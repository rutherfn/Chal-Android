package com.nicholasrutherford.chal.recycler.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.recycler.viewholders.ProfileSettingsViewHolder

class ProfileSettings(private val mContext: Context, private val listOfSubProfiles: ArrayList<String>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.settings_regular_layout, parent, false)
        return ProfileSettingsViewHolder(itemView, mContext, listOfSubProfiles)
    }

    override fun getItemCount(): Int {
        return listOfSubProfiles.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ProfileSettingsViewHolder) {
            holder.main(mContext, position, listOfSubProfiles)
        }
    }


}