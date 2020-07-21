package com.nicholasrutherford.chal.recycler.viewholders

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.helpers.Helper
import com.nicholasrutherford.chal.helpers.Typeface

class ProfileSettingsViewHolder (itemView: View, private val mContext: Context, private val listOfSubTitles: ArrayList<String>) : RecyclerView.ViewHolder(itemView) {

    // declarations
    private val tvSettingsEntry: TextView  = itemView.findViewById(R.id.tvSettingsEntry)

    private val typeface = Typeface()
    private val helper = Helper()

     fun main(context: Context, pos: Int, listOfSubTitles: ArrayList<String>) {

        typeface.setTypefaceForSubHeaderRegular(tvSettingsEntry, context)

        helper.setTextViewColor(context, tvSettingsEntry, R.color.colorBlack)

        tvSettingsEntry.text = listOfSubTitles[pos]
    }

}