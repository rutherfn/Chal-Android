package com.nicholasrutherford.chal.helpers

import android.content.Context
import android.graphics.Typeface
import android.widget.TextView

class Typeface {

    fun setTypefaceForHeaderBold(textView: TextView, context: Context) {
        val typefaceHeaderBold = Typeface.createFromAsset(context.assets, "font/AlegreyaSC-Bold.otf")
        textView.typeface = typefaceHeaderBold
    }

    fun setTypefaceForHeaderRegular(textView: TextView, context: Context) {
        val typefaceHeaderRegular = Typeface.createFromAsset(context.assets, "font/Alegreya-Regular.otf")
        textView.typeface = typefaceHeaderRegular
    }

    fun setTypefaceForSubHeaderBold(textView: TextView, context: Context) {
        val typefaceSubHeaderBold = Typeface.createFromAsset(context.assets, "font/Domine-Bold.ttf")
        textView.typeface = typefaceSubHeaderBold
    }

    fun setTypefaceForSubHeaderRegular(textView: TextView, context: Context) {
        val typefaceSubHeaderRegular = Typeface.createFromAsset(context.assets, "font/Domine-Regular.ttf")
        textView.typeface = typefaceSubHeaderRegular
    }

    fun setTypefaceForBodyBold(textView: TextView, context: Context) {
        val typefaceBodyBold = Typeface.createFromAsset(context.assets, "font/OpenSans-Bold.ttf")
        textView.typeface = typefaceBodyBold
    }

    fun setTypefaceForBodyItalic(textView: TextView, context: Context) {
        val typefaceBodyItalic =
            Typeface.createFromAsset(context.assets, "font/OpenSans-Italic.ttf")
        textView.typeface = typefaceBodyItalic
    }

    fun setTypefaceForBodyLight(textView: TextView, context: Context) {
        val typefaceBodyLight = Typeface.createFromAsset(context.assets, "font/OpenSans-Light.ttf")
        textView.typeface = typefaceBodyLight
    }

    fun setTypefaceForBodyRegular(textView: TextView, context: Context) {
        val typefaceBody = Typeface.createFromAsset(context.assets, "font/OpenSans-Regular.ttf")
        textView.typeface = typefaceBody
    }

}