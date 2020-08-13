package com.nicholasrutherford.chal.recycler.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.data.responses.SearchPeople
import com.nicholasrutherford.chal.recycler.viewholders.SuggestedFriendsViewHolder
import com.nicholasrutherford.chal.viewmodels.SearchPeopleViewModel
import java.util.*
import kotlin.collections.ArrayList

class SuggestedFriends(private val mContext: Context, private val searchPeopleList: ArrayList<SearchPeople>, private var viewModel: SearchPeopleViewModel?) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {

    var filterPeopleList = ArrayList<SearchPeople>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.suggested_friends_layout, parent, false)
        return SuggestedFriendsViewHolder(
            itemView,
            mContext,
            searchPeopleList,
            filterPeopleList,
            viewModel
        )
    }

    override fun getItemCount(): Int {
        return if(filterPeopleList.size == 0) {
            searchPeopleList.size
        } else {
            filterPeopleList.size
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is SuggestedFriendsViewHolder) {
            holder.main(mContext, position)
        }
    }

    override fun getFilter(): Filter{
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                filterPeopleList = if(charSearch.isEmpty()) {
                    println("Its empty")
                    searchPeopleList
                } else {
                    println("Search view not empty")
                    val resultPeopleList = ArrayList<SearchPeople>()
                    for (row in searchPeopleList) {
                        if(row.firstName.toLowerCase(Locale.ROOT).contains(charSearch.toLowerCase(Locale.ROOT))) {
                            println(row.firstName)
                            resultPeopleList.add(row)
                        }
                    }
                    resultPeopleList
                }
                val filterResults = FilterResults()
                filterResults.values = filterPeopleList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filterPeopleList = results?.values as ArrayList<SearchPeople>
                notifyDataSetChanged()
            }

        }
    }

}