package com.nicholasrutherford.chal.ext.forgotpassword

import com.nicholasrutherford.chal.databinding.FragmentForgotPasswordBinding

interface ForgotPasswordFragmentExtension {
    fun editorActionListener(bind: FragmentForgotPasswordBinding)
    fun textChangedListener(bind: FragmentForgotPasswordBinding)
    fun updateTypefaces(bind: FragmentForgotPasswordBinding)
    fun clickListener(bind: FragmentForgotPasswordBinding)
}