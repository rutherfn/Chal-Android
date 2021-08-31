package com.nicholasrutherford.chal.ext.fragments.login

import com.nicholasrutherford.chal.databinding.FragmentLoginBinding

interface LoginFragmentExt {
    fun main(bind: FragmentLoginBinding)
    fun collectViewStateUpdated(bind: FragmentLoginBinding)
    fun updateTypefaces(bind: FragmentLoginBinding)
    fun textChangedListener(bind: FragmentLoginBinding)
    fun editActionListener(bind: FragmentLoginBinding)
    fun clickListeners(bind: FragmentLoginBinding)
    fun updateView(bind: FragmentLoginBinding)
}