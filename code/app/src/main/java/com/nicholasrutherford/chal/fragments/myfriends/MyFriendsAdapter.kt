package com.nicholasrutherford.chal.fragments.myfriends

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nicholasrutherford.chal.databinding.FriendsListLayoutBinding

class MyFriendsAdapter(private val mContext: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FriendsListLayoutBinding.inflate(inflater, parent, false)
        return MyFriendsViewHolder(mContext, binding)
    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is MyFriendsViewHolder) {
            holder.bind(position)
        }
    }

}