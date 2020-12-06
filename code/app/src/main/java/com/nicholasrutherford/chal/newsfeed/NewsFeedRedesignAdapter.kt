package com.nicholasrutherford.chal.newsfeed

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nicholasrutherford.chal.databinding.RedesignMyFeedLayoutBinding
import com.nicholasrutherford.chal.helpers.Typeface
import com.nicholasrutherford.chal.helpers.visibleOrGone
import com.squareup.picasso.Picasso

class NewsFeedRedesignAdapter (private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
       val inflater = LayoutInflater.from(parent.context)
       val binding = RedesignMyFeedLayoutBinding.inflate(inflater, parent, false)
       return NewsFeedRedsignViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return 3
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is NewsFeedRedsignViewHolder) {
            holder.bind(position)
        }
    }

    inner class NewsFeedRedsignViewHolder(private var binding: RedesignMyFeedLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
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

            listOfTitles.add("7 Days Of Meditation - Health And Wellness")
            listOfTitles.add("7 Days Of Meditation - Health And Wellness")
            listOfTitles.add("7 Days Of Meditation - Health And Wellness")

            listOfDesc.add("Lorem ipsum dolor sir amet, consetutr sadiscing eltir, sed diam nomuntry")
            listOfDesc.add("Lorem ipsum dolor sir amet, consetutr sadiscing eltir, sed diam nomuntry")
            listOfDesc.add("Lorem ipsum dolor sir amet, consetutr sadiscing eltir, sed diam nomuntry")

            listOfImages.add("https://daveasprey.com/wp-content/uploads/2015/03/meditation_sun.jpg")
            listOfImages.add("https://daveasprey.com/wp-content/uploads/2015/03/meditation_sun.jpg")
            listOfImages.add("https://daveasprey.com/wp-content/uploads/2015/03/meditation_sun.jpg")

            listOfUsers.add("https://pbs.twimg.com/profile_images/971963353106010113/HSOt7Yvd_400x400.jpg")
            listOfUsers.add("https://upload.wikimedia.org/wikipedia/en/4/4e/Mount_Mary_University_logo.png")
            listOfUsers.add("https://pbs.twimg.com/profile_images/1230253776793149441/OqV-SeCv.jpg")
        }

        fun bind(position: Int) {
            setupData()
            binding.tvHomeUsername.text = listOfNames[position]
            binding.tvHomeChallengeTitle.text = listOfTitles[position]
            binding.tvChallengepostDesc.text = listOfDesc[position]
            Picasso.get().load(listOfUsers[position]).into(binding.ivHomeProfilePicture)
            Picasso.get().load(listOfImages[position]).into(binding.ivHomePostImage)

            typeface.setTypefaceForSubHeaderRegular(binding.tvHomeUsername, context)

            typeface.setTypefaceForSubHeaderRegular(binding.btnDayOfChallenge, context)
            typeface.setTypefaceForSubHeaderBold(binding.tvHomeChallengeTitle, context)
            typeface.setTypefaceForBodyItalic(binding.tvChallengepostDesc, context)
        }

    }
}