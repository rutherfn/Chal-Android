package com.nicholasrutherford.chal.helpers

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity

class Helper {

    fun setTextViewColor(context: Context, textView: TextView, color: Int) { // helper method?
        textView.setTextColor(ContextCompat.getColor(context, color))
    }

    fun setTextViewColorWithString(context: Context, textView: TextView, color: String) { // helper method?
        textView.setTextColor(Color.parseColor(color))
    }

    fun hideSoftKeyBoard(activity: Activity) {
        val inputManager: InputMethodManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(activity.currentFocus?.windowToken, InputMethodManager.SHOW_FORCED)
    }

}