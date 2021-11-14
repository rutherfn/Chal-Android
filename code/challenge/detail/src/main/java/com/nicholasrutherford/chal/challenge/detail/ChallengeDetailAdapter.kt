package com.nicholasrutherford.chal.challenge.detail

import android.app.Application
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nicholasrutherford.chal.challenge.detail.databinding.ChallengeDetailListBinding
import com.nicholasrutherford.chal.ui.typefaces.Typefaces
import con.nicholasrutherford.chal.data.challenges.AvailableChallenges
import con.nicholasrutherford.chal.data.challenges.JoinableChallenges

class ChallengeDetailAdapter(
    private val viewModel: ChallengeDetailViewModel,
    private val application: Application,
    private val joinableChallenges: List<JoinableChallenges>,
    private val typefaces: Typefaces
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ChallengeDetailListBinding.inflate(inflater, parent, false)
        return ChallengeDetailsViewHolder(binding, viewModel, application, joinableChallenges, typefaces)
    }

    override fun getItemCount(): Int {
        return joinableChallenges.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ChallengeDetailsViewHolder) {
            holder.bind(position)
        }
    }

    inner class ChallengeDetailsViewHolder(
        private val binding: ChallengeDetailListBinding,
        private val viewModel: ChallengeDetailViewModel,
        private val application: Application,
        private val joinableChallenges: List<JoinableChallenges>,
        private val typefaces: Typefaces
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            typefaces.setTextViewHeaderBoldTypeface(binding.tvChallengesCategories)

            typefaces.setTextViewSubHeaderRegularTypeface(binding.tvChallengeOneName)
            typefaces.setTextViewBodyItalicTypeface(binding.tvChallengeOneLength)

            typefaces.setTextViewSubHeaderRegularTypeface(binding.tvChallengeTwoName)
            typefaces.setTextViewBodyItalicTypeface(binding.tvChallengeTwoLength)

            typefaces.setTextViewSubHeaderRegularTypeface(binding.tvChallengeThreeName)
            typefaces.setTextViewBodyItalicTypeface(binding.tvChallengeThreeLength)

            Glide.with(application).load(categoryChallengeIcon(position)).into(binding.cvChallengeIcon)
            binding.tvChallengesCategories.text = application.getString(R.string.related_challenges)

            Glide.with(application).load(joinableChallenges[position].challenges[0].url).into(binding.ivChallengeOne)
            binding.tvChallengeOneName.text = joinableChallenges[position].challenges[0].title
            binding.tvChallengeOneLength.text = joinableChallenges[position].challenges[0].time

            Glide.with(application).load(joinableChallenges[position].challenges[1].url).into(binding.ivChallengeTwo)
            binding.tvChallengeTwoName.text = joinableChallenges[position].challenges[1].title
            binding.tvChallengeTwoLength.text = joinableChallenges[position].challenges[1].time

            Glide.with(application).load(joinableChallenges[position].challenges[2].url).into(binding.ivChallengeThree)
            binding.tvChallengeThreeName.text = joinableChallenges[position].challenges[2].title
            binding.tvChallengeThreeLength.text = joinableChallenges[position].challenges[2].time

            onClickListeners(position)
        }

        private fun onClickListeners(position: Int) {
            binding.ivChallengeOne.setOnClickListener {
                viewModel.onRelatedChallengeClicked(
                    selectedAvailableChallenge = joinableChallenges[position].challenges[0]
                )
            }
            binding.ivChallengeTwo.setOnClickListener {
                viewModel.onRelatedChallengeClicked(
                    selectedAvailableChallenge = joinableChallenges[position].challenges[1]
                )
            }
            binding.ivChallengeThree.setOnClickListener {
                viewModel.onRelatedChallengeClicked(
                    selectedAvailableChallenge = joinableChallenges[position].challenges[2]
                )
            }
        }

        private fun categoryChallengeIcon(pos: Int): Int {
            return when (joinableChallenges[pos].categoryNumber) {
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
            return joinableChallenges[position].challenges[index]
        }
    }
}