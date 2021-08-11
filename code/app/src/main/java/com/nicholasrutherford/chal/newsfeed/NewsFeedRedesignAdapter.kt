package com.nicholasrutherford.chal.newsfeed

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nicholasrutherford.chal.data.responses.NewsFeedResponse
import com.nicholasrutherford.chal.data.responses.post.PostListResponse
import com.nicholasrutherford.chal.databinding.RedesignMyFeedLayoutBinding
import com.nicholasrutherford.chal.helpers.Typeface

class NewsFeedRedesignAdapter(private val context: Context, private val newsFeedList: List<PostListResponse>, private val viewModel: NewsFeedViewModel) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RedesignMyFeedLayoutBinding.inflate(inflater, parent, false)
        return NewsFeedRedsignViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return newsFeedList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is NewsFeedRedsignViewHolder) {
            holder.bind(position)
        }
    }

    inner class NewsFeedRedsignViewHolder(private var binding: RedesignMyFeedLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        private val typeface = Typeface()

        fun bind(position: Int) {
            binding.tvHomeUsername.text = newsFeedList[position].posts?.username
            Glide.with(context).load(newsFeedList[position].posts?.usernameUrl).into(binding.ivHomeProfilePicture)

            Glide.with(context).load(newsFeedList[position].posts?.image).into(binding.ivHomePostImage)
            binding.tvHomeChallengeTitle.text = newsFeedList[position].posts?.title
            binding.tvChallengepostDesc.text = newsFeedList[position].posts?.description
            binding.btnDayOfChallenge.text = "Day ".plus( newsFeedList[position].posts?.currentDay)

            updateTypeface()
        }

        private fun updateTypeface() {
            typeface.setTypefaceForSubHeaderRegular(binding.btnDayOfChallenge, context)
            typeface.setTypefaceForSubHeaderBold(binding.tvHomeChallengeTitle, context)
            typeface.setTypefaceForBodyItalic(binding.tvChallengepostDesc, context)
        }
    }
}
