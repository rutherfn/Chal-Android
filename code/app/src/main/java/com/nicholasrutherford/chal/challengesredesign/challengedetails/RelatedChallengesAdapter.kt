package com.nicholasrutherford.chal.challengesredesign.challengedetails

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.databinding.ChallengesListLayoutBinding
import com.nicholasrutherford.chal.helpers.Typeface
import com.nicholasrutherford.chal.helpers.visibleOrGone

class RelatedChallengesAdapter (private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ChallengesListLayoutBinding.inflate(inflater, parent, false)
        return RelatedChallengesViewHolder(binding, context)
    }

    override fun getItemCount(): Int {
        return 1
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
            Glide.with(context).load(R.drawable.ic_health).into(binding.cvChallengeIcon)
            binding.tvChallengesCategories.text = "Related Challenges"

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