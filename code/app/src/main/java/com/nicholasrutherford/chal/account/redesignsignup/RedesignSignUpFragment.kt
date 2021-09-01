package com.nicholasrutherford.chal.account.redesignsignup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nicholasrutherford.chal.databinding.FragmentSignUpBinding
import com.nicholasrutherford.chal.ext.fragments.signup.SignUpFragmentExt
import com.nicholasrutherford.chal.ui.typefaces.TypefacesImpl
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class RedesignSignUpFragment @Inject constructor(
    private val typeface: TypefacesImpl
) : DaggerFragment(), SignUpFragmentExt {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val bind = FragmentSignUpBinding.inflate(layoutInflater)
        updateTypefaces(bind)
        clickListeners(bind)
        return bind.root
    }

    override fun updateTypefaces(bind: FragmentSignUpBinding) {
        typeface.setTextViewHeaderBoldTypeface(bind.tvSignUp)
        typeface.setTextViewSubHeaderRegularTypeface(bind.tvSubHeader)

        typeface.setTextViewBodyBoldTypeface(bind.tvAlreadyHaveAccount)
        typeface.setTextViewBodyBoldTypeface(bind.tvLogin)
    }

    override fun clickListeners(bind: FragmentSignUpBinding) {
        bind.ivGram.setOnClickListener {
        }

        bind.ivFacebook.setOnClickListener {
        }

        bind.ivLinkedin.setOnClickListener {
        }

        bind.tvLogin.setOnClickListener {
        }

        bind.btnSignUpRegular.setOnClickListener {
        }
    }
}