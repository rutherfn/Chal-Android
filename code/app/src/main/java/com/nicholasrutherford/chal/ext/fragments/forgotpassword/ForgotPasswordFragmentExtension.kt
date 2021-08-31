package com.nicholasrutherford.chal.ext.fragments.forgotpassword

import com.nicholasrutherford.chal.databinding.FragmentForgotPasswordBinding

interface ForgotPasswordFragmentExtension {
    fun collectViewStateUpdated(bind: FragmentForgotPasswordBinding)
    fun editorActionListener(bind: FragmentForgotPasswordBinding)
    fun textChangedListener(bind: FragmentForgotPasswordBinding)
    fun updateTypefaces(bind: FragmentForgotPasswordBinding)
    fun clickListeners(bind: FragmentForgotPasswordBinding)
    fun updateView(bind: FragmentForgotPasswordBinding)
}