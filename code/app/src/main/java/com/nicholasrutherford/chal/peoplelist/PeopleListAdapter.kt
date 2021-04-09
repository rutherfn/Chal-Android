package com.nicholasrutherford.chal.peoplelist

import android.app.Application
import android.text.method.TextKeyListener.clear
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nicholasrutherford.chal.data.responses.ProfileListResponse
import com.nicholasrutherford.chal.databinding.PeopleListLayoutBinding
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class PeopleListAdapter @Inject constructor(private val application: Application, private val peopleListViewModel: PeopleListViewModel, private val profileListResponse: MutableList<ProfileListResponse>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {

    var profileFilterList = ArrayList<ProfileListResponse>()

    init {
        profileFilterList = profileListResponse as ArrayList<ProfileListResponse>
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PeopleListLayoutBinding.inflate(inflater, parent, false)
        return PeopleListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PeopleListViewHolder).bind(profileFilterList[position])
    }

    override fun getItemCount(): Int {
        return profileFilterList.size
    }

    inner class PeopleListViewHolder(private var binding: PeopleListLayoutBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(profileFilter: ProfileListResponse) {
            updateUI(profileFilter)
            onClick()
        }

        private fun updateUI(profileFilter: ProfileListResponse) {
            Glide.with(application.applicationContext).load(profileFilter.profileImage)
                .into(binding.cvProfilePicture)
            binding.tvUsername.text = profileFilter.username
        }

        private fun onClick() {

        }

    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()

                if (charSearch.isEmpty()) {
                    profileFilterList = profileListResponse as ArrayList<ProfileListResponse>
                } else {
                    val resultList = ArrayList<ProfileListResponse>()
                    for (row in profileListResponse) {
                        if (row.username.toLowerCase().contains(constraint.toString().toLowerCase())) {
                            resultList.add(row)
                        }
                    }
                    profileFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = profileFilterList

                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                println(results?.values)
                profileFilterList = results?.values as ArrayList<ProfileListResponse>
                notifyDataSetChanged()
            }
        }
    }
}
