package com.nicholasrutherford.chal.recycler.viewholders

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.helpers.Helper
import com.nicholasrutherford.chal.helpers.Typeface
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class SuggestedFriendsViewHolder (itemView: View, private val mContext: Context) : RecyclerView.ViewHolder(itemView) {

    // declarations
    private val cvUserFirebase: CircleImageView = itemView.findViewById(R.id.cvUserFirebase)
    private val tvUserFullName : TextView = itemView.findViewById(R.id.tvUserFullName)
    private val tvSubText: TextView = itemView.findViewById(R.id.tvSubText)

    private val typeface = Typeface()
    private val helper = Helper()

    fun main(context: Context) {

        typeface.setTypefaceForSubHeaderBold(tvUserFullName, context)
        typeface.setTypefaceForBodyItalic(tvSubText, context)

        helper.setTextViewColor(context, tvUserFullName, R.color.colorPrimary)
        helper.setTextViewColor(context, tvSubText, R.color.colorBlack)

        Picasso.get().load("https://images.squarespace-cdn.com/content/v1/571681753c44d835a440c8b5/1505504729161-1UJ19XWMXEC49USEI8XB/ke17ZwdGBToddI8pDm48kKXUMXbOAS9uLKnw2cR7RqNZw-zPPgdn4jUwVcJE1ZvWQUxwkmyExglNqGp0IvTJZamWLI2zvYWH8K3-s_4yszcp2ryTI0HqTOaaUohrI8PIl5fwWS02sClU08YhH79XKOzn_YMhvFfehdbF3LHEgY8KMshLAGzx4R3EDFOm1kBS/joepoeschl.jpg")
            .into(cvUserFirebase)
        tvUserFullName.text = "Joe Poeschl "
        tvSubText.text = "Because you like design..."
    }

}