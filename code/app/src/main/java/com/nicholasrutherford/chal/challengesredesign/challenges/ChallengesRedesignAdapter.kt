package com.nicholasrutherford.chal.challengesredesign.challenges

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nicholasrutherford.chal.MainActivity
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.data.realdata.LiveChallenges
import com.nicholasrutherford.chal.databinding.ChallengesListLayoutBinding
import com.nicholasrutherford.chal.helpers.Typeface

const val FIRST_POSITION = 0
const val SECOND_POSITION = 1
const val THIRD_POSITION = 2

class ChallengesRedesignAdapter(
    private val mainActivity: MainActivity,
    private val viewModel: ChallengesRedesignViewModel,
    private val context: Context,
    private val fragmentManager: FragmentManager,
    private val container: Int,
    private val bottomNavigationView: BottomNavigationView,
    private val liveChallengesList: List<LiveChallenges>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ChallengesListLayoutBinding.inflate(inflater, parent, false)
        return ChallengesRedesignViewHolder(binding, viewModel, context, liveChallengesList)
    }

    override fun getItemCount(): Int {
        return liveChallengesList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ChallengesRedesignViewHolder) {
            holder.bind(position)
        }
    }

    inner class ChallengesRedesignViewHolder(
        private var binding: ChallengesListLayoutBinding,
        private val viewModel: ChallengesRedesignViewModel,
        private val context: Context,
        private val liveChallengesList: List<LiveChallenges>
    ) : RecyclerView.ViewHolder(binding.root) {

        val typeface = Typeface()

        fun categoryChallengeIcon(pos: Int): Int {
            return when (liveChallengesList[pos].categoryNumber) {
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

        fun bind(position: Int) {
            binding.tvChallengesCategories.text = liveChallengesList[position].category
            Glide.with(context).load(categoryChallengeIcon(position)).into(binding.cvChallengeIcon)

            Glide.with(context).load(liveChallengesList[position].challenges[FIRST_POSITION].url).into(binding.ivChallengeOne)
            binding.tvChallengeOneName.text = liveChallengesList[position].challenges[FIRST_POSITION].title
            binding.tvChallengeOneLength.text = liveChallengesList[position].challenges[FIRST_POSITION].time

            Glide.with(context).load(liveChallengesList[position].challenges[SECOND_POSITION].url).into(binding.ivChallengeTwo)
            binding.tvChallengeTwoName.text = liveChallengesList[position].challenges[SECOND_POSITION].title
            binding.tvChallengeTwoLength.text = liveChallengesList[position].challenges[SECOND_POSITION].time

            Glide.with(context).load(liveChallengesList[position].challenges[THIRD_POSITION].url).into(binding.ivChallengeThree)
            binding.tvChallengeThreeName.text = liveChallengesList[position].challenges[THIRD_POSITION].title
            binding.tvChallengeThreeLength.text = liveChallengesList[position].challenges[THIRD_POSITION].time

            typeface.setTypefaceForHeaderBold(binding.tvChallengesCategories, context)
            typeface.setTypefaceForSubHeaderBold(binding.tvChallengeOneName, context)
            typeface.setTypefaceForBodyItalic(binding.tvChallengeOneLength, context)
            typeface.setTypefaceForSubHeaderBold(binding.tvChallengeTwoName, context)
            typeface.setTypefaceForBodyItalic(binding.tvChallengeTwoLength, context)
            typeface.setTypefaceForSubHeaderBold(binding.tvChallengeThreeName, context)
            typeface.setTypefaceForBodyItalic(binding.tvChallengeThreeLength, context)

            binding.ivChallengeOne.setOnClickListener {
                viewModel.challengesRedesignNavigationImpl.showChallengeDetails(mainActivity, context, true, fragmentManager, container, bottomNavigationView)
            }
            binding.ivChallengeTwo.setOnClickListener {
                viewModel.challengesRedesignNavigationImpl.showChallengeDetails(mainActivity, context, true, fragmentManager, container, bottomNavigationView)
            }
            binding.ivChallengeThree.setOnClickListener {
                viewModel.challengesRedesignNavigationImpl.showChallengeDetails(mainActivity, context, true, fragmentManager, container, bottomNavigationView)
            }
        }
    }
}
