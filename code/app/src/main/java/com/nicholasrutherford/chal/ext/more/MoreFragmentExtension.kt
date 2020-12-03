package com.nicholasrutherford.chal.ext.more

import com.nicholasrutherford.chal.databinding.FragmentMoreBinding

interface MoreFragmentExtension {
    fun updateTypefaces(bind: FragmentMoreBinding)
    fun updateView(bind: FragmentMoreBinding)
    fun clickListeners(bind: FragmentMoreBinding)
    fun containerId(): Int
}