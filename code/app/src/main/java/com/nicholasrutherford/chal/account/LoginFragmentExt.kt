package com.nicholasrutherford.chal.account

import com.nicholasrutherford.chal.databinding.FragmentLoginBinding

interface LoginFragmentExt {
    fun bind(bind: FragmentLoginBinding)
    fun updateView(bind: FragmentLoginBinding)
    fun updateTypefaces(bind: FragmentLoginBinding)
    fun updateColors(bind: FragmentLoginBinding)
    fun textChangedListener(bind: FragmentLoginBinding)
    fun editActionListener(bind: FragmentLoginBinding)
    fun clickListeners(bind: FragmentLoginBinding)
}