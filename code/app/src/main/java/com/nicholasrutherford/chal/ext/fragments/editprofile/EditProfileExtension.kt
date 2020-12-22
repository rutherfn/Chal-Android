package com.nicholasrutherford.chal.ext.fragments.editprofile

import com.nicholasrutherford.chal.databinding.FragmentEditProfileBinding

interface EditProfileExtension  {
    fun updateTypefaces(bind: FragmentEditProfileBinding)
    fun updateView(bind: FragmentEditProfileBinding)
    fun clickListeners(bind: FragmentEditProfileBinding)
    fun containerId(): Int
}