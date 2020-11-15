package com.nicholasrutherford.chal.ext

import com.nicholasrutherford.chal.databinding.FragmentCreateAccountBinding

interface CreateAccountFragmentExtension {
    fun main(bind: FragmentCreateAccountBinding)
    fun updateTypefaces(bind: FragmentCreateAccountBinding)
    fun textChangedListener(bind: FragmentCreateAccountBinding)
    fun editActionListener(bind: FragmentCreateAccountBinding)
    fun clickListeners(bind: FragmentCreateAccountBinding)
}