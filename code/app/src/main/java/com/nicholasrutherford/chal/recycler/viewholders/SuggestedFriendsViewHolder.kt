package com.nicholasrutherford.chal.recycler.viewholders

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.data.responses.SearchPeople
import com.nicholasrutherford.chal.helpers.Helper
import com.nicholasrutherford.chal.helpers.Typeface
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class SuggestedFriendsViewHolder (itemView: View, private val mContext: Context,private var searchPeopleList: ArrayList<SearchPeople>, private var filterSearchPeopleList: ArrayList<SearchPeople> ) : RecyclerView.ViewHolder(itemView) {

    // declarations
    private val cvUserFirebase: CircleImageView = itemView.findViewById(R.id.cvUserFirebase)
    private val tvUserFullName : TextView = itemView.findViewById(R.id.tvUserFullName)
    private val tvSubText: TextView = itemView.findViewById(R.id.tvSubText)

    private val typeface = Typeface()
    private val helper = Helper()

    init {
        filterSearchPeopleList = searchPeopleList
    }

    fun main(context: Context, i: Int) {

        typeface.setTypefaceForSubHeaderBold(tvUserFullName, context)
        typeface.setTypefaceForBodyItalic(tvSubText, context)

        helper.setTextViewColor(context, tvUserFullName, R.color.colorPrimary)
        helper.setTextViewColor(context, tvSubText, R.color.colorBlack)

        Picasso.get().load(filterSearchPeopleList[i].profileImageValue)
            .into(cvUserFirebase)
        tvUserFullName.text = filterSearchPeopleList[i].firstName.plus(" ").plus(filterSearchPeopleList[i].lastName)
        tvSubText.text = filterSearchPeopleList[i].reasonSuggested
    }

}