package com.nicholasrutherford.chal.recycler.viewholders

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.helpers.Helper
import com.nicholasrutherford.chal.helpers.Typeface
import com.nicholasrutherford.chal.data.responses.ChallengeResponse
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class ActiveChallengesViewHolder(itemView: View, private val mContext: Context, private val activeChallengesList: MutableList<ChallengeResponse>?) : RecyclerView.ViewHolder(itemView) {

    // declarations
    private var cvChallenge: CircleImageView = itemView.findViewById(R.id.cvChallenge)
    private var tvChallengesTitle: TextView = itemView.findViewById(R.id.tvChallengesTitle)
    private var tvCategoryChallenge: TextView = itemView.findViewById(R.id.tvCategoryChallenge)

    private val typeface = Typeface()
    private val helper = Helper()

    private val listOfImages: ArrayList<String> = ArrayList()
    private val listOfTitles : ArrayList<String> = ArrayList()
    private val listOfCategories : ArrayList<String> = ArrayList()

    fun main(context: Context, pos: Int, activeChallengesList: MutableList<ChallengeResponse>?) {

        setupView(context)

        listOfTitles.clear()

        listOfCategories.clear()

        listOfImages.clear()

        listOfTitles.add("30 Days Cook Something New Challenge")
        listOfTitles.add("#100 Days Of Coding Challenge TEST")
        listOfTitles.add("30 Days of Nature")

        listOfCategories.add("Cooking")
        listOfCategories.add("Coding")
        listOfCategories.add("Nature")

        listOfImages.add("https://fitnessfor10.com/wp-content/uploads/2019/05/Fitness-for-10-Home-Licensing-Information.jpg")
        listOfImages.add("https://fitnessfor10.com/wp-content/uploads/2019/05/Fitness-for-10-Home-Licensing-Information.jpg")
        listOfImages.add("https://fitnessfor10.com/wp-content/uploads/2019/05/Fitness-for-10-Home-Licensing-Information.jpg")

        if (activeChallengesList != null) {
            Picasso.get().load(listOfImages[pos]).into(cvChallenge)
            tvChallengesTitle.text = listOfTitles[pos]
            tvCategoryChallenge.text = listOfCategories[pos]
        }
    }

    private fun setupView(context: Context) {

        typeface.setTypefaceForHeaderBold(tvChallengesTitle, context)
        typeface.setTypefaceForBodyItalic(tvCategoryChallenge, context)

        helper.setTextViewColor(context, tvChallengesTitle, R.color.colorPrimary)
        helper.setTextViewColor(context, tvCategoryChallenge, R.color.colorBlack)
    }

}