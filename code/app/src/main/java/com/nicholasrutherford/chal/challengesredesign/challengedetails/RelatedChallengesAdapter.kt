package com.nicholasrutherford.chal.challengesredesign.challengedetails

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.data.realdata.Challenges
import com.nicholasrutherford.chal.data.realdata.LiveChallenges
import com.nicholasrutherford.chal.databinding.ChallengesListLayoutBinding
import com.nicholasrutherford.chal.helpers.Typeface

const val FIRST_POSITION = 0
const val SECOND_POSITION = 1
const val THIRD_POSITION = 2

class RelatedChallengesAdapter(private val context: Context, private val listFeatureChallenges: List<LiveChallenges>, private val viewModel: ChallengeDetailsViewModel) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ChallengesListLayoutBinding.inflate(inflater, parent, false)
        return RelatedChallengesViewHolder(binding, context)
    }

    override fun getItemCount(): Int {
        return listFeatureChallenges.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is RelatedChallengesViewHolder) {
            holder.bind(position)
        }
    }

    inner class RelatedChallengesViewHolder(private var binding: ChallengesListLayoutBinding, private var context: Context) :
        RecyclerView.ViewHolder(binding.root) {

        val typeface = Typeface()

        fun bind(position: Int) {
            Glide.with(context).load(categoryChallengeIcon(position)).into(binding.cvChallengeIcon)
            binding.tvChallengesCategories.text = context.getString(R.string.related_challenges)

            Glide.with(context).load(listFeatureChallenges[position].challenges[FIRST_POSITION].url).into(binding.ivChallengeOne)
            binding.tvChallengeOneName.text = listFeatureChallenges[position].challenges[FIRST_POSITION].title
            binding.tvChallengeOneLength.text = listFeatureChallenges[position].challenges[FIRST_POSITION].time

            Glide.with(context).load(listFeatureChallenges[position].challenges[SECOND_POSITION].url).into(binding.ivChallengeTwo)
            binding.tvChallengeTwoName.text = listFeatureChallenges[position].challenges[SECOND_POSITION].title
            binding.tvChallengeTwoLength.text = listFeatureChallenges[position].challenges[SECOND_POSITION].time

            Glide.with(context).load(listFeatureChallenges[position].challenges[THIRD_POSITION].url).into(binding.ivChallengeThree)
            binding.tvChallengeThreeName.text = listFeatureChallenges[position].challenges[THIRD_POSITION].title
            binding.tvChallengeThreeLength.text = listFeatureChallenges[position].challenges[THIRD_POSITION].time

            setupTypeface()

            binding.ivChallengeOne.setOnClickListener {
                viewModel.showChallengeDetails(
                    selectedFeaturedChallenge(
                        position,
                        FIRST_POSITION
                    )
                )
            }
            binding.ivChallengeTwo.setOnClickListener {
                viewModel.showChallengeDetails(
                    selectedFeaturedChallenge(
                        position,
                        SECOND_POSITION
                    )
                )
            }
            binding.ivChallengeThree.setOnClickListener {
                viewModel.showChallengeDetails(
                    selectedFeaturedChallenge(
                        position,
                        THIRD_POSITION
                    )
                )
            }
        }

        private fun selectedFeaturedChallenge(position: Int, index: Int): Challenges {
            return listFeatureChallenges[position].challenges[index]
        }

        private fun categoryChallengeIcon(pos: Int): Int {
            return when (listFeatureChallenges[pos].categoryNumber) {
                0 -> {
                    R.drawable.ic_health_wellness
                }
                1 -> {
                    R.drawable.ic_intellectual
                }
                else -> {
                    R.drawable.ic_lifestyle
                }
            }
        }

        private fun setupTypeface() {
            typeface.setTypefaceForHeaderBold(binding.tvChallengesCategories, context)
            typeface.setTypefaceForSubHeaderRegular(binding.tvChallengeOneName, context)
            typeface.setTypefaceForBodyItalic(binding.tvChallengeOneLength, context)
            typeface.setTypefaceForSubHeaderRegular(binding.tvChallengeTwoName, context)
            typeface.setTypefaceForBodyItalic(binding.tvChallengeTwoLength, context)
            typeface.setTypefaceForSubHeaderRegular(binding.tvChallengeThreeName, context)
            typeface.setTypefaceForBodyItalic(binding.tvChallengeThreeLength, context)
        }
    }
}
