package com.nicholasrutherford.chal.bugreport

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nicholasrutherford.chal.MainActivity
import com.nicholasrutherford.chal.databinding.FragmentBugReportBinding
import com.nicholasrutherford.chal.ext.fragments.bugreport.BugReportFragmentExtension
import com.nicholasrutherford.chal.helpers.Typeface

class BugReportFragment (private val mainActivity: MainActivity, private val appContext: Context): Fragment(), BugReportFragmentExtension {

    private val typeface = Typeface()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val bind = FragmentBugReportBinding.inflate(layoutInflater)
        updateTypefaces(bind)
        clickListeners(bind)
        updateView(bind)

        return bind.root
    }

    override fun updateTypefaces(bind: FragmentBugReportBinding) {
        typeface.setTypefaceForHeaderBold(bind.tbBugReport.tvTitle, appContext)
    }

    override fun clickListeners(bind: FragmentBugReportBinding) {
        TODO("Not yet implemented")
    }

    override fun containerId(): Int {
        TODO("Not yet implemented")
    }

    override fun updateView(bind: FragmentBugReportBinding) {
        TODO("Not yet implemented")
    }

}