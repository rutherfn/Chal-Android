package com.nicholasrutherford.chal.recycler.viewholders

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.helpers.Helper
import com.nicholasrutherford.chal.helpers.Typeface
import com.nicholasrutherford.chal.data.responses.ChallengeResponse

class SearchChallengesViewHolder (itemView: View, private val mContext: Context) : RecyclerView.ViewHolder(itemView) {

    private val tvChallengesViews: TextView = itemView.findViewById(R.id.tvChallengeViews)
    private val tvChallengeDuration: TextView = itemView.findViewById(R.id.tvChallengesDuration)
    private val tvChallenge: TextView = itemView.findViewById(R.id.tvChallenge)

    private val typeface = Typeface()
    private val helper = Helper()

    private val listOfViews: ArrayList<String> = ArrayList()
    private val listOfDurations : ArrayList<String> = ArrayList()
    private val listOfChallenges : ArrayList<String> = ArrayList()

    fun main(context: Context, pos: Int) {

        listOfViews.clear()

        listOfDurations.clear()

        listOfChallenges.clear()

        listOfViews.add("2,131")
        listOfViews.add("55")
        listOfViews.add("34")
        listOfViews.add("22")
        listOfViews.add("4,031")
        listOfViews.add("2,431")
        listOfViews.add("1,031")
        listOfViews.add("555")
        listOfViews.add("11")

        listOfDurations.add("7 Day")
        listOfDurations.add("7 Day")
        listOfDurations.add("7 Day")
        listOfDurations.add("7 Day")
        listOfDurations.add("7 Day")
        listOfDurations.add("7 Day")
        listOfDurations.add("7 Day")
        listOfDurations.add("7 Day")
        listOfDurations.add("7 Day")
        listOfDurations.add("7 Day")
        listOfDurations.add("7 Day")
        listOfDurations.add("7 Day")

        listOfChallenges.add("#Pushup Challenge")
        listOfChallenges.add("#New Woman Challenge")
        listOfChallenges.add("#Hydration Challenge")
        listOfChallenges.add("#Pushup Challenge")
        listOfChallenges.add("#New Woman Challenge")
        listOfChallenges.add("#Hydration Challenge")
        listOfChallenges.add("#Pushup Challenge")
        listOfChallenges.add("#New Woman Challenge")
        listOfChallenges.add("#Hydration Challenge")
        listOfChallenges.add("#Pushup Challenge")
        listOfChallenges.add("#New Woman Challenge")
        listOfChallenges.add("#Hydration Challenge")

        typeface.setTypefaceForSubHeaderBold(tvChallengesViews, context)
        typeface.setTypefaceForBodyBold(tvChallengeDuration, context)
        typeface.setTypefaceForBodyBold(tvChallenge, context)

        //tvChallengesViews.text = listOfViews[pos]
        tvChallenge.text = listOfChallenges[pos]
        tvChallengeDuration.text = listOfDurations[pos]
    }

}