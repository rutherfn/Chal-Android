package com.nicholasrutherford.chal.navigationimpl.signup

import android.content.Intent
import com.nicholasrutherford.chal.account.createaccount.CreateAccountActivity
import com.nicholasrutherford.chal.account.login.LoginActivity
import com.nicholasrutherford.chal.account.signup.SignUpNavigation
import com.nicholasrutherford.chal.account.signup.SignUpActivity
import com.nicholasrutherford.chal.account.socialmedia.SocialMediaWebActivity

class SignUpNavigationImpl : SignUpNavigation {
    override fun login(signUpActivity: SignUpActivity) {
        val intent = Intent(signUpActivity.applicationContext, LoginActivity::class.java)
        signUpActivity.startActivity(intent)
        signUpActivity.finish()
    }

    override fun createAccount(signUpActivity: SignUpActivity) {
        val intent = Intent(signUpActivity.applicationContext, CreateAccountActivity::class.java)
        signUpActivity.startActivity(intent)
        signUpActivity.finish()
    }

    override fun socialMedia(isGram: Boolean, isFacebook: Boolean, isLinkedin: Boolean, signUpActivity: SignUpActivity) {
        val intent = Intent(signUpActivity.applicationContext, SocialMediaWebActivity::class.java)
        intent.putExtra("isGram", isGram)
        intent.putExtra("isFaceBook", isFacebook)
        intent.putExtra("isLinkedin", isLinkedin)
        signUpActivity.startActivity(intent)
    }

}