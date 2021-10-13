package com.nicholasrutherford.chal.challenge.list

import android.app.Application
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nicholasrutherford.chal.challenge.list.databinding.ChallengeListLayoutBinding
import com.nicholasrutherford.chal.ui.typefaces.Typefaces
import con.nicholasrutherford.chal.data.challenges.ActiveChallenge
import con.nicholasrutherford.chal.data.challenges.AvailableChallenges
import con.nicholasrutherford.chal.data.challenges.JoinableChallenges

class ChallengeListAdapter(
    private val viewModel: ChallengeListViewModel,
    private val application: Application,
    private val joinableChallengesList: List<JoinableChallenges>,
    private val typefaces: Typefaces
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ChallengeListLayoutBinding.inflate(inflater, parent, false)
        return ChallengeViewHolder(binding, viewModel, application, joinableChallengesList, typefaces)
    }

    override fun getItemCount(): Int {
        return joinableChallengesList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ChallengeViewHolder) {
            holder.bind(position)
        }
    }

    inner class ChallengeViewHolder(
        private val binding: ChallengeListLayoutBinding,
        private val viewModel: ChallengeListViewModel,
        private val application: Application,
        private val joinableChallengesList: List<JoinableChallenges>,
        private val typefaces: Typefaces
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            // TODO come back to this and refactored hard coded indexes

            binding.tvChallengesCategories.text = joinableChallengesList[position].category
            Glide.with(application).load(categoryChallengeIcon(position)).into(binding.cvChallengeIcon)

            Glide.with(application).load(joinableChallengesList[position].challenges[0].url).into(binding.ivChallengeOne)
            binding.tvChallengeOneName.text = joinableChallengesList[position].challenges[0].title
            binding.tvChallengeOneLength.text = joinableChallengesList[position].challenges[0].time

            Glide.with(application).load(joinableChallengesList[position].challenges[1].url).into(binding.ivChallengeTwo)
            binding.tvChallengeTwoName.text = joinableChallengesList[position].challenges[1].title
            binding.tvChallengeTwoLength.text = joinableChallengesList[position].challenges[1].time

            Glide.with(application).load(joinableChallengesList[position].challenges[2].url).into(binding.ivChallengeThree)
            binding.tvChallengeThreeName.text = joinableChallengesList[position].challenges[2].title
            binding.tvChallengeThreeLength.text = joinableChallengesList[position].challenges[2].time

            typefaces.setTextViewHeaderBoldTypeface(binding.tvChallengesCategories)

            typefaces.setTextViewSubHeaderBoldTypeface(binding.tvChallengeOneName)
            typefaces.setTextViewBodyItalicTypeface(binding.tvChallengeOneLength)

            typefaces.setTextViewSubHeaderBoldTypeface(binding.tvChallengeTwoName)
            typefaces.setTextViewBodyItalicTypeface(binding.tvChallengeTwoLength)

            typefaces.setTextViewSubHeaderBoldTypeface(binding.tvChallengeThreeName)
            typefaces.setTextViewBodyItalicTypeface(binding.tvChallengeThreeLength)

            binding.ivChallengeOne.setOnClickListener {
                viewModel.onShowChallengeDetailItemClicked(selectedChallenge(position, 0))
            }
            binding.ivChallengeTwo.setOnClickListener {
                viewModel.onShowChallengeDetailItemClicked(selectedChallenge(position, 1))
            }
            binding.ivChallengeThree.setOnClickListener {
                viewModel.onShowChallengeDetailItemClicked(selectedChallenge(position, 2))
            }
        }

        private fun categoryChallengeIcon(pos: Int): Int {
            return when (joinableChallengesList[pos].categoryNumber) {
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

        private fun selectedChallenge(position: Int, index: Int): AvailableChallenges {
            return joinableChallengesList[position].challenges[index]
        }

    }

}