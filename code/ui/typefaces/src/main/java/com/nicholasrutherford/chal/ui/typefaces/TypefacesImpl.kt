package com.nicholasrutherford.chal.ui.typefaces

import android.app.Application
import android.graphics.Typeface
import android.widget.TextView
import javax.inject.Inject

class TypefacesImpl @Inject constructor(private val application: Application) : Typefaces {

    override fun typeface(path: String): Typeface {
        return Typeface.createFromAsset(application.applicationContext.assets, path)
    }

    override fun setTextViewHeaderBoldTypeface(textview: TextView) {
        textview.typeface = typeface(path = TypefacesType.MONTSERRAT_BOLD.directory)
    }

    override fun setTextViewHeaderItalicTypeface(textview: TextView) {
        textview.typeface = typeface(path = TypefacesType.MONTSERRAT_ITALIC.directory)
    }

    override fun setTextViewHeaderLightTypeface(textview: TextView) {
        textview.typeface = typeface(path = TypefacesType.MONTSERRAT_LIGHT.directory)
    }

    override fun setTextViewHeaderRegularTypeface(textview: TextView) {
        textview.typeface = typeface(path = TypefacesType.MONTSERRAT_REGULAR.directory)
    }

    override fun setTextViewSubHeaderBoldTypeface(textview: TextView) {
        textview.typeface = typeface(path = TypefacesType.ROBOTO_BOLD.directory)
    }

    override fun setTextViewSubHeaderItalicTypeface(textview: TextView) {
        textview.typeface = typeface(path = TypefacesType.ROBOTO_ITALIC.directory)
    }

    override fun setTextViewSubHeaderLightTypeface(textview: TextView) {
        textview.typeface = typeface(path = TypefacesType.ROBOTO_LIGHT.directory)
    }

    override fun setTextViewSubHeaderRegularTypeface(textview: TextView) {
        textview.typeface = typeface(path = TypefacesType.ROBOTO_REGULAR.directory)
    }

    override fun setTextViewBodyBoldTypeface(textview: TextView) {
        textview.typeface = typeface(path = TypefacesType.OPEN_SANS_BOLD.directory)
    }

    override fun setTextViewBodyItalicTypeface(textview: TextView) {
        textview.typeface = typeface(path = TypefacesType.OPEN_SANS_ITALIC.directory)
    }

    override fun setTextViewBodyLightTypeface(textview: TextView) {
        textview.typeface = typeface(path = TypefacesType.OPEN_SANS_LIGHT.directory)
    }

    override fun setTextViewBodyRegularTypeface(textview: TextView) {
        textview.typeface = typeface(path = TypefacesType.OPEN_SANS_REGULAR.directory)
    }
}