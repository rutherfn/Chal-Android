package com.nicholasrutherford.chal.ext

import com.nicholasrutherford.chal.databinding.FragmentLoginBinding

interface LoginFragmentExtension {
    fun main(bind: FragmentLoginBinding)
    fun updateTypefaces(bind: FragmentLoginBinding)
    fun textChangedListener(bind: FragmentLoginBinding)
    fun editActionListener(bind: FragmentLoginBinding)
    fun clickListeners(bind: FragmentLoginBinding)
}