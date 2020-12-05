package com.nicholasrutherford.chal.challengesredesign.challenges

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.activitys.MainActivity
import com.nicholasrutherford.chal.databinding.ChallengesListLayoutBinding
import com.nicholasrutherford.chal.helpers.Typeface

// whole logic for this class isn't set just place holder stuff until i get local challenges saved in.

class ChallengesRedesignAdapter (private val mainActivity: MainActivity, private val viewModel: ChallengesRedesignViewModel, private val context: Context,
                                 private val fragmentManager: FragmentManager, private val container: Int,
                                 private val bottomNavigationView: BottomNavigationView
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ChallengesListLayoutBinding.inflate(inflater, parent, false)
        return ChallengesRedesignViewHolder(binding, viewModel, context)
    }

    override fun getItemCount(): Int {
        return 2
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ChallengesRedesignViewHolder) {
            holder.bind(position)
        }
    }

    inner class ChallengesRedesignViewHolder(private var binding: ChallengesListLayoutBinding,
                                             private val viewModel: ChallengesRedesignViewModel,
                                             private val context: Context) : RecyclerView.ViewHolder(binding.root) {

        val typeface = Typeface()

        fun loadInDummyData(position: Int) {
            if (position == 0) {
                binding.tvChallengesCategories.text = "Health & Wellness"
                Glide.with(context).load(R.drawable.ic_health).into(binding.cvChallengeIcon)
            } else {
                binding.tvChallengesCategories.text = "Social"
                Glide.with(context).load(R.drawable.ic_people).into(binding.cvChallengeIcon)
            }
        }

        fun bind(position: Int) {
            loadInDummyData(position)

            typeface.setTypefaceForHeaderBold(binding.tvChallengesCategories, context)

            typeface.setTypefaceForSubHeaderRegular(binding.tvChallengeOneName, context)
            typeface.setTypefaceForBodyItalic(binding.tvChallengeOneLength, context)

            typeface.setTypefaceForSubHeaderRegular(binding.tvChallengeTwoName, context)
            typeface.setTypefaceForBodyItalic(binding.tvChallengeTwoLength, context)

            typeface.setTypefaceForSubHeaderRegular(binding.tvChallengeThreeName, context)
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