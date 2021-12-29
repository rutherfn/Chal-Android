package com.nicholasrutherford.chal.profile

import android.app.Application
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nicholasrutherford.chal.helper.constants.CURRENT_CATEGORY_LIST
import com.nicholasrutherford.chal.profile.databinding.ProfileChallengesListBinding
import com.nicholasrutherford.chal.ui.typefaces.Typefaces
import com.squareup.picasso.Picasso
import con.nicholasrutherford.chal.data.challenges.ActiveChallengesListResponse

class ProfileListAdapter(
    private val application: Application,
    private val typefaces: Typefaces,
    private val activeChallengesListResponse: List<ActiveChallengesListResponse>,
    private val viewModel: ProfileViewModel
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ProfileChallengesListBinding.inflate(inflater, parent, false)
        return ProfileListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return activeChallengesListResponse.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ProfileListViewHolder) {
            holder.bind(position)
        }
    }

    inner class ProfileListViewHolder(private val binding: ProfileChallengesListBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            val categoryName = activeChallengesListResponse[position].activeChallenges?.categoryName
            val daysOfChallenge = activeChallengesListResponse[position].activeChallenges?.numberOfDaysOfChallenge
            val challengeCurrentDay = activeChallengesListResponse[position].activeChallenges?.currentDay

            typefaces.setTextViewSubHeaderBoldTypeface(binding.tvChallengeTitle)
            typefaces.setTextViewBodyItalicTypeface(binding.tvChallengeCategory)
            typefaces.setTextViewBodyItalicTypeface(binding.tvChallengeCurrentDay)

            binding.tvChallengeTitle.text = activeChallengesListResponse[position].activeChallenges?.name
                ?: application.getString(R.string.empty_string)

            binding.tvChallengeCategory.text = application.getString(R.string.category_x, categoryName)
            binding.tvChallengeCurrentDay.text =
                application.getString(R.string.days_completed, challengeCurrentDay!!.toInt(), daysOfChallenge!!.toInt())

            val options = RequestOptions()
                .placeholder(R.drawable.circle)
                .error(R.drawable.circle)

            Glide.with(application).load(challengeTypeImage(position)).apply(options)
                .into(binding.cvChallengeCategory)

            binding.clProfileChallengesList.setOnClickListener {
                viewModel.onItemClicked(index = position)
            }
        }

        private fun challengeTypeImage(position: Int): Int {
            return when (activeChallengesListResponse[position].activeChallenges?.categoryName) {
                CURRENT_CATEGORY_LIST[0] -> {
                    R.drawable.ic_health_wellness
                }
                CURRENT_CATEGORY_LIST[1] -> {
                    R.drawable.ic_intellectual
                }
                else -> {
                    R.drawable.ic_lifestyle
                }
            }
        }
    }
}