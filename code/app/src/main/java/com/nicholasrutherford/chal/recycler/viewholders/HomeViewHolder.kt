package com.nicholasrutherford.chal.recycler.viewholders

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.nicholasrutherford.chal.databinding.HomeWallLayoutBinding
import com.nicholasrutherford.chal.helpers.Typeface
import com.nicholasrutherford.chal.viewmodels.HomeViewModel
import com.squareup.picasso.Picasso

class HomeViewHolder (private var binding: HomeWallLayoutBinding, private val viewModel: HomeViewModel, private val context: Context) : RecyclerView.ViewHolder(binding.root) {

    private val typeface = Typeface()

    fun bind(position: Int) {
        typeface.setTypefaceForHeaderBold(binding.tvFullName, context)
        typeface.setTypefaceForSubHeaderBold(binding.tvChallengePostTitle, context)
        typeface.setTypefaceForBodyBold(binding.tvChallengePostBody, context)
        typeface.setTypefaceForBodyRegular(binding.tvViewAllComments, context)
        Picasso.get().load("https://cdn.shopify.com/s/files/1/2516/0556/articles/Blog_Post_Photos_64_2048x.png?v=1547149531").into(binding.ivChallengePost)
    }
}