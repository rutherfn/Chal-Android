package com.nicholasrutherford.chal.main.news.feed

import android.app.Application
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nicholasrutherford.chal.helper.constants.CURRENT_CATEGORY_LIST
import com.nicholasrutherford.chal.helper.constants.PLACEHOLDER_CHALLENGE_HEADER_IMAGE
import com.nicholasrutherford.chal.main.news.feed.databinding.NewsFeedChallengeHeaderLayoutBinding
import com.nicholasrutherford.chal.ui.typefaces.Typefaces
import com.squareup.picasso.Picasso
import con.nicholasrutherford.chal.data.challenges.ActiveChallengesListResponse

class NewsFeedChallengeHeaderAdapter(
    private val application: Application,
    private val newsFeedViewModel: NewsFeedViewModel,
    private val typefaces: Typefaces,
    private val listOfActiveUserChallenges: List<ActiveChallengesListResponse>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = NewsFeedChallengeHeaderLayoutBinding.inflate(inflater, parent, false)
        return NewsFeedChallengeViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return if (newsFeedViewModel.viewState.myChallengesVisible) {
            listOfActiveUserChallenges.size
        } else {
            return 0
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is NewsFeedChallengeViewHolder) {
            holder.bind(position)
        }
    }

    inner class NewsFeedChallengeViewHolder(private var binding: NewsFeedChallengeHeaderLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            updateTypefaces()

            val currentDay = listOfActiveUserChallenges[position].activeChallenges?.currentDay ?: 0
            val challengeExpired = listOfActiveUserChallenges[position].activeChallenges?.dateChallengeExpired?.toInt() ?: 0

            val currentChallengeDay = newsFeedViewModel.getRealCurrentDayOnChallenge(currentDay = currentDay, challengeExpired = challengeExpired)
            val challengeExpireDay = 7

            binding.tvChallengeHeaderTitle.text = listOfActiveUserChallenges[position].activeChallenges?.name
            binding.tvChallengeHeaderDesc.text = application.getString(R.string.days_completed, currentChallengeDay, challengeExpireDay)
            Picasso.get().load(PLACEHOLDER_CHALLENGE_HEADER_IMAGE).into(binding.ivChallengeHeader)

            val options = RequestOptions()
                .placeholder(R.drawable.circle)
                .error(R.drawable.circle)

            Glide.with(application).load(challengeTypeImage(position)).apply(options)
                .into(binding.ivChallengeType)

            clickListeners()
        }

        fun clickListeners() {
            binding.btnUpdateProgress.setOnClickListener {
                newsFeedViewModel.onAddProgressTabClicked()
            }
        }

        private fun updateTypefaces() {
            typefaces.setTextViewSubHeaderBoldTypeface(binding.tvChallengeHeaderTitle)
            typefaces.setTextViewBodyItalicTypeface(binding.tvChallengeHeaderDesc)
            typefaces.setTextViewBodyRegularTypeface(binding.btnUpdateProgress)
        }

        private fun challengeTypeImage(position: Int): Int {
            return when (listOfActiveUserChallenges[position].activeChallenges?.categoryName) {
                CURRENT_CATEGORY_LIST[0] -> {
                    R.drawable.ic_health_wellness_white
                }
                CURRENT_CATEGORY_LIST[1] -> {
                    R.drawable.ic_intellectual_white
                }
                else -> {
                    R.drawable.ic_lifestyle_white
                }
            }
        }
    }
}