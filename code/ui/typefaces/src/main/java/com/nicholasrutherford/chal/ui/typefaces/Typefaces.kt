package com.nicholasrutherford.chal.ui.typefaces

import android.graphics.Typeface
import android.widget.TextView

interface Typefaces {
    fun typeface(path: String): Typeface

    fun setTextViewHeaderBoldTypeface(textview: TextView)
    fun setTextViewHeaderItalicTypeface(textview: TextView)
    fun setTextViewHeaderLightTypeface(textview: TextView)
    fun setTextViewHeaderRegularTypeface(textview: TextView)

    fun setTextViewSubHeaderBoldTypeface(textview: TextView)
    fun setTextViewSubHeaderItalicTypeface(textview: TextView)
    fun setTextViewSubHeaderLightTypeface(textview: TextView)
    fun setTextViewSubHeaderRegularTypeface(textview: TextView)

    fun setTextViewBodyBoldTypeface(textview: TextView)
    fun setTextViewBodyItalicTypeface(textview: TextView)
    fun setTextViewBodyLightTypeface(textview: TextView)
    fun setTextViewBodyRegularTypeface(textview: TextView)
}