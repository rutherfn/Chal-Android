package com.nicholasrutherford.chal.main.news.feed

import android.app.Application
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.nicholasrutherford.chal.data.post.PostListResponse
import com.nicholasrutherford.chal.main.news.feed.databinding.NewsFeedListLayoutBinding
import com.nicholasrutherford.chal.ui.typefaces.Typefaces

class NewsFeedListAdapter(
    private val application: Application,
    private val newsFeedList: List<PostListResponse>,
    private val typefaces: Typefaces,
    private val newsFeedViewModel: NewsFeedViewModel
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = NewsFeedListLayoutBinding.inflate(inflater, parent, false)
        return NewsFeedListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return newsFeedList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is NewsFeedListViewHolder) {
            holder.bind(position)
        }
    }

    inner class NewsFeedListViewHolder(private val binding: NewsFeedListLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        val requestOption : RequestOptions = RequestOptions()
            .placeholder(R.drawable.circle)
            .error(R.drawable.ic_error)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .priority(Priority.HIGH)
            .dontAnimate()
            .dontTransform()

        fun bind(position: Int) {
            val currentDay = newsFeedList[position].posts?.currentDay?.toInt() ?: 0
            val challengeExpired = newsFeedList[position].posts?.dateChallengeExpired?.toInt() ?: 0

            binding.tvHomeUsername.text = newsFeedList[position].posts?.username
            Glide.with(application).load(newsFeedList[position].posts?.usernameUrl).apply(requestOption).into(binding.ivHomeProfilePicture)

            Glide.with(application).load(newsFeedList[position].posts?.image).apply(requestOption).into(binding.ivHomePostImage)
            binding.tvHomeChallengeTitle.text = newsFeedList[position].posts?.title
            binding.tvChallengepostDesc.text = newsFeedList[position].posts?.description

            binding.btnDayOfChallenge.text = "Day ".plus(currentDay) // TODO update this

            updateTypeface()
        }

        private fun updateTypeface() {
            typefaces.setTextViewHeaderRegularTypeface(binding.btnDayOfChallenge)
            typefaces.setTextViewSubHeaderBoldTypeface(binding.tvHomeChallengeTitle)
            typefaces.setTextViewBodyItalicTypeface(binding.tvChallengepostDesc)
        }
    }
}