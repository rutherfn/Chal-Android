package com.nicholasrutherford.chal.screens.home

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import com.nicholasrutherford.chal.databinding.HomeWallLayoutBinding
import com.nicholasrutherford.chal.helpers.Typeface
import com.nicholasrutherford.chal.helpers.visibleOrGone
import com.nicholasrutherford.chal.screens.home.HomeViewModel
import com.squareup.picasso.Picasso

class HomeViewHolder (private var binding: HomeWallLayoutBinding, private val viewModel: HomeViewModel, private val context: Context) : RecyclerView.ViewHolder(binding.root) {

    private val typeface = Typeface()
    private val listOfNames: ArrayList<String> = ArrayList()
    private val listOfLastUpdatedTimes : ArrayList<String> = ArrayList()
    private val listOfTitles : ArrayList<String> = ArrayList()
    private val listOfDesc: ArrayList<String> = ArrayList()
    private val listOfImages: ArrayList<String> = ArrayList()
    private val listOfUsers: ArrayList<String> = ArrayList()
    private val listOfHashTags: ArrayList<String> = ArrayList()

    fun setupData() {
        listOfNames.add("Nick Rutherford")
        listOfNames.add("Marina Thoj")
        listOfNames.add("Patrick McGinn")
        // possible will shelton

        listOfLastUpdatedTimes.add("Last Updated 1 Min Ago")
        listOfLastUpdatedTimes.add("Last Updated 7 Mins Ago")
        listOfLastUpdatedTimes.add("Last Updated 9 Mins Ago")

        listOfTitles.add("Day 5: In Class Today")
        listOfTitles.add("Day 2: Drinking Water Everyday")
        listOfTitles.add("Day 3: This wont be easy")

        listOfDesc.add("Spent today in cycling class! Trying to work on my cardio one step at a time.")
        listOfDesc.add("Drinking water to improve my health! Water opens you up to a clearer frame of mind!")
        listOfDesc.add("Today on the biking challenge I biked for 2 miles. Tomorrow for 2.5 miles wont be easy but I am ready for the challenge!")

        listOfImages.add("https://cdn.shopify.com/s/files/1/2516/0556/articles/Blog_Post_Photos_64_2048x.png?v=1547149531")
        listOfImages.add("https://cdn-prod.medicalnewstoday.com/content/images/articles/317/317698/drinking-water-after-a-workout-in-the-sun.jpg")
        listOfImages.add("https://momentummag.com/wp-content/uploads/2016/04/asdsadg.jpg")

        listOfHashTags.add("#7DayCardioChallenge")
        listOfHashTags.add("#7DayHydrationChallenge")
        listOfHashTags.add("#7DayBikingChallenge")

        listOfUsers.add("https://pbs.twimg.com/profile_images/971963353106010113/HSOt7Yvd_400x400.jpg")
        listOfUsers.add("https://upload.wikimedia.org/wikipedia/en/4/4e/Mount_Mary_University_logo.png")
        listOfUsers.add("https://pbs.twimg.com/profile_images/1230253776793149441/OqV-SeCv.jpg")
    }

    fun bind(position: Int) {
        setupData()
        binding.tvFullName.text = listOfNames[position]
        binding.tvTimeUpdated.text = listOfLastUpdatedTimes[position]
        binding.tvChallengePostTitle.text = listOfTitles[position]
        binding.tvChallengePostBody.text = listOfDesc[position]
        Picasso.get().load(listOfUsers[position]).into(binding.cvProfile)
        Picasso.get().load(listOfImages[position]).into(binding.ivChallengePost)
        binding.tvViewChallenge.text = listOfHashTags[position]
        binding.ivLikes.visibleOrGone = false
        binding.tvCurrentLikes.visibleOrGone = false
        binding.tvViewAllComments.visibleOrGone = false

        typeface.setHeaderTypefaceBold(binding.tvFullName, context, viewModel.viewState.configurationEntity.primaryHeaderTypefaceBold)

        typeface.setTypefaceForRegularSubHeader(binding.tvTimeUpdated, context, viewModel.viewState.configurationEntity.subHeaderTypeface)

        typeface.setTypefaceForBoldSubHeader(binding.tvChallengePostTitle, context, viewModel.viewState.configurationEntity.subHeaderTypefaceBold)
        typeface.setTypefaceForRegularBody(binding.tvChallengePostBody, context, viewModel.viewState.configurationEntity.bodyTypeface)
        typeface.setTypefaceForRegularBody(binding.tvCurrentLikes, context, viewModel.viewState.configurationEntity.bodyTypeface)

        typeface.setTypefaceForRegularBody(binding.tvViewAllComments, context, viewModel.viewState.configurationEntity.bodyTypeface)
        typeface.setTypefaceForRegularBody(binding.tvViewChallenge, context, viewModel.viewState.configurationEntity.bodyTypeface)
        clickListeners()
    }

    fun clickListeners() {
        binding.ivShare.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT, "Share a post here")
            intent.type = "text/plain"
            context.startActivity(intent)
        }
    }
}