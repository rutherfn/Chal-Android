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

        typeface.setHeaderTypefaceBold(binding.tvFullName, context, viewModel.viewState.configurationEntity.primaryHeaderTypefaceBold)

        typeface.setTypefaceForRegularSubHeader(binding.tvTimeUpdated, context, viewModel.viewState.configurationEntity.subHeaderTypeface)

        typeface.setTypefaceForBoldBody(binding.tvHashTagOne, context, viewModel.viewState.configurationEntity.bodyTypefaceBold)
        typeface.setTypefaceForBoldBody(binding.tvHashTagTwo, context, viewModel.viewState.configurationEntity.bodyTypefaceBold)
        typeface.setTypefaceForBoldBody(binding.tvHashTagThree, context, viewModel.viewState.configurationEntity.bodyTypefaceBold)

        typeface.setTypefaceForBoldSubHeader(binding.tvChallengeTitle, context, viewModel.viewState.configurationEntity.subHeaderTypefaceBold)
        typeface.setTypefaceForRegularBody(binding.tvChallengeDurationLeft, context, viewModel.viewState.configurationEntity.bodyTypeface)

        typeface.setTypefaceForBoldSubHeader(binding.tvChallengePostTitle, context, viewModel.viewState.configurationEntity.subHeaderTypefaceBold)
        typeface.setTypefaceForRegularBody(binding.tvChallengePostBody, context, viewModel.viewState.configurationEntity.bodyTypeface)
        typeface.setTypefaceForRegularBody(binding.tvCurrentLikes, context, viewModel.viewState.configurationEntity.bodyTypeface)

        typeface.setTypefaceForRegularBody(binding.tvViewAllComments, context, viewModel.viewState.configurationEntity.bodyTypeface)
        typeface.setTypefaceForRegularBody(binding.tvViewChallenge, context, viewModel.viewState.configurationEntity.bodyTypeface)

        Picasso.get().load(R.drawable.willplaceholder).into(binding.cvProfile)

        Picasso.get().load("https://cdn.shopify.com/s/files/1/2516/0556/articles/Blog_Post_Photos_64_2048x.png?v=1547149531").into(binding.ivChallengePost)
    }
}