package com.nicholasrutherford.chal.recycler.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.models.responses.ChallengeResponse
import com.nicholasrutherford.chal.recycler.viewholders.ActiveChallengesViewHolder

class ActiveChallenges(private val mContext: Context, private val activeChallengesList: MutableList<ChallengeResponse>?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.active_challenges_layout, parent, false)
        return ActiveChallengesViewHolder(
            itemView,
            mContext,
            activeChallengesList
        )
    }

    override fun getItemCount(): Int {
        return activeChallengesList?.size ?: 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ActiveChallengesViewHolder) {
            holder.main(mContext, position, activeChallengesList)
        }
    }

}