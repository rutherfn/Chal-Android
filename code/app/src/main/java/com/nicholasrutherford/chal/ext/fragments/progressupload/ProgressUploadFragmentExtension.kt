package com.nicholasrutherford.chal.ext.fragments.progressupload

import com.nicholasrutherford.chal.databinding.FragmentProgressUploadBinding

interface ProgressUploadFragmentExtension {
    fun main(bind: FragmentProgressUploadBinding)
    fun updateTypefaces(bind: FragmentProgressUploadBinding)
    fun updateView(bind: FragmentProgressUploadBinding)
    fun clickListeners(bind: FragmentProgressUploadBinding)
}