package com.nicholasrutherford.chal.recycler.viewholders

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.databinding.HomeWallLayoutBinding
import com.nicholasrutherford.chal.helpers.Typeface
import com.nicholasrutherford.chal.viewmodels.HomeViewModel
import com.squareup.picasso.Picasso

class HomeViewHolder (private var binding: HomeWallLayoutBinding, private val viewModel: HomeViewModel, private val context: Context) : RecyclerView.ViewHolder(binding.root) {

    private val typeface = Typeface()

    fun bind(position: Int) {

        binding.tvChallengeTitle.text = "# 100 Day Fitness Challenge"
        binding.tvChallengeDurationLeft.text = "Challenge Duration - 2 Days Left"
        binding.tvChallengePostTitle.text = "In Class Today!"
        binding.tvChallengePostBody.text = "Spent today in class! Trying to work on my fitness one day at a time"

        typeface.setTypefaceForHeaderBold(binding.tvFullName, context)
        typeface.setTypefaceForSubHeaderRegular(binding.tvTimeUpdated, context)

        typeface.setTypefaceForBodyBold(binding.tvHashTagOne, context)
        typeface.setTypefaceForBodyBold(binding.tvHashTagTwo, context)
        typeface.setTypefaceForBodyBold(binding.tvHashTagThree, context)

        typeface.setTypefaceForSubHeaderBold(binding.tvChallengeTitle, context)
        typeface.setTypefaceForBodyRegular(binding.tvChallengeDurationLeft, context)

        typeface.setTypefaceForSubHeaderBold(binding.tvChallengePostTitle, context)
        typeface.setTypefaceForBodyRegular(binding.tvChallengePostBody, context)
        typeface.setTypefaceForBodyRegular(binding.tvCurrentLikes, context)

        typeface.setTypefaceForBodyRegular(binding.tvViewAllComments, context)
        typeface.setTypefaceForBodyRegular(binding.tvViewChallenge, context)

        Picasso.get().load(R.drawable.willplaceholder).into(binding.cvProfile)

        Picasso.get().load("https://cdn.shopify.com/s/files/1/2516/0556/articles/Blog_Post_Photos_64_2048x.png?v=1547149531").into(binding.ivChallengePost)
    }
}