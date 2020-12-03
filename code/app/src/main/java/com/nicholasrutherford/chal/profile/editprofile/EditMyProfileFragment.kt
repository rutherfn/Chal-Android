package com.nicholasrutherford.chal.profile.editprofile

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nicholasrutherford.chal.R
import com.nicholasrutherford.chal.activitys.MainActivity
import com.nicholasrutherford.chal.databinding.FragmentEditProfileBinding
import com.nicholasrutherford.chal.ext.editprofile.EditProfileExtension
import com.nicholasrutherford.chal.helpers.Typeface

class EditMyProfileFragment(private val mainActivity: MainActivity, private val appContext: Context): Fragment(), EditProfileExtension {

    private val typeface = Typeface()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val bind = FragmentEditProfileBinding.inflate(layoutInflater)
        updateTypefaces(bind)
        clickListeners(bind)
        updateView(bind)
        return bind.root
    }
    override fun updateTypefaces(bind: FragmentEditProfileBinding) {
    }

    override fun updateView(bind: FragmentEditProfileBinding) {
    }

    override fun clickListeners(bind: FragmentEditProfileBinding) {

    }

    override fun containerId(): Int {
        return R.id.container
    }

}