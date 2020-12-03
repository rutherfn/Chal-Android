package com.nicholasrutherford.chal.account.signup

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nicholasrutherford.chal.databinding.FragmentSignUpBinding
import com.nicholasrutherford.chal.ext.signup.SignUpFragmentExtension
import com.nicholasrutherford.chal.helpers.Typeface
import com.nicholasrutherford.chal.navigationimpl.SignUpNavigationImpl

class SignUpFragment(private val activity: SignUpActivity, private val appContext: Context) : Fragment(),
    SignUpFragmentExtension {

    private val typeface = Typeface()
    private val navigation = SignUpNavigationImpl()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val bind = FragmentSignUpBinding.inflate(layoutInflater)
        updateTypefaces(bind)
        clickListeners(bind)
        return bind.root
    }

    override fun updateTypefaces(bind: FragmentSignUpBinding) {
        typeface.setTypefaceForHeaderBold(bind.tvSignUp, appContext)
        typeface.setTypefaceForHeaderRegular(bind.tvSubHeader, appContext)

        typeface.setTypefaceForBodyBold(bind.tvAlreadyHaveAccount, appContext)
        typeface.setTypefaceForBodyBold(bind.tvLogin, appContext)
    }

    override fun clickListeners(bind: FragmentSignUpBinding) {
        bind.ivGram.setOnClickListener {
            navigation.socialMedia(isGram = true, isFacebook = false, isLinkedin = false, signUpActivity = activity)
        }

        bind.ivFacebook.setOnClickListener {
            navigation.socialMedia(isGram = false, isFacebook = true, isLinkedin = false, signUpActivity = activity)
        }

        bind.ivLinkedin.setOnClickListener {
            navigation.socialMedia(isGram = false, isFacebook = false, isLinkedin = true, signUpActivity = activity)
        }

        bind.tvLogin.setOnClickListener {
            navigation.login(activity)
        }

        bind.btnSignUpRegular.setOnClickListener {
            navigation.createAccount(activity)
        }

    }

}