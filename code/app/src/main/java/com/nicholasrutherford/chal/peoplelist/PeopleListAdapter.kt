package com.nicholasrutherford.chal.peoplelist

import android.app.Application
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nicholasrutherford.chal.data.responses.PeopleListResponse
import com.nicholasrutherford.chal.databinding.PeopleListLayoutBinding
import javax.inject.Inject
import kotlin.collections.ArrayList

class PeopleListAdapter @Inject constructor(private val application: Application, private val peopleListViewModel: PeopleListViewModel, private val peopleListResponse: MutableList<PeopleListResponse>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {

    var peopleFilterList = ArrayList<PeopleListResponse>()

    init {
        peopleFilterList = peopleListResponse as ArrayList<PeopleListResponse>
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PeopleListLayoutBinding.inflate(inflater, parent, false)
        return PeopleListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PeopleListViewHolder).bind(peopleFilterList[position])
    }

    override fun getItemCount(): Int {
        return peopleFilterList.size
    }

    inner class PeopleListViewHolder(private var binding: PeopleListLayoutBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(peopleFilter: PeopleListResponse) {
            updateUI(peopleFilter)
            onClick()
        }

        private fun updateUI(peopleFilter: PeopleListResponse) {
            Glide.with(application.applicationContext).load(peopleFilter.profileImage)
                .into(binding.cvProfilePicture)
            binding.tvUsername.text = peopleFilter.username
        }

        private fun onClick() {

        }

    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()

                if (charSearch.isEmpty()) {
                    peopleFilterList = peopleListResponse as ArrayList<PeopleListResponse>
                } else {
                    val resultList = ArrayList<PeopleListResponse>()
                    for (row in peopleListResponse) {
                        if (row.username.toLowerCase().contains(constraint.toString().toLowerCase())) {
                            resultList.add(row)
                        }
                    }
                    peopleFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = peopleFilterList

                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                peopleFilterList = results?.values as ArrayList<PeopleListResponse>
                notifyDataSetChanged()
            }
        }
    }
}
