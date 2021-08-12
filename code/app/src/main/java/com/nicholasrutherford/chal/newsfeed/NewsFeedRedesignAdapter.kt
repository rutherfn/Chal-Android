package com.nicholasrutherford.chal.newsfeed

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.nicholasrutherford.chal.ChallengeCalenderDay
import com.nicholasrutherford.chal.R
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
        private val challengeCalenderDay = ChallengeCalenderDay()

        val requestOption : RequestOptions = RequestOptions()
            .placeholder(R.drawable.circle)
            .error(R.drawable.ic_error)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .priority(Priority.HIGH)
            .dontAnimate()
            .dontTransform()

        fun bind(position: Int) {

            val currentDay = newsFeedList[position].posts?.currentDay?.toInt() ?: 0
            val challengeExpired = newsFeedList[position].posts?.currentDay?.toInt()?.plus(7) ?: 0

            val currentChallengeDay = challengeCalenderDay.getRealCurrentDayOnChallenge(currentDay = currentDay, challengeExpired = challengeExpired) + 1

            binding.tvHomeUsername.text = newsFeedList[position].posts?.username
            Glide.with(context).load(newsFeedList[position].posts?.usernameUrl).apply(requestOption).into(binding.ivHomeProfilePicture)

            Glide.with(context).load(newsFeedList[position].posts?.image).apply(requestOption).into(binding.ivHomePostImage)
            binding.tvHomeChallengeTitle.text = newsFeedList[position].posts?.title
            binding.tvChallengepostDesc.text = newsFeedList[position].posts?.description
            binding.btnDayOfChallenge.text = "Day ".plus(currentChallengeDay)

            updateTypeface()
        }

        private fun updateTypeface() {
            typeface.setTypefaceForSubHeaderRegular(binding.btnDayOfChallenge, context)
            typeface.setTypefaceForSubHeaderBold(binding.tvHomeChallengeTitle, context)
            typeface.setTypefaceForBodyItalic(binding.tvChallengepostDesc, context)
        }
    }
}
