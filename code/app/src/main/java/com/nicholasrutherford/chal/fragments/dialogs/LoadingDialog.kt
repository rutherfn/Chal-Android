package com.nicholasrutherford.chal.fragments.dialogs

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.helpers.Helper
import com.nicholasrutherford.chal.helpers.Typeface

class LoadingDialog : DialogFragment() {

    // declarations
    private var mView: View? = null
    private var typeface = Typeface()
    private var helper = Helper()
    private lateinit var pbLoading: ProgressBar
    private lateinit var tvLoading: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater.inflate(R.layout.loading_dialog, container, false)
        isCancelable = false
        main()
        return mView
    }

    private fun main() {
        setupIds()
    }

    private fun setupIds() {
        pbLoading = mView!!.findViewById(R.id.pbLoading)
        tvLoading = mView!!.findViewById(R.id.tvLoading)
        setupLoadingAndProgress()
    }

    @SuppressLint("ResourceAsColor")
    private fun setupLoadingAndProgress() {

        context?.let {
            typeface.setTypefaceForHeaderBold(tvLoading, it)
            helper.setTextViewColor(it,tvLoading, R.color.colorPrimary)
        }

    //    DrawableCompat.setTint(pbLoading.progressDrawable, R.color.colorBlack);
    }



}