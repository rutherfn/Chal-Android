package com.nicholasrutherford.chal.newsfeed

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.data.responses.CurrentActiveChallengesResponse
import com.nicholasrutherford.chal.databinding.MyChallengesHeaderLayoutBinding
import com.nicholasrutherford.chal.helpers.Helper
import com.nicholasrutherford.chal.helpers.Typeface
import com.squareup.picasso.Picasso

const val placeHolderImage = "https://www.topsecrets.com/wp-content/uploads/2020/03/goal-leap-4052923_1280-1024x512.jpg"

class ChallengesHeaderAdapter(
    private val context: Context,
    private val viewModel: NewsFeedViewModel,
    private val listOfActiveChallenges: List<CurrentActiveChallengesResponse>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MyChallengesHeaderLayoutBinding.inflate(inflater, parent, false)
        return MyChallengesViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return if (viewModel.viewState.myChallengesVisible) {
            listOfActiveChallenges.size
        } else {
            0
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MyChallengesViewHolder) {
            holder.bind(position)
        }
    }

    inner class MyChallengesViewHolder(private var binding: MyChallengesHeaderLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        private val typeface = Typeface()
        private val helper = Helper()

        fun bind(position: Int) {
            updateTypefaces()

            val currentChallengeDay = listOfActiveChallenges[position].dayOnChallenge
            val challengeExpireDay = listOfActiveChallenges[position].numberOfDaysOfChallenge

            binding.tvChallengeHeaderTitle.text = listOfActiveChallenges[position].name
            binding.tvChallengeHeaderDesc.text = context.getString(R.string.days_completed, currentChallengeDay, challengeExpireDay)
            Picasso.get().load(placeHolderImage).into(binding.ivChallengeHeader)

            val options = RequestOptions()
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
            Glide.with(context).load(challengeTypeImage(position)).apply(options)
                .into(binding.ivChallengeType)
        }

        private fun updateTypefaces() {
            typeface.setTypefaceForSubHeaderBold(binding.tvChallengeHeaderTitle, context)
            typeface.setTypefaceForBodyItalic(binding.tvChallengeHeaderDesc, context)
            typeface.setTypefaceForBodyRegular(binding.btnUpdateProgress, context)
        }

        private fun challengeTypeImage(position: Int): Int {
            return when (listOfActiveChallenges[position].categoryName) {
                helper.categoryList[0] -> {
                    R.drawable.ic_health_wellness_white
                }
                helper.categoryList[1] -> {
                    R.drawable.ic_intellectual_white
                }
                else -> {
                    R.drawable.ic_lifestyle_white
                }
            }
        }

    }
}
