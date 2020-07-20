package com.nicholasrutherford.chal.helpers

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.core.content.ContextCompat

class Helper {

    fun setTextViewColor(context: Context, textView: TextView, color: Int) { // helper method?
        textView.setTextColor(ContextCompat.getColor(context, color))
    }

    fun hideSoftKeyBoard(activity: Activity) {
        val inputManager: InputMethodManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(activity.currentFocus?.windowToken, InputMethodManager.SHOW_FORCED)
    }

}