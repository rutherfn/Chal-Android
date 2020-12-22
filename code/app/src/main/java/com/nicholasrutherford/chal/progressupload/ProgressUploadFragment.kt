package com.nicholasrutherford.chal.progressupload

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nicholasrutherford.chal.MainActivity
import com.nicholasrutherford.chal.databinding.FragmentProgressUploadBinding
import com.nicholasrutherford.chal.ext.fragments.progressupload.ProgressUploadFragmentExtension
import com.nicholasrutherford.chal.helpers.Typeface

class ProgressUploadFragment (activity: MainActivity, private val appContext: Context) : Fragment(),
    ProgressUploadFragmentExtension {

    private val typeface = Typeface()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val bind = FragmentProgressUploadBinding.inflate(layoutInflater)
        main(bind)
        return bind.root
    }

    override fun main(bind: FragmentProgressUploadBinding) {
        updateTypefaces(bind)

        updateView(bind)
        clickListeners(bind)
    }

    override fun updateTypefaces(bind: FragmentProgressUploadBinding) {
        typeface.setTypefaceForHeaderBold(bind.clPostProgress.tvPostProgress, appContext)
        typeface.setTypefaceForBodyRegular(bind.clPostProgress.tvPostProgress, appContext)

        typeface.setTypefaceForSubHeaderRegular(bind.clPostProgress.tvSelectChallenge, appContext)
        typeface.setTypefaceForSubHeaderRegular(bind.clPostProgress.tvSelectCategory, appContext)
        typeface.setTypefaceForSubHeaderRegular(bind.clPostProgress.tvAddCaption, appContext)
        typeface.setTypefaceForSubHeaderRegular(bind.clPostProgress.tvUploadImage, appContext)

        typeface.setTypefaceForSubHeaderRegular(bind.clPostProgress.btnPostProgressToMyFeed, appContext)
        typeface.setTypefaceForSubHeaderRegular(bind.clPostProgress.btnCancelAndDiscardPost, appContext)
    }

    override fun updateView(bind: FragmentProgressUploadBinding) {

    }

    override fun clickListeners(bind: FragmentProgressUploadBinding) {
    }

}