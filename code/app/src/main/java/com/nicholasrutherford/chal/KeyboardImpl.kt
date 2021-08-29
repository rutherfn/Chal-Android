package com.nicholasrutherford.chal

import android.content.Context
import android.view.inputmethod.InputMethodManager
import com.nicholasrutherford.chal.main.MainActivity
import javax.inject.Inject

class KeyboardImpl @Inject constructor(private val mainActivity: MainActivity) {

    fun hideKeyBoard() {
        val inputManager: InputMethodManager = mainActivity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(mainActivity.currentFocus?.windowToken, InputMethodManager.SHOW_FORCED)
    }
}