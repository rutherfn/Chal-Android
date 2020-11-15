package com.nicholasrutherford.chal.account.signup

interface SignUpNavigation {
    fun login(signUpActivity: SignUpActivity)
    fun createAccount(signUpActivity: SignUpActivity)
    fun socialMedia(isGram: Boolean, isFacebook: Boolean, isLinkedin: Boolean, signUpActivity: SignUpActivity)
}