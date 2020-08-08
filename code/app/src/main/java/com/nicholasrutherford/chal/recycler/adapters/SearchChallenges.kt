package com.nicholasrutherford.chal.recycler.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.data.responses.ChallengeResponse
import com.nicholasrutherford.chal.recycler.viewholders.SearchChallengesViewHolder

class SearchChallenges(private val mContext: Context, private val searchChallengesList: MutableList<ChallengeResponse>?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.search_challenges, parent, false)
        return SearchChallengesViewHolder(
            itemView,
            mContext,
           searchChallengesList
        )
    }

    override fun getItemCount(): Int {
        return 9
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is SearchChallengesViewHolder) {
            holder.main(mContext, position, searchChallengesList)
        }
    }

}