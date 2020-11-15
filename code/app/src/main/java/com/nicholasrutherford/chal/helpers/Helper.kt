package com.nicholasrutherford.chal.helpers

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.core.content.ContextCompat
import java.io.IOException

class Helper {

    fun setTextViewColor(context: Context, textView: TextView, color: Int) { // helper method?
        textView.setTextColor(ContextCompat.getColor(context, color))
    }

    @Throws(InterruptedException::class, IOException::class) // responsible for checking internet connection, by hitting google.com. If it cant hit it, return back false
    fun isConnected(): Boolean {
        val command = "ping -c 1 google.com"
        return Runtime.getRuntime().exec(command).waitFor() == 0
    }

    fun hideSoftKeyBoard(activity: Activity) {
        val inputManager: InputMethodManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(activity.currentFocus?.windowToken, InputMethodManager.SHOW_FORCED)
    }

}