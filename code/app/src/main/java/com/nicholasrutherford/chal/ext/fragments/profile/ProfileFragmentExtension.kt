package com.nicholasrutherford.chal.ext.fragments.profile

import com.nicholasrutherford.chal.databinding.FragmentProfilesBinding

interface ProfileFragmentExtension  {
    fun updateTypefaces(bind: FragmentProfilesBinding)
    fun updateView(bind: FragmentProfilesBinding)
    fun clickListeners(bind: FragmentProfilesBinding)
    fun containerId(): Int
}